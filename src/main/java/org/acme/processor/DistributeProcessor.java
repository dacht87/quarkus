
package org.acme.processor;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;

import org.acme.bean.ConsultaReniec;
import org.acme.bean.Respuesta;
import org.acme.mapper.RespuestaMapper;

public class DistributeProcessor implements Processor{

    RespuestaMapper mapper =new RespuestaMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        //ConsultaReniec query = exchange.getIn().getBody(ConsultaReniec.class);
        System.out.println("Distribute: ");
        Respuesta resp = exchange.getIn().getBody(Respuesta.class);
        System.out.println("Distribute: "+resp.respuesta);
        //exchange.getIn().setBody(new Respuesta(query.nroDoc+"-"+query.fNacimiento+"- Juan Perez"));
        exchange.getIn().setBody(mapper.toString(resp));
    }
}