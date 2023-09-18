package org.acme;

public class DynamicRouter {
    
    String route(String body){
        return String.format("jms:queue:%s","DEV.QUEUE.RESPUESTA.CLI1");
    }
}
