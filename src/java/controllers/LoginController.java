/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Branch;
import model.User;
import services.Authenticator;
import services.UserDetails;
import services.branchService;
import services.userService;

/**
 *
 * @author it177479
 */
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //initializing the logger
    static Logger log = Logger.getLogger(LoginController.class.getName());
    
    
    
   //logging in different levels
//    log.trace("This is a Trace");
//    log.debug("This is a Debug");
//    log.info("This is an Info");
//    log.warn("This is a Warn");
//    log.error("This is an Error");
//    log.fatal("This is a Fatal");
 
 
 
    public LoginController() {
        super();
    }
    
    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
            
		String username = request.getParameter("username").toUpperCase();
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
                      
               
		Authenticator authenticator = new Authenticator();
                userService us1 = new userService();
                
                // edited to simple login without check
		//String result = authenticator.authenticate(username, password);
                String result = "success";
                String statUser = null;
                
                  
                if (result.equals("success")){

                    try {
                        statUser = us1.getUserStatus(username);
                        
                        if (statUser.equals("A") && !statUser.equals(null)){
                            
                            User user = new User(username);
                            Branch branch = new Branch();
                   
                            try {
                                branchService bs = new branchService();
                                branch = bs.getBranch(username);
                                
                            } catch (Exception ex) {
                                
                                log.warning("Exception"+ex);
                                //Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            log.info("user "+username+" loggedin succesfully");
                            //request.getSession().setAttribute("validate", "logged");
                            UserDetails ud = new UserDetails(user, branch);
                            rd = request.getRequestDispatcher("dashboard.jsp");
                        }else if(statUser.equals("D")){
                            log.info("user "+username+" loggin failed deactivated user");
                            request.setAttribute("validationMessage", "Unregistered user");
                            rd = request.getRequestDispatcher("index.jsp");
                        
                        }
                        
                    } catch (Exception ex) {
                        
                        log.warning("Exception"+ex);
                        //Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    request.setAttribute("validationMessage", "Invalidusernamepassword");
                    
                    log.info("user "+username+" loggin failed invalid username/pasword");
                    rd = request.getRequestDispatcher("index.jsp");
                }
                rd.forward(request, response);
       
    }
    
    
         
}
                                            