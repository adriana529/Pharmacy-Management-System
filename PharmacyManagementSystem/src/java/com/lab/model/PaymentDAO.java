package com.lab.model;
public class PaymentDAO {
    
    private int paymentID;
    private String purchaseID;
    private String paymentDate;
    private double amount;
    private String paymentMethod;
    private String status;

    // Constructor, Getter, dan Setter
    PaymentDAO(){};
    
    public void setPaymentID(int paymentID){
        this.paymentID = paymentID;
    }
    
    public int getPaymentID(){
        return paymentID;
    }
    
     public void setPurchaseID(String purchaseID){
        this.purchaseID = purchaseID;
    }
    
    public String getPurchaseID(){
        return purchaseID;
    }
    
     public void setPaymentDate(String paymentDate){
        this.paymentDate = paymentDate;
    }
    
    public String getPaymentDate(){
        return paymentDate;
    }
    
     public void setAmount(double amount){
        this.amount = amount;
    }
    
    public double getAmount(){
        return amount;
    }
    
     public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    
    public String getPaymentMethod(){
        return paymentMethod;
    }
    
     public void setStatus(String status){
        this.status = status;
    }
    
    public String getStatus(){
        return status;
    }
}
