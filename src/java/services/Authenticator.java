/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author it177479
 */
public class Authenticator {
    
    public String authenticate(String username, String password) {
        
        Connection conn = null;
        connection newConn = new connection();
        String result = "failure";
        SimpleLdapAuthentication slap = new SimpleLdapAuthentication();
        
              
        try {
//               conn = newConn.connectAs400DB(1,username,password);
//                if ( conn != null) {
//                    result =  "success";
//                } else {
//                        result =  "failure";
//                }

                if (slap.authenticateLdap(username, password).equals("ok")){
                    result = "success";
                }else{
                    result =  "failure";
                }

        }
        catch (Exception ex) {
            System.out.println("Invalied User");
        }
        
        return result;
    }
    
}
