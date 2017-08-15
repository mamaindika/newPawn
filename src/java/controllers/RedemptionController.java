/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerDetails;
import model.TicketDetails;
import services.ArticleDetailsService;
import services.CustomerDetailsService;
import services.TicketDetailsService;

/**
 *
 * @author boc
 */
public class RedemptionController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String TicketNo = request.getParameter("ticketNo");
        
        System.out.println("Ticket No is "+TicketNo);
        request.setAttribute("message", "Hello world");
        
        ArticleDetailsService ads = new ArticleDetailsService();
        String ArticleDetailsObj = ads.getArticleDetails(TicketNo);
        
        TicketDetailsService tds = new TicketDetailsService();
        TicketDetails td = tds.getTicketDetails(TicketNo);
        if(ArticleDetailsObj==null || td==null){
            request.setAttribute("validationMessage", "RedemptionErr");
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        }else{
            String customerId = td.getCustomerID();
            CustomerDetailsService cds = new CustomerDetailsService();
            CustomerDetails cd = cds.getCustomerDetails(customerId);

            request.setAttribute("ArticleDetailsObj", ArticleDetailsObj); 
            request.setAttribute("TicketDetails", td);
            request.setAttribute("TicketNo", TicketNo);
            request.setAttribute("CustomerDetails", cd); 

            RequestDispatcher dispatcher = request.getRequestDispatcher("Redemption.jsp");
            dispatcher.forward(request, response);
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
            Logger.getLogger(RedemptionController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RedemptionController.class.getName()).log(Level.SEVERE, null, ex);
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
