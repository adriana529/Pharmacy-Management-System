import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Ambil input dari checkout.jsp
        String purchaseId = request.getParameter("purchaseId");
        String billName = request.getParameter("billName");
        String billEmail = request.getParameter("billEmail");
        double amount = Double.parseDouble(request.getParameter("amount"));
        int amountSen = (int) (amount * 100); // Tukar ke SEN

        // 2. Simpan rekod awal ke DB menggunakan DAO
        PaymentDAO dao = new PaymentDAO();
        dao.insertPayment(purchaseId, amount);

        // 3. Persediaan Data untuk API toyyibPay
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("userSecretKey", "SALIN_SECRET_KEY_SANDBOX_ANDA");
        params.put("categoryCode", "SALIN_CATEGORY_CODE_SANDBOX_ANDA");
        params.put("billName", billName);
        params.put("billDescription", "Pembayaran Invois " + purchaseId);
        params.put("billPriceSetting", 1);
        params.put("billPayorInfo", 1);
        params.put("billAmount", amountSen);
        params.put("billReturnUrl", "http://localhost:8080/projek-anda/return.jsp");
        params.put("billCallbackUrl", "http://domain.com/callback"); // Gunakan domain live untuk callback
        params.put("billExternalReferenceNo", purchaseId);
        params.put("billTo", billName);
        params.put("billEmail", billEmail);
        params.put("billPhone", "0123456789");

        // 4. Proses Hantar POST Request (Simulasi cURL di Java)
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        URL url = new URL("https://staging.toyyibpay.com/index.php/api/createBill");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        
        byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);
        conn.getOutputStream().write(postDataBytes);

        // 5. Baca Respons daripada toyyibPay
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        
        String apiResponse = sb.toString(); // Ini adalah JSON string

        // 6. Ekstrak BillCode & Redirect
        // Untuk tutorial ringkas, kita guna manual parsing jika tidak guna library JSON
        if (apiResponse.contains("BillCode")) {
            // Contoh respons: [{"BillCode":"8x9y1z2"}]
            String billCode = apiResponse.split("\"BillCode\":\"")[1].split("\"")[0];
            
            // Simpan BillCode ke DB melalui DAO jika perlu
            // dao.updateBillCode(purchaseId, billCode);

            // Redirect pengguna ke portal bayaran
            response.sendRedirect("https://staging.toyyibpay.com/" + billCode);
        } else {
            response.getWriter().println("Ralat API: " + apiResponse);
        }
    }
}