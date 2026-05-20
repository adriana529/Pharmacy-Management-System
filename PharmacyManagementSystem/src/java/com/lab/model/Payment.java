package com.lab.model;

public class Payment {
    private int paymentID;
    private String purchaseID;
    private String paymentDate;
    private double amount;
    private String paymentMethod;
    private String status;
    private String billCode;

    // Empty Constructor
    public Payment() {}

    // Constructor with fields
    public Payment(String purchaseID, double amount, String paymentMethod) {
        this.purchaseID = purchaseID;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "PENDING";
    }

    // Getters and Setters
    public int getPaymentID() { return paymentID; }
    public void setPaymentID(int paymentID) { this.paymentID = paymentID; }

    public String getPurchaseID() { return purchaseID; }
    public void setPurchaseID(String purchaseID) { this.purchaseID = purchaseID; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getBillCode(){
        return billCode;
    }
    
    public void setBillCode(String billCode){
        this.billCode=billCode;
    }
}