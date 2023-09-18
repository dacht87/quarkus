// package org.acme;

// import org.apache.camel.builder.RouteBuilder;
// import org.eclipse.microprofile.config.inject.ConfigProperty;

// import jakarta.enterprise.context.ApplicationScoped;

// @ApplicationScoped
// public class DistributeRouteBuilder extends RouteBuilder {

//     @ConfigProperty(name = "app.jms.queue-processed")
//     private String queue_in;

//     @ConfigProperty(name = "app.jms.queue-end")
//     private String queue_out;

//     @Override
//     public void configure() throws Exception {
//         from(String.format("jms:queue:%s",queue_in))
//             .log("Received a message - ${body} - sending to end")
//         .to(String.format("jms:queue:%s",queue_out));
//     }

// }
