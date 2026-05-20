<%@ page import="com.lab.dao.PaymentDAO" %>
<%
    // 1. Get the status parameters from ToyyibPay
    String status = request.getParameter("status_id"); // 1 = success, 0 = fail/pending
    String billCode = request.getParameter("billcode");

    if (status != null && billCode != null) {
        PaymentDAO dao = new PaymentDAO();
        
        // 2. Logic to update status in DB
        if (status.equals("1")) {
            dao.updatePaymentStatus(billCode, "SUCCESS");
%>
            <div style="text-align:center; margin-top:50px;">
                <h1>Payment Successful!</h1>
                <p>Thank you for your purchase. Your transaction ID is: <%= billCode %></p>
                <a href="Dashboard.jsp">Back to Home</a>
            </div>
<%
        } else {
            dao.updatePaymentStatus(billCode, "FAILED");
%>
            <div style="text-align:center; margin-top:50px;">
                <h1>Payment Failed</h1>
                <p>Please try again or contact support.</p>
                <a href="Payment.jsp">Retry Payment</a>
            </div>
<%
        }
    } else {
        out.println("Invalid Request.");
    }
%>