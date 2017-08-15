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
import model.Branch;

/**
 *
 * @author it177479
 */
public class branchDayProcess {
    
    public static String dayStart(int branchSeq) throws Exception{
     
        branchService bs = new branchService();
        Branch branch = bs.getBranch(branchSeq);
        String result ="";
        
        Connection conn = null;
        connection newConn = new connection();
        Properties prop = new Properties(); 
                   
        conn = newConn.connectAs400DB(5,"","");

        if (conn != null){
            
            // load a properties file
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
            Statement st2 = conn.createStatement();
               
            String sql1 = "UPDATE "+ prop.getProperty("DATA_LIB")+"/PWTMBRN1 "
                        + "SET MBRNRNST='1' , MBRNLTWD='"+branch.getNextWorkDate()+"' "
                        + "WHERE MBRNSEQN='"+branch.getSequence()+"' ";
                                                
            
            System.out.println("sql:---"+sql1);
            
            st2.executeUpdate(sql1);
            result = "success";
        }else{
           result  = "fail";
        }
        conn.close();
        return result;
    
    }
    
    
    public static String dayEnd(int branchSeq) throws Exception{
     
        branchService bs = new branchService();
        Branch branch = bs.getBranch(branchSeq);
        String result ="";
        
        Connection conn = null;
        connection newConn = new connection();
        Properties prop = new Properties(); 
                   
        conn = newConn.connectAs400DB(5,"","");

        if (conn != null){
            
            // load a properties file
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
            Statement st2 = conn.createStatement();
               
            String sql1 = "UPDATE "+ prop.getProperty("DATA_LIB")+"/PWTMBRN1 "
                        + "SET MBRNRNST='9' , MBRNNXDT='"+bs.getNextDate(branch)+"' "
                        + "WHERE MBRNSEQN='"+branch.getSequence()+"' ";
                                                
            
            System.out.println("sql:---"+sql1);
            
            st2.executeUpdate(sql1);
            result = "success";
        }else{
            result  = "fail";
        }
        conn.close();
        return result;
    }
}
