package com.goutam.razorpayPaymentGateway.model;;

public class PaymentDesc {

    private int amount;
    private String receipt;
    private String currency;

    public int getAmount() {
        return amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public String getCurrency() {
        return currency;
    }
}
