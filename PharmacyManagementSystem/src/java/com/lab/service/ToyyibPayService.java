package com.lab.service;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ToyyibPayService {
    private static final String API_URL = "https://toyyibpay.com/index.php/api/createBill";
    private static final String SECRET_KEY = "3lu4wf3t-z61g-d8kc-g2tv-dpy2jofi4nvy"; // REPLACE WITH YOUR SANDBOX KEY
    private static final String CATEGORY_CODE = "oil0wv33"; // REPLACE WITH YOUR CODE

    public String createBill(String amount, String desc) throws Exception {
        String amountInCents = String.valueOf((int)(Double.parseDouble(amount) * 100));
        String urlParameters = "userSecretKey=" + SECRET_KEY + "&categoryCode=" + CATEGORY_CODE + 
                               "&billName=Payment&billDescription=" + desc + "&billAmount=" + amountInCents +
                               "&billReturnUrl=http://localhost:8080/YourApp/PaymentSuccess.jsp";

        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(urlParameters.getBytes(StandardCharsets.UTF_8));
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String response = br.readLine(); // Simplified extraction for demonstration
            String billCode = response.split("\"BillCode\":\"")[1].split("\"")[0];
            return "https://toyyibpay.com/" + billCode;
        }
    }
}