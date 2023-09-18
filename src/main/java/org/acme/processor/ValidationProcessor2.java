package org.acme.processor;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.acme.bean.Respuesta;

public class ValidationProcessor2 implements Processor{

    //ConsultaReniecMapper mapper =new ConsultaReniecMapper();
    //private BindyFixedLengthDataFormat camelDataFormat = new BindyFixedLengthDataFormat(Header.class);

    @Override
    public void process(Exchange exchange) throws Exception {


        System.out.println("=====GET INFOR VALIDATOR2");  
        String query = exchange.getIn().getBody(String.class);

        int longuitud = query.length();

        if (longuitud != 128) {

            System.out.println("===============ERROR Longuitud incorrecta:" + longuitud);  
            exchange.getIn().setBody(new Respuesta(query + "0002"));
            return;
            
        } else {
            System.out.println("=====ok");  
            exchange.getIn().setBody(query);
        }

        // if(1 =1)
        // {
        
        //      System.out.println("=====ERROR 1");  
        //     exchange.getIn().setBody(new Respuesta("El tipo de consulta es incorrecto"));
        //     return;
        // }else{
        //     System.out.println("=====ok");  
        //     exchange.getIn().setBody(query);
        // }

        //exchange.getIn().setBody(query);

    }
}