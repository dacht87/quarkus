package org.acme.mapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.acme.bean.Respuesta;

public class RespuestaMapper {

    public String toString(Respuesta texto){
        
        StringBuilder sb = new StringBuilder();
        sb.append(texto.respuesta);
        return sb.toString();
    }    
}
