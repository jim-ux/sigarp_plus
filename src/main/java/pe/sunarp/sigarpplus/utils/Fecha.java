package pe.sunarp.sigarpplus.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {

    public static String formatDate(Date rawDate){

        if(rawDate != null){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(rawDate);
        }

        return "";


    }
}
