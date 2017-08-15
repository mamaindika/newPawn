/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import model.TicketDetails;

/**
 *
 * @author it177479
 */
public class InterestDue {
    
    
    public int getInterestDue(String branchSequence , String dateGranted) throws Exception{
        
       TicketDetails td = new TicketDetails();
        String result = null;
        int noOfDays = 0;
      
        Connection conn = null;
        connection newConn = new connection();
        Properties prop = new Properties(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                   
         conn = newConn.connectAs400DB(5,"","");

        if (!conn.isClosed()){
            
            // load a properties file
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
            Statement st2 = conn.createStatement();

             String sql2 = "SELECT * FROM "
                       + "PAWNLIB"+"/PWTMBRN1 "
                        + "WHERE MBRNSEQN='"+branchSequence+"'";
             
             
            
             ResultSet rs2 = st2.executeQuery(sql2);
             
            String lastWorkingDate = null;
            
             if(rs2.next()){
                 
                 lastWorkingDate = rs2.getString("MBRNLTWD");
  
             }
             

             Date lwd = formatter.parse(lastWorkingDate);
             Date granted = formatter.parse(dateGranted);
             
             //System.out.println("Dete diff : "+getTimeDiff(lwd, granted));
             noOfDays = Integer.parseInt(getTimeDiff(lwd, granted));
        }
        
      
        
         conn.close();
        return noOfDays ;
       
    }  
    
    public String getTimeDiff(Date dateOne, Date dateTwo) {       
            String diff = "";        
            long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());        
            diff = String.format("%d hour(s) %d min(s)", TimeUnit.MILLISECONDS.toHours(timeDiff),TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));        
             
            long nodays = TimeUnit.MILLISECONDS.toHours(timeDiff)/24;
            //int nodays = Integer.parseInt(diff) / 24;
             
            
            return String.valueOf(nodays);
            //return diff;
    }
}
