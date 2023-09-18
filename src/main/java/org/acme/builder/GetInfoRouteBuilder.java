package org.acme.builder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;

import jakarta.enterprise.context.ApplicationScoped;

import org.acme.bindy.ftp.Persona;
import org.acme.bean.ConsultaReniec;
import org.acme.bean.Respuesta;
import org.acme.bindy.ftp.Header;
import org.acme.processor.GetInfoProcessor;
import org.acme.repository.PersonaRepository;

import jakarta.inject.Inject;

@ApplicationScoped
public class GetInfoRouteBuilder extends RouteBuilder {

   //private JacksonDataFormat format=new JacksonDataFormat(Persona.class);
   //private BindyFixedLengthDataFormat camelDataFormat = new BindyFixedLengthDataFormat(Persona.class);
   //private JacksonDataFormat format=new JacksonDataFormat(ConsultaReniec.class);
   private JacksonDataFormat formatRpta=new JacksonDataFormat(Respuesta.class);
   private BindyFixedLengthDataFormat camelDataFormat = new BindyFixedLengthDataFormat(Header.class);

    // @ConfigProperty(name = "app.jms.queue-validated")
    // private String queue_in;
     @ConfigProperty(name = "app.jms.queue-validated")
    private String queue_in;

    @ConfigProperty(name = "app.jms.queue-processed")
    private String queue_out;

    @Inject
    PersonaRepository personaRepository;

    @Override
    public void configure() throws Exception {


        System.out.println("=====inicia marshall");  

        from(String.format("jms:queue:%s",queue_in))
            .log("Received a message - ${body} - sending to processed")
            .unmarshal(camelDataFormat)
            //.unmarshal(camelDataFormat)   //.bindy(BindyType.Fixed, Persona.class)
            .process(new GetInfoProcessor(personaRepository))
            
            //.marshal(camelDataFormat)
            .marshal(formatRpta)
        .to(String.format("jms:queue:%s",queue_out));
    }

}
