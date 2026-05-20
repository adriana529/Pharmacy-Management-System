<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout Pembayaran</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light p-5">

<div class="container" style="max-width: 500px;">
    <div class="card shadow-sm p-4">
        <h3 class="mb-4 text-center">Maklumat Pembayaran</h3>
        
        <form action="PaymentController" method="POST">
            <input type="hidden" name="purchaseId" value="PUR-99821"> 

            <div class="mb-3">
                <label class="form-label">Nama Penuh</label>
                <input type="text" name="billName" class="form-control" placeholder="Ahmad Ali" required>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Emel</label>
                <input type="email" name="billEmail" class="form-control" placeholder="ahmad@email.com" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Amaun (RM)</label>
                <input type="number" step="0.01" name="amount" class="form-control" value="10.00" readonly>
            </div>

            <button type="submit" class="btn btn-primary w-100">Bayar via toyyibPay</button>
        </form>
    </div>
</div>

</body>
</html>