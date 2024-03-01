package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Builder @Getter
public class Order {
    private String id;
    private List<Product> products;
    private Long orderTime;
    private String author;
    private String status;

    public Order(String id, List<Product> products, Long orderTime, String author){
        this.id = id;
        this.orderTime = orderTime;
        this.author = author;
        this.status= OrderStatus.WAITINGPAYMENT.getValue();

        if (products.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            this.products = products;
        }
    }
    public Order(String id, List<Product> products, Long orderTime, String author, String status){
        this(id, products, orderTime, author);
        setStatus(status);
    }

    public void setStatus(String status){
        if (OrderStatus.contains(status)){
            this.status = status;
        }else{
            throw new IllegalArgumentException();
        }
    }
}