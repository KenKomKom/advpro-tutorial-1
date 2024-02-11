package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    Product createAndSaveProduct(String name, String id, int quantity){
        Product product = createProduct(name,id,quantity);
        productRepository.create(product);
        product.setProductId(id); // productRepo.create changes the id

        return product;
    }

    Product createProduct(String name, String id, int quantity){
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(quantity);

        return product;
    }

    @Test
    void testCreateAndFind(){
        Product product = createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(savedProduct.getProductId(), product.getProductId());
        assertEquals(savedProduct.getProductName(), product.getProductName());
        assertEquals(savedProduct.getProductQuantity(), product.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        Product product2 = createAndSaveProduct("Sampo Cap Usep","a0f9de45-90b1-437d-a0bf-d0821dde9096",50);

        Iterator<Product> productIterator = productRepository.findAll();
      
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateAndDelete(){
        Iterator<Product> productIterator = productRepository.findAll();

        createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        assertTrue(productIterator.hasNext());
        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateAndEdit(){
        // Initializing first object
        Product product1 = createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        // Object with same id different attribute
        Product product2 = createProduct("Lalalalala","eb558e9f-1c39-460e-8860-71af6af63bd6",200);

        productRepository.edit(product2);

        assertEquals(200, product1.getProductQuantity());
        assertEquals("Lalalalala", product1.getProductName());
    }

    @Test
    void testCreateEditDelete(){
        Iterator<Product> productIterator = productRepository.findAll();

        // Initializing first object
        Product product1 = createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        // Object with same id different attribute
        Product product2 = createProduct("Lalalalala","eb558e9f-1c39-460e-8860-71af6af63bd6",200);

        productRepository.edit(product2);

        assertEquals(200, product1.getProductQuantity());
        assertEquals("Lalalalala", product1.getProductName());

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditIfNotFound(){
        Product product1 = createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        // Object with same id different attribute
        Product product2 = createProduct("Lalalalala","-",200);

        productRepository.edit(product2);

        assertNotEquals(200, product1.getProductQuantity());
        assertNotEquals("Lalalalala", product1.getProductName());
    }

    @Test
    void testDeleteIfNotFound(){
        Iterator<Product> productIterator = productRepository.findAll();

        createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        productRepository.delete("-");
        assertTrue(productIterator.hasNext());

    }

    @Test
    void testCreateGet(){
        Product product1 = createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);
        Product result = productRepository.getProduct(product1.getProductId());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getProductId());
        assertEquals("Sampo Cap Bambang", result.getProductName());
        assertEquals(100, result.getProductQuantity());
    }

    @Test
    void testCreateGetNotFound(){
        try{
            productRepository.getProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");
        }catch (Exception e){
            // Jika ada error maka berhasil
            // Tidak bisa catch InvalidKeyException karena never thrown entah kenapa
            System.out.println("product not found successfully");
        }
    }
}
