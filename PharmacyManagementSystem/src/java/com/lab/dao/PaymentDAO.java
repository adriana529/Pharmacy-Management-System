package com.lab.dao;

import java.sql.*;
import com.lab.model.Payment;

public class PaymentDAO {
    // Database configuration - Ensure these match your environment
    private String url = "jdbc:mysql://localhost:3306/pharmacydata";
    private String user = "admin";
    private String password = "admin";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found!", e);
        }
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Persists a new Payment record to the database.
     * @param payment The Payment model object containing transaction details.
     * @return true if the insertion was successful, false otherwise.
     */
    public boolean savePayment(Payment payment) {
        String sql = "INSERT INTO payments (purchaseID, amount, paymentMethod, status, billCode) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, payment.getPurchaseID());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getPaymentMethod());
            stmt.setString(4, payment.getStatus());
            stmt.setString(5, payment.getBillCode());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Database Error during savePayment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the status of a payment record based on the ToyyibPay BillCode.
     * @param billCode The unique identifier returned by ToyyibPay.
     * @param status The new status (e.g., "SUCCESS", "FAILED").
     */
    public void updatePaymentStatus(String billCode, String status) {
        String sql = "UPDATE payments SET status = ? WHERE billCode = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            stmt.setString(2, billCode);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Database Error during updatePaymentStatus: " + e.getMessage());
            e.printStackTrace();
        }
    }
}