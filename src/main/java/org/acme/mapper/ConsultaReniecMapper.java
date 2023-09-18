package org.acme.mapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.acme.bean.ConsultaReniec;

public class ConsultaReniecMapper {

    public ConsultaReniec fromString(String texto){

        ConsultaReniec consulta=null;
        String regex = "^(.{9})(.{10}).*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        
        if (matcher.find()) {
            consulta=new ConsultaReniec();
            consulta.nroDoc=matcher.group(1).trim();
            String fechaString = matcher.group(2);
            
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                consulta.fNacimiento=dateFormat.parse(fechaString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return consulta;
    }    
}
