/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author it177479
 */
public class connection {
    
    /**
     * this method is used for connect AS400 DB2
     * it uses jt400.jar library
     * input connection Type type as integer  1==login && 5==data
     * 
     * @return
     * @throws Exception 
     */
    public static Connection connectAs400DB(Integer connectionType,String username , String password) throws Exception{
        
       // Connection conn = null;
        Properties prop = new Properties(); 
        Connection result = null;
               
        try{
           
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
             
        
            DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver()); 

                        
            String URL = prop.getProperty("URL_AS400")+prop.getProperty("IP_ADDRESS_SYSTEM")+prop.getProperty("SYSTEM_NAME");
              
            if (connectionType == 1){
                URL = URL + "libraries="+prop.getProperty("LOGIN_LIB")+";prompt=false;";
                result = DriverManager.getConnection(URL,username,password);
                //result = DriverManager.getConnection(URL,"SG177461","testwer");
                
            
            }else if (connectionType == 5){
                URL = URL + "libraries="+prop.getProperty("DATA_LIB")+";prompt=false;";
                result = DriverManager.getConnection(URL,prop.getProperty("USER_NAME_AS400"),prop.getProperty("USER_PASSWORD_AS400"));
            
            }
            
                     
       }catch (Exception ex){
           System.out.println("DB connection to AS400DB2 Error: "+ex);
           result = null;
       }
        
        finally{
        
            return result;
        }
        
    }
    
}
