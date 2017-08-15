/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import model.ArticleDetails;
import model.CustomerDetails;
import model.TicketDetails;

/**
 *
 * @author it177479
 */
public class CustomerDetailsService {
    
    
    public CustomerDetails getCustomerDetails(String customerId) throws Exception{
        
        CustomerDetails cd = new CustomerDetails();
        String result = null;
        
        Connection conn = null;
        connection newConn = new connection();
        Properties prop = new Properties(); 
                   
        conn = newConn.connectAs400DB(5,"","");

        if (!conn.isClosed()){
             
            // load a properties file
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
            Statement st2 = conn.createStatement();
               
            String sql1 = "SELECT * FROM "
                        + prop.getProperty("DATA_LIB")+"/PWTMCUS1 "
                        + "WHERE MCUSCUCD="+customerId.trim();
            
            //System.out.println("sql:---"+sql1);
            
            ResultSet rs2 = st2.executeQuery(sql1);


            if (rs2.next()) {
               //result = rs2.getString("usbstat");
              try {
                    
                cd.setIdnumber(rs2.getString("MCUSNICN"));
                cd.setSalutation(rs2.getString("MCUSSALU"));
                cd.setInitials(rs2.getString("MCUSFNAM"));
                cd.setFirstname(rs2.getString("MCUSFNAM"));
                cd.setLastname(rs2.getString("MCUSLNAM"));
               
               } catch (Exception e) {
                   System.out.println(e);
               }
              
           }
        }
        
        return cd;
    }    
}
