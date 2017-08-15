/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Branch;

/**
 *
 * @author it177479
 */
public class branchService {
    
    
    public Branch getBranch(String username) throws SQLException, Exception{
        
        Branch branch = new Branch();
        
        Connection conn = null;
        connection newConn = new connection();
        Properties prop = new Properties(); 
                   
        conn = newConn.connectAs400DB(5,"","");

        if (conn != null){
            
            // load a properties file
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
            Statement st2 = conn.createStatement();
               
            String sql1 = "SELECT brnseqn,brncode,brnname,brnclcd,MBRNRNST,MBRNLTWD,MBRNNXDT FROM "
                        + prop.getProperty("DATA_LIB")+"/pwpusb01,"
                        + prop.getProperty("DATA_LIB")+"/pwpbrn01,"
                        + prop.getProperty("DATA_LIB")+"/PWTMBRN1 "
                        + "WHERE usbprf='"+username+"' "
                        + "AND usbbrsq=brnseqn AND brnseqn = MBRNSEQN";
            
            System.out.println("sql:---"+sql1);
            
            ResultSet rs2 = st2.executeQuery(sql1);
    
            if (rs2.next()) {
                
                branch.setCode(rs2.getInt("brncode"));
                branch.setLastWorkDate(rs2.getInt("MBRNLTWD"));
                branch.setName(rs2.getString("brnname"));
                branch.setNextWorkDate(rs2.getInt("MBRNNXDT"));
                branch.setRunningStatus(rs2.getInt("MBRNRNST"));
                branch.setSequence(rs2.getInt("brnseqn"));
                branch.setCalenderprofile(rs2.getInt("brnclcd"));
          
            }
            
        }
        conn.close();
        return branch;
    }
    
    public Branch getBranch(int branchSequence) throws SQLException, Exception{
               
               
        Branch branch = new Branch();
        
        Connection conn = null;
        connection newConn = new connection();
        Properties prop = new Properties(); 
                   
        conn = newConn.connectAs400DB(5,"","");

        if (conn != null){
            
            // load a properties file
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
            Statement st2 = conn.createStatement();
               
            String sql1 = "SELECT brnseqn,brncode,brnname,brnclcd,MBRNRNST,MBRNLTWD,MBRNNXDT FROM "
                        + prop.getProperty("DATA_LIB")+"/pwpbrn01,"
                        + prop.getProperty("DATA_LIB")+"/PWTMBRN1 "
                        + "WHERE MBRNSEQN='"+branchSequence+"' "
                        + "AND brnseqn = MBRNSEQN";
                        
            
            System.out.println("sql:---"+sql1);
            
            ResultSet rs2 = st2.executeQuery(sql1);
    
            if (rs2.next()) {
                
                branch.setCode(rs2.getInt("brncode"));
                branch.setLastWorkDate(rs2.getInt("MBRNLTWD"));
                branch.setName(rs2.getString("brnname"));
                branch.setNextWorkDate(rs2.getInt("MBRNNXDT"));
                branch.setRunningStatus(rs2.getInt("MBRNRNST"));
                branch.setSequence(rs2.getInt("brnseqn"));
                branch.setCalenderprofile(rs2.getInt("brnclcd"));
          
            }
        }
         
        conn.close();
        return branch;
        
    }
    
    
    public int getNextDate(Branch branch) throws SQLException, Exception{
        
        //Date nextDate;
        int lstDate = branch.getLastWorkDate();
        int clndPrf = branch.getCalenderprofile();
        
        System.out.println("lst date---"+lstDate);
        int tempDate = lstDate;
        boolean var = true;
        int nextDtInt;
        
        java.util.Date utilStartDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(String.valueOf(tempDate));
         
        
        do{
           
            //System.out.println("temp date to check---:"+utilStartDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(utilStartDate);
            cal.add(Calendar.DATE, 1); //minus number would decrement the days
            
            utilStartDate = cal.getTime();

            System.out.println("next date to check---:"+utilStartDate);

            String yearString = new SimpleDateFormat("yyyy").format(utilStartDate);
            String monthString = new SimpleDateFormat("MM").format(utilStartDate);
            String dayString = new SimpleDateFormat("dd").format(utilStartDate);

            nextDtInt = Integer.parseInt(yearString+monthString+dayString);


            Connection conn = null;
            connection newConn = new connection();
            Properties prop = new Properties(); 

            conn = newConn.connectAs400DB(5,"","");

            if (conn != null){

                // load a properties file
                prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
                Statement st2 = conn.createStatement();

                String sql1 = "SELECT * FROM "
                            + prop.getProperty("DATA_LIB")+"/pwpcls01 "
                            + "WHERE clspkey='"+clndPrf+"' "
                            + "AND clsdate ='"+ nextDtInt + "'";


                System.out.println("sql:---"+sql1);

                ResultSet rs2 = st2.executeQuery(sql1);

                if (rs2.next() && rs2.getInt("CLSHLST") == 1) {
                    var = true;
                    System.out.println("holiday:"+nextDtInt);
                }
                else{
                    System.out.println("next Business day:"+nextDtInt);
                    var = false;
                    conn.close();
                    break;
                                       
                }
            }
        }while(var == true);
         
       return nextDtInt;
    }
}
