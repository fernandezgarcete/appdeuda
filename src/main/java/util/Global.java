package util;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;
import java.util.Calendar;
/**
 *
 * @author Administrador
 */
public class Global {
    public static Date getDate(String fecha){

            Date f = null;
            String dia;
            String mes;
            String year;
            int ini;
            int fin;
        
            if (fecha.contains("/")) {
                ini = fecha.indexOf("/");
                fin = fecha.lastIndexOf("/");
                dia = fecha.substring(0, ini);
                mes = fecha.substring(ini+1, fin);
                year = fecha.substring(fin+1, fecha.length());
                
                try {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, Integer.parseInt(year));
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
                    calendar.set(Calendar.MONTH, Integer.parseInt(mes));
                    
                    f = new Date(calendar.getTime().getTime());
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage(),e);
                }
                return f; 
            }
            if (fecha.contains("-")) {
                ini = fecha.indexOf("-");
                fin = fecha.lastIndexOf("-");
                dia = fecha.substring(0, ini);
                mes = fecha.substring(ini, fin);
                year = fecha.substring(fin, fecha.length());
                
                try {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, Integer.parseInt(year));
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
                    calendar.set(Calendar.MONTH, Integer.parseInt(mes));
                    
                    f = new Date(calendar.getTime().getTime());
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage(),e);
                }
                return f; 
            }
        return null;
    }
}
