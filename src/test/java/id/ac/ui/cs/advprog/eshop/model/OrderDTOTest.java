package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class OrderDTOTest {
    private List<Product> products;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateDTO(){
        OrderDTO dto = new OrderDTO();
        assertNotNull(dto);
    }

    @Test
    void testProducts(){
        OrderDTO dto = new OrderDTO();
        dto.setProducts(products);
        assertSame(products, dto.getProducts());
    }

    @Test
    void testAddProducts(){
        OrderDTO dto = new OrderDTO();
        dto.setProducts(products);
        dto.addProduct(products.get(0));
        products.add(products.get(0));
        assertSame(products, dto.getProducts());
    }

    @Test
    void testOwner(){
        OrderDTO dto = new OrderDTO();
        dto.setOwner("aa");
        assertEquals("aa", dto.getOwner());
    }
}
