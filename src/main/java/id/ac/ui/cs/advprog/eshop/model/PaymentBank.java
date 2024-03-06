package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import java.util.Map;

public class PaymentBank extends Payment{

    public PaymentBank(Order order, String method, Map<String, String> paymentData){
        super(order, method, paymentData);
    }
    public PaymentBank(String id, Order order, String method, Map<String, String> paymentData){
        super(id, order, method, paymentData);
    }
    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.get("bankName").isBlank() ||
                paymentData.get("referenceCode").isBlank()){
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;
    }
}
