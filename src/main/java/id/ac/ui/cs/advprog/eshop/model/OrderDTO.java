package id.ac.ui.cs.advprog.eshop.model;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private List<Product> products;
    private String owner;

    public OrderDTO(){
        products = new ArrayList<>();
    }
    public void addProduct(Product p) {
        this.products.add(p);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
