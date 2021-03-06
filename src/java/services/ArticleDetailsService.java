/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import model.ArticleDetails;
import model.TicketDetails;

/**
 *
 * @author it177479
 */
public class ArticleDetailsService {
    
    
    public String getArticleDetails(String ticketNo) throws Exception{
        
        
        List<ArticleDetails> adlist = new ArrayList<ArticleDetails>();
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
                        + prop.getProperty("DATA_LIB")+"/PWTMTKA1 a,"+prop.getProperty("DATA_LIB")+"/PWTMART1 aty "
                        + "WHERE MTKANUMB='"+ticketNo+"' AND a.MTKATYCD = aty.MARTTYCD ";
            
            //System.out.println("sql:---"+sql1);
            
            ResultSet rs2 = st2.executeQuery(sql1);
    
            

            int i = 0;
                //while(rs2.next()) {
                //    i++;
                //}


             //ad = new ArticleDetails[5];
             
             int counter = 0;
            while (rs2.next()) {
               //result = rs2.getString("usbstat");
                ArticleDetails ad = null;
              try {
               ad = new ArticleDetails();     
            //  System.out.println("Desc : "+rs2.getString("MARTDESC"));  
               ad.setArticletypecode(rs2.getString("MTKATYCD"));
               ad.setNoofitems(rs2.getString("MTKANOIT"));
               ad.setAssessedvalue(rs2.getString("MTKAASVL"));
               ad.setCaretage(rs2.getString("MTKACRTG"));
               ad.setGrossweight(rs2.getString("MTKAGRWT"));
               ad.setNetweight(rs2.getString("MTKANTWT"));
               ad.setTypedescription(rs2.getString("MARTDESC"));
               
              
               
               } catch (Exception e) {
                   System.out.println(e);
               }
              //counter++; 
              
              adlist.add(ad);
           }
            
            
        }
        
        Gson gson = new Gson();
        String retobj = gson.toJson(adlist);
        
        System.out.println(retobj );
        
        return retobj;
    }    
}
