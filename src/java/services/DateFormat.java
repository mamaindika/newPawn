/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author boc
 */
public class DateFormat {
    
public String getDateFormat(int Date){
        String date = Integer.toString(Date);
        String Year  = date.substring(0,4);
        String Month = date.substring(4,6);
        String Day = date.substring(6,8);
        String DateFormat = Year+"/"+Month+"/"+Day;

return DateFormat;
}
public String getDateFormat(String date){
        date = "20111221";
        String Year  = date.substring(0,4);
        String Month = date.substring(4,6);
        String Day = date.substring(6,8);
        String DateFormat = Year+"/"+Month+"/"+Day;

return DateFormat;
}
    
}
