package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @MockBean
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl service;

    private List<Product> allProducts;

    @BeforeEach
    void setUp(){
        allProducts = new ArrayList<>();
        createAndSaveProduct("nama","id",1);
    }

    Product createAndSaveProduct(String name, String id, int quantity){
        Product product = createProduct(name,id,quantity);
        service.create(product);
        allProducts.add(product);

        return product;
    }

    @AfterEach
    void deleteProduct(){
        allProducts = null;
    }

    boolean editRepoMock(Product product){
        for(Product datum : allProducts){
            if (datum.getProductId().equals(product.getProductId())){
                datum.setProductName(product.getProductName());
                datum.setProductQuantity(product.getProductQuantity());
                return true;
            }
        }
        return false;
    }

    Product createProduct(String name, String id, int quantity){
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(quantity);

        return product;
    }

    @Test
    void testCreateProduct(){
        createAndSaveProduct("nama","id",1);
        when(productRepository.findAll()).thenReturn(allProducts.iterator());
        List<Product> list = service.findAll();

        assertEquals("id", list.getFirst().getProductId());
        assertEquals("nama", list.getFirst().getProductName());
        assertEquals(1, list.getFirst().getProductQuantity());
    }

    @Test
    void testFindAllProduct(){
        createAndSaveProduct("nomu","uwu",2);
        when(productRepository.findAll()).thenReturn(allProducts.iterator());
        List<Product> list = service.findAll();

        assertEquals("id", list.get(0).getProductId());
        assertEquals("nama", list.get(0).getProductName());
        assertEquals(1, list.get(0).getProductQuantity());
        assertEquals("uwu", list.get(1).getProductId());
        assertEquals("nomu", list.get(1).getProductName());
        assertEquals(2, list.get(1).getProductQuantity());
    }

    @Test
    void testEditGetProduct(){
        Product product2 = createAndSaveProduct("aa", "id2",20);
        Product product3 = createProduct("nomu","id2",2); // Data to edit product2

        when(productRepository.edit(product3)).thenReturn(editRepoMock(product3));
        boolean hasEdit = service.update(product3);
        when(productRepository.getProduct(product2.getProductId())).thenReturn(product2);
        Product resultEdit = service.findById(product2.getProductId());

        assertTrue(hasEdit);
        assertEquals("id2", resultEdit.getProductId());
        assertEquals("nomu", resultEdit.getProductName());
        assertEquals(2, resultEdit.getProductQuantity());
    }

    @Test
    void testCreateDeleteProduct(){
        Product product2 = createAndSaveProduct("aa", "id2",20);
        when(productRepository.delete("id")).thenReturn(allProducts.remove(product2));
        boolean hasDelete = service.deleteProductById("id");

        when(productRepository.findAll()).thenReturn(allProducts.iterator());
        List<Product> list = service.findAll();
        assertTrue(hasDelete);
        assertEquals(1,list.size());
    }
}
