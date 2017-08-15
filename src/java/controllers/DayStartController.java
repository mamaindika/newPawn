/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Branch;
import model.User;
import services.UserDetails;
import services.branchDayProcess;
import services.branchService;

/**
 *
 * @author boc
 */
public class DayStartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        int Sequence = Integer.parseInt(request.getParameter("Sequence"));
        String branchname = request.getParameter("branchname");
        String username = request.getParameter("username").toUpperCase();
        User user = new User(username);
        Branch branch = new Branch();
        branchService bs = new branchService();
        branch = bs.getBranch(username);
        branchDayProcess bdp = new branchDayProcess();
        String result  = bdp.dayStart(Sequence);
        UserDetails ud = new  UserDetails(user,branch);
       
        
        if(result.equals("success")){
            request.setAttribute("validationMessage", "DayStartSuccess");
            RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
            rd.forward(request, response);
            
        }
        else if(result.equals("fail")){
            request.setAttribute("validationMessage", "DayStartFail");
            RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DayStartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DayStartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
