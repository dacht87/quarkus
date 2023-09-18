package org.acme.builder;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import jakarta.enterprise.context.ApplicationScoped;

import org.acme.bean.ConsultaReniec;
import org.acme.bean.Respuesta;
import org.acme.processor.DistributeProcessor;

@ApplicationScoped
public class DistributeRouteBuilder extends RouteBuilder {

    private JacksonDataFormat format=new JacksonDataFormat(ConsultaReniec.class);
    private JacksonDataFormat formatRpta=new JacksonDataFormat(Respuesta.class);

    @ConfigProperty(name = "app.jms.queue-processed")
    private String queue_in;

    @ConfigProperty(name = "app.jms.queue-end")
    private String queue_out;

    // @Override
    // public void configure() throws Exception {
    //     from(String.format("jms:queue:%s",queue_in))
    //         .log("Received a message - ${body} - sending to end")
    //         .unmarshal(format)
    //         .process(new DistributeProcessor())
    //         .marshal(formatRpta)
    //     .to(String.format("jms:queue:%s",queue_out));
    // }
    @Override
    public void configure() throws Exception {
        from(String.format("jms:queue:%s",queue_in))
            .log("Received a message - ${body} - sending to end Distribute")
            .unmarshal(formatRpta)
            .process(new DistributeProcessor())
            .marshal(formatRpta)
        .to(String.format("jms:queue:%s",queue_out));
    }

}
