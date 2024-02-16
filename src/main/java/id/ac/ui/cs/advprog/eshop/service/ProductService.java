package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import javax.management.openmbean.InvalidKeyException;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    boolean update(Product product);
    Product findById(String productId) throws InvalidKeyException;
    boolean deleteProductById(String productId);
}
