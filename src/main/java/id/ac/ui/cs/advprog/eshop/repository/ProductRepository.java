package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import javax.management.openmbean.InvalidKeyException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        product.setProductId(String.valueOf(UUID.randomUUID()));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public boolean edit(Product product){
        int index = findIndex(product.getProductId());

        if (index==-1) return false;
        else{
            Product existingProduct = productData.get(index);
            existingProduct.setProductQuantity(product.getProductQuantity());
            existingProduct.setProductName(product.getProductName());
            return true;
        }
    }

    public int findIndex(String productId){
        int index=0;
        for (Product productDatum : productData) {
            if (productDatum.getProductId().equals(productId)) {
                return index;
            }
            index += 1;
        }
        return -1;
    }
    public Product getProduct(String productId) throws InvalidKeyException{
        int index=findIndex(productId);
        if (index==-1) throw new InvalidKeyException();
        else {
            return productData.get(index);
        }
    }
}
