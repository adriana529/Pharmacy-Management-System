<%@ page import="com.lab.dao.PaymentDAO, com.lab.model.Payment" %>
<%
    // 1. Receive data from the form
    String amountStr = request.getParameter("amount");
    String method = request.getParameter("paymentMethod");
    String purchaseID = "INV-" + System.currentTimeMillis();

    // 2. Create the Model object (using constructor)
    Payment payment = new Payment(purchaseID, Double.parseDouble(amountStr), method);

    // 3. Save via DAO
    PaymentDAO dao = new PaymentDAO();
    boolean saved = dao.savePayment(payment);

    if (saved) {
        // 4. API Logic (ToyyibPay)
        // ... call your API here ...
        out.println("<h2>Payment saved! Redirecting to ToyyibPay...</h2>");
    } else {
        out.println("<h2>Database Error!</h2>");
    }
%>