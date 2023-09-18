
package org.acme.processor;

import org.apache.camel.Processor;
import org.apache.camel.Exchange;

import org.acme.bean.Respuesta;
import org.acme.bindy.ftp.Header;

public class ValidationProcessor implements Processor{

 
    @Override
    public void process(Exchange exchange) throws Exception {

        System.out.println("=====GET INFOR VALIDATOR");  

        Header query = exchange.getIn().getBody(Header.class);
        //String query = exchange.getIn().getBody(String.class);

        System.out.println("=====GET INFOR VALIDATOR111111" + query);  

        //System.out.println("NUEVO DNI PARSE:" + query.dni+"-"+query.fechaNacimiento+"-info");
        System.out.println("NUEVO cod Institución :" + query.version+"-info");
        System.out.println("NUEVO TTL :" + query.lonCabecera+"-info");
        System.out.println("NUEVO TTL2 :" + query.tipoServicio+"-info");
        System.out.println("NUEVO TTL2 :" + query.longTotalTrama+"-info");
        System.out.println("NUEVO TTL2 :" + query.fragmentacion+"-info");
        System.out.println("NUEVO TTL2 :" + query.TTL+"-info");
        System.out.println("NUEVO TTL2 :" + query.tipoConsulta+"-info");
        System.out.println("NUEVO TTL TRAMA :" + query.getTramaHeader()+"-info");

        System.out.println("=====GET INFOR VALIDATOR22222");  

        String tipoCons = query.tipoConsulta.trim();

        if(!tipoCons.equalsIgnoreCase("1") && !tipoCons.equalsIgnoreCase("2") ){
        
            System.out.println("=====ERROR en tipo consulta:" + tipoCons );  
            //exchange.getIn().setBody(new Respuesta("El tipo de consulta es incorrecto"));
            exchange.getIn().setBody(new Respuesta(query.getTramaHeader() + "0001"));
            return;
        }else{
            System.out.println("=====ok");  
            exchange.getIn().setBody(query);
        }

        //exchange.getIn().setBody(query);

    }
}