/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import model.Branch;
import static jdk.nashorn.internal.objects.NativeMath.round;
import model.TicketDetails;

/**
 *
 * @author it177479
 */
public class TicketDetailsService {
    
    
    public TicketDetails getTicketDetails(String ticketNo) throws Exception{
        
        TicketDetails td = new TicketDetails();
        InterestDue id = new InterestDue();
        String result = null;
        //td=null;
        
        Connection conn = null;
        connection newConn = new connection();
        Properties prop = new Properties(); 
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                   
        conn = newConn.connectAs400DB(5,"","");

        if (!conn.isClosed()){
            
            // load a properties file
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
            Statement st2 = conn.createStatement();
               
            String sql1 = "SELECT * FROM "
                        + prop.getProperty("DATA_LIB")+"/PWTMTKT1 "
                        + "WHERE MTKTNUMB='"+ticketNo+"'";
            
            //System.out.println("sql:---"+sql1);
            
            ResultSet rs2 = st2.executeQuery(sql1);
    
            if (rs2.next()) {
               //result = rs2.getString("usbstat");
              try {
                    
               td.setBranchSequence(rs2.getString("MTKTBRSQ"));
              
               if (rs2.getString("MTKTLNTY").equals("1")){
                   td.setTickettype("LOAN");
               }else{
                   td.setTickettype("OD");
               }
               
               td.setCustomerID(rs2.getString("MTKTCUNO"));
               td.setTicketstatus(rs2.getString("MTKTSTAT"));
               td.setLoanpurposecode(rs2.getString("MTKTLNPU"));
               td.setDateofNPAtransfer(rs2.getString("MTKTNPDT"));
               td.setDategranted(rs2.getString("MTKTDTGR"));
               td.setLasttransactiondate(rs2.getString("MTKTLTXD"));
               td.setInterestrate(rs2.getString("MTKTINTR"));
               td.setPaidInterest(rs2.getString("MTKTINPD"));
               td.setPaidCapital(rs2.getString("MTKTCPPD"));
               td.setMaturitydate(rs2.getString("MTKTMTDT"));
               td.setGrantedamount(rs2.getString("MTKTGRAM"));
               td.setAssessedvalue(rs2.getString("MTKTASVL"));
               td.setPeriod(rs2.getString("MTKTPERD"));
               td.setNumberofarticles(rs2.getString("MTKTNOAR"));
               td.setBalanceamount(rs2.getString("MTKTBLAM"));
               
               
               int noOfDays = id.getInterestDue(td.getBranchSequence(), td.getDategranted());
           
                // System.out.println("balance : "+td.getBalanceamount() +" int rate : "+td.getInterestrate());
                double balance = Float.parseFloat(td.getBalanceamount());

                int intRate = Integer.parseInt(td.getInterestrate());
                //System.out.println("balance : "+balance+" int rate : "+intRate);

                double intrestDue =  ((balance * intRate *noOfDays)/36500) - Double.parseDouble(td.getPaidInterest()) ;
                double intduefinal = 0.00;
                if (intrestDue > 0){
                 intduefinal = Math.ceil(intrestDue);
                }else{
                   intduefinal = 0.00;
                }
                String intdue = String.format("%.2f", intduefinal);

                System.out.println("Intrest Due : "+intdue);

                try{
                      td.setInterestDue(intdue);
                }catch(Exception e){
                      System.out.println(e);

                }

               } catch (Exception e) {
                   System.out.println(e);
               }
           }else{
             td = null;
            }
            
            
            
       
                
        }
         conn.close();
        return td;
       
    } 
    
    
    public String updateTransaction(int ticketNo,double transAmount,String transType) throws Exception{
        
        String result = "";
        Connection conn = null;
        connection newConn = new connection();
        Properties prop = new Properties(); 
        
        conn = newConn.connectAs400DB(5,"","");
        
        TicketDetails td  = getTicketDetails(Integer.toString(ticketNo));
        Branch branch = new Branch();
        branchService bs = new branchService();
        
        branch = bs.getBranch(Integer.parseInt(td.getBranchSequence()));
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
       

        if (!conn.isClosed()){
            
            // load a properties file
            prop.load(connection.class.getResourceAsStream("dbConfig.properties"));
            Statement st2 = conn.createStatement();
            Date date = new Date();   
            String sql1,sql2;
            
            Double intersetDue = Double.parseDouble(td.getInterestDue());
            Double intPaid = 0.00;
            Double capitalPaid = 0.00;
            
            
            if (transAmount > intersetDue){
                intPaid = intersetDue;
                capitalPaid = (transAmount-intPaid);
            }else{
                intPaid = transAmount;
                capitalPaid = 0.00;
            }
            
            sql1 = "INSERT INTO "
                    + prop.getProperty("DATA_LIB")+"/PWTMTRN1 "
                    + "VALUES("
                    + "'"+td.getBranchSequence()+"',"   // branch Sequence
                    + "'"+ticketNo+"',"                 // ticketno
                    + "'"+transType.trim()+"',"           // trans type PP/RD
                    + "'"+branch.getLastWorkDate()+"'," // bussiness date
                    + "'"+transAmount+"',"              // trans amount
                    + "'"+intPaid+"',"              // int amount
                    + "'"+capitalPaid+"',"              // capital amount
                    + "'"+0.00+"',"              // tax amount
                    + "'"+UserDetails.username+ "',"       // created user
                    + "'"+dateFormat.format(date)+"',"                          // system date
                    + "'"+timeFormat.format(date)+"'"                           // system time
                    + ")";
            
            System.out.println("sql1:---"+sql1);
            
            sql2 = "UPDATE "
                    + prop.getProperty("DATA_LIB")+"/PWTMTKT1 "
                    + "SET "
                    + "MTKTLTXD='"+branch.getLastWorkDate()+"',"   // lst Trans Date
                    + "MTKTINPD= MTKTINPD + '"+intPaid+"',"     // interset amount pd
                    + "MTKTCPPD= MTKTCPPD + '"+capitalPaid+"',"       // capital pd
                    + "MTKTBLAM= MTKTBLAM - '"+capitalPaid+"' " // balance
                    + "WHERE MTKTNUMB='"+ticketNo+"'";              // tkt no
            
             System.out.println("sql2:---"+sql2);
            
            // customer obligation update also needs to do
            
            st2.executeUpdate(sql1);
            st2.executeUpdate(sql2);
           
            result = "success";
           }else{
            result = "failed";
        }
           
        return result;
    }
}
