package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){}

    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

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
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);
    }

    @Test
    void testCreateAndDelete(){
        Iterator<Product> productIterator = productRepository.findAll();

        Product product = new Product();
        productRepository.create(product);

        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);


        assertTrue(productIterator.hasNext());
        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateAndEdit(){
        Random randomizer = new Random();
        int randomNumber = randomizer.nextInt(1000);

        Iterator<Product> productIterator = productRepository.findAll();

        // Initializing first object
        Product product1 = new Product();
        productRepository.create(product1);
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        // Object with same id different attribute
        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Lalalalala");
        product2.setProductQuantity(randomNumber);

        productRepository.edit(product2);

        assertEquals(randomNumber, product1.getProductQuantity());
        assertEquals("Lalalalala", product1.getProductName());
    }

    @Test
    void testCreateEditDelete(){
        Random randomizer = new Random();
        int randomNumber = randomizer.nextInt(1000);

        Iterator<Product> productIterator = productRepository.findAll();

        // Initializing first object
        Product product1 = new Product();
        productRepository.create(product1);
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        // Object with same id different attribute
        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Lalalalala");
        product2.setProductQuantity(randomNumber);

        productRepository.edit(product2);

        assertEquals(randomNumber, product1.getProductQuantity());
        assertEquals("Lalalalala", product1.getProductName());

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditIfNotFound(){
        Random randomizer = new Random();
        int randomNumber = randomizer.nextInt(1000);

        Product product1 = new Product();
        productRepository.create(product1);
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        // Object with same id different attribute
        Product product2 = new Product();
        product2.setProductId("");
        product2.setProductName("Lalalalala");
        product2.setProductQuantity(randomNumber);

        productRepository.edit(product2);

        assertNotEquals(randomNumber, product1.getProductQuantity());
        assertNotEquals("Lalalalala", product1.getProductName());
    }

    @Test
    void testDeleteIfNotFound(){
        Iterator<Product> productIterator = productRepository.findAll();

        Product product1 = new Product();
        productRepository.create(product1);
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        productRepository.delete("-");
        assertTrue(productIterator.hasNext());

    }
}
