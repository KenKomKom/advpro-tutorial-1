package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import javax.management.openmbean.InvalidKeyException;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public boolean edit(Product product);
    public Product getProduct(String productId) throws InvalidKeyException;
}
