package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import java.util.Map;

public class PaymentVoucher extends  Payment{
    public PaymentVoucher(Order order, String method, Map<String, String>paymentData){
        super(order, method,paymentData);
    }
    public PaymentVoucher(String id, Order order, String method, Map<String, String>paymentData){
        super(id, order, method,paymentData);
    }
    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        int numOfNumerics = 0;
        for (int i=0; i<paymentData.get("voucherCode").length(); i++){
            if (Character.isDigit(paymentData.get("voucherCode").charAt(i))){
                numOfNumerics+=1;
            }
        }
        if (paymentData.get("voucherCode").length()!=16 ||
                !paymentData.get("voucherCode").startsWith("ESHOP") ||
                numOfNumerics!=8){
            throw new IllegalArgumentException();
        }
        this.paymentData=paymentData;
    }
}
