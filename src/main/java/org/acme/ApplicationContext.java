package org.acme;

import org.apache.camel.component.jms.JmsComponent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ibm.msg.client.jakarta.jms.JmsConnectionFactory;
import com.ibm.msg.client.jakarta.jms.JmsConstants;
import com.ibm.msg.client.jakarta.jms.JmsFactoryFactory;
import com.ibm.msg.client.jakarta.wmq.WMQConstants;
import com.ibm.msg.client.jakarta.wmq.common.CommonConstants;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;

@ApplicationScoped
public class ApplicationContext {

    @ConfigProperty(name = "app.jms.host")
    private String host;

    @ConfigProperty(name = "app.jms.port")
    private int port;

    @ConfigProperty(name = "app.jms.channel")
    private String channel;

    @ConfigProperty(name = "app.jms.queuemgr")
    private String queuemgr;

    @ConfigProperty(name = "app.jms.user")
    private String user;

    @ConfigProperty(name = "app.jms.password")
    private String password;

    @Produces
    @Default
    public ConnectionFactory connectionFactory() throws JMSException {
        
        JmsFactoryFactory ff;
        JmsConnectionFactory factory;
        try {        
            ff = JmsFactoryFactory.getInstance(JmsConstants.JAKARTA_WMQ_PROVIDER);
            factory = ff.createConnectionFactory();
            factory.setIntProperty(CommonConstants.WMQ_CONNECTION_MODE, CommonConstants.WMQ_CM_CLIENT);
            factory.setStringProperty(CommonConstants.WMQ_HOST_NAME, this.host);
            factory.setIntProperty(CommonConstants.WMQ_PORT, this.port);
            factory.setStringProperty(CommonConstants.WMQ_CHANNEL, this.channel);
            factory.setStringProperty(CommonConstants.WMQ_QUEUE_MANAGER, this.queuemgr);
            factory.setStringProperty(WMQConstants.USERID, this.user);
            factory.setStringProperty(WMQConstants.PASSWORD, this.password);
            factory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
        } catch (JMSException je) {
            throw je;
        }
        return factory;
    }

    
    @Produces
    @Default
    public JmsComponent jms() throws JMSException {
      JmsComponent jmsComponent = new JmsComponent();
      jmsComponent.setConnectionFactory(connectionFactory());
      return jmsComponent;
    }

}
