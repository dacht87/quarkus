
package org.acme.processor;
import org.apache.camel.Processor;
import org.acme.bindy.ftp.Persona;
import org.apache.camel.Exchange;
import org.acme.bean.ConsultaReniec;
import org.acme.bean.Respuesta;
import org.acme.bindy.ftp.Header;

import org.acme.repository.PersonaRepository;
import org.acme.model.PersonaModel;

import java.util.List;

public class GetInfoProcessor implements Processor{

   
    PersonaRepository personaRepository;
        
    public GetInfoProcessor(PersonaRepository personaRepository){
        this.personaRepository=personaRepository;
    }

    public void process(Exchange exchange) throws Exception {

        System.out.println("=====GET INFOR PROCESOR");  

        Header query = exchange.getIn().getBody(Header.class);
        System.out.println(query.tipoConsulta);
        PersonaModel model=null;
        if(query.tipoConsulta.equalsIgnoreCase("2")){
        System.out.println("=====GET INFOR PROCESOR111111"); 
            List<PersonaModel> lista=personaRepository.findByTipoYNumeroDocumento("DNI",query.usuarioFinalInst);
            System.out.println("=====GET INFOR PROCESOR22222: "+lista.size());  
            if(lista.size()>0)
                model=lista.get(0);
        }
        //System.out.println(query.nroDoc+"-"+query.fNacimiento+"-info");
        exchange.getIn().setBody(new Respuesta(model==null?"persona no encontrada" : query.getTramaHeader() + model.npersona_id+"-"+model.npersona_idtipdoc+"-"+model.cpersona_nrodoc+"-"+model.cpersona_apepaterno+"-"+model.cpersona_apematerno+"-"+model.cpersona_nombres+"-"+model.dpersona_fnacimiento+"-"+model.cpersona_codsexo));
    }
}