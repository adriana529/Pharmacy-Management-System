/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/PaymentController"})
public class PaymentController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        // 1. Ambil data dari form
        String purchaseId = request.getParameter("purchaseId");
        String billName = request.getParameter("billName");
        String billEmail = request.getParameter("billEmail");
        String amount = request.getParameter("amount");

        // Tukar RM ke SEN untuk toyyibPay
        double amountDouble = Double.parseDouble(amount) * 100;
        int amountSen = (int) amountDouble;

        // 2. Simpan rekod awal ke Database (Store Layer)
        // PaymentDAO.saveInitialPayment(purchaseId, amount);
        // 3. Panggil API toyyibPay (Sandbox)
        String userSecretKey = "SALIN_SECRET_KEY_ANDA";
        String categoryCode = "SALIN_CATEGORY_CODE_ANDA";

        try {
            String url = "https://staging.toyyibpay.com/index.php/api/createBill";
            String params = "userSecretKey=" + userSecretKey
                    + "&categoryCode=" + categoryCode
                    + "&billName=" + billName
                    + "&billDescription=Payment for " + purchaseId
                    + "&billPriceSetting=1"
                    + "&billPayorInfo=1"
                    + "&billAmount=" + amountSen
                    + "&billReturnUrl=http://localhost:8080/app/return.jsp"
                    + "&billCallbackUrl=http://domain.com/callback"
                    + "&billExternalReferenceNo=" + purchaseId
                    + "&billTo=" + billName
                    + "&billEmail=" + billEmail
                    + "&billPhone=0123456789";

            // Gunakan library seperti OkHttp atau HttpURLConnection untuk hantar POST
            // Selepas dapat BillCode, redirect user:
            // response.sendRedirect("https://staging.toyyibpay.com/" + billCode);
        } catch (Exception e) {
            e.printStackTrace();
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
