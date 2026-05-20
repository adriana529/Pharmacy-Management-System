import java.sql.*;

public class PaymentDAO {
    private String url = "jdbc:mysql://localhost:3306/db_anda";
    private String user = "root";
    private String pass = "";

    // Fungsi untuk simpan rekod pembayaran baru (Status: PENDING)
    public void insertPayment(String purchaseId, double amount) {
        String sql = "INSERT INTO Payment (PurchaseID, Amount, Status) VALUES (?, ?, 'PENDING')";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, purchaseId);
            ps.setDouble(2, amount);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk kemaskini status selepas bayaran berjaya (Status: SUCCESS)
    public void updatePaymentStatus(String purchaseId, String method) {
        String sql = "UPDATE Payment SET PaymentDate = NOW(), Status = 'SUCCESS', PaymentMethod = ? WHERE PurchaseID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, method);
            ps.setString(2, purchaseId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}