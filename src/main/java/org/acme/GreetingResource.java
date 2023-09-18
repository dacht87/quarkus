package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
 
@Path("/hello")
public class GreetingResource {

    @Inject
    private ConnectionFactory connectionFactory;

    @ConfigProperty(name = "app.jms.queue-start")
    private String queue;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws JMSException {

        Connection conn = connectionFactory.createConnection();

        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue destination = session.createQueue(queue);
        MessageProducer producer = session.createProducer(destination);

        TextMessage message = session.createTextMessage("00020128000000023000                      0000000002RENIECPERURENIECDE2116    RENIEC001 0000INS00070600648  HOST000000          ");
        //TextMessage message = session.createTextMessage("00020128000000023000                      0000000002RENIECPERURENIECDE2116    RENIEC001 0000INS00070600648  HOST000000");
        //TextMessage message = session.createTextMessage("00020128000000023000");
        //TextMessage message = session.createTextMessage("4587153801/01/2023");//session.createTextMessage("Hola Denis!!!");
        //TextMessage message = session.createTextMessage("{\"nroDoc\":\"45871538\", \"fNacimiento\":\"1999-05-17T00:00:00.000Z\"}");
        //TextMessage message = session.createTextMessage("4587158 17/05/1999");
        message.setStringProperty("_Destination", destination.toString());

        System.out.println("=======niciar envíox");

        producer.send(message);

        return "Message send it " + message;
    }
}
