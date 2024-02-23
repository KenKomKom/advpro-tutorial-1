package id.ac.ui.cs.advprog.eshop.controllers;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.openmbean.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllersTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductServiceImpl service;
    @MockBean
    private CarServiceImpl carService;

    @InjectMocks
    private ProductController controller;


    private List<Product> allProducts;

    private  Product mockAddProductToRepository(Product product){
        allProducts.add(product);
        return product;
    }

    private boolean mockEditProductToRepository(Product product){
        for(Product datum : allProducts){
            if(datum.getProductId().equals(product.getProductId())){
                datum.setProductName(product.getProductName());
                datum.setProductQuantity(product.getProductQuantity());
                return true;
            }
        }
        return false;
    }

    Product createAndSaveProduct(){
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("bambang");
        return product;
    }

    @BeforeEach
    void setUp(){
        allProducts = new ArrayList<>();
    }

    @AfterEach
    void deleteRepository(){
        allProducts = null;
    }

    @Test
    public void createPageTest() throws Exception{
        mvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create New Product")));
    }
    @Test
    public void listPageTest() throws Exception{
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")));
    }
    @Test
    public void createProductPostTest() throws Exception{
        Product product = createAndSaveProduct();

        when(service.create(product)).thenReturn(mockAddProductToRepository(product));
        mvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());

        when(service.findAll()).thenReturn(allProducts);
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")))
                .andExpect(content().string(containsString("bambang")));
    }

    @Test
    public void editProductGetTest() throws Exception{

        Product product = createAndSaveProduct();

        when(service.create(product)).thenReturn(mockAddProductToRepository(product));
        mvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());
        when(service.findById(product.getProductId())).thenReturn(product);
        mvc.perform(get("/product/edit/"+product.getProductId()))
                .andExpect(status().isOk()).andExpect(content().string(containsString("Edit Product")));
    }

    @Test
    public void editProductPostTest() throws Exception{

        Product product = createAndSaveProduct();

        when(service.create(product)).thenReturn(mockAddProductToRepository(product));
        mvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());

        Product product2 = new Product();
        product2.setProductId(product.getProductId());
        product2.setProductName("adi");
        when(service.update(product2)).thenReturn(mockEditProductToRepository(product2));
        mvc.perform(post("/product/edit/"+product.getProductId()).flashAttr("product",product2))
                .andExpect(status().is3xxRedirection());

        when(service.findAll()).thenReturn(allProducts);
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")))
                .andExpect(content().string(containsString("adi")));
    }
    @Test
    public void editProductGetNotFoundTest() throws Exception{

        Product product2 = new Product();
        product2.setProductId("some ID");
        product2.setProductName("adi");
        when(service.findById("some ID")).thenThrow(new InvalidKeyException());
        mvc.perform(get("/product/edit/"+product2.getProductId()).flashAttr("product",product2))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void deleteProductGetTest() throws Exception{

        Product product = createAndSaveProduct();

        when(service.create(product)).thenReturn(mockAddProductToRepository(product));
        mvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());

        when(service.findAll()).thenReturn(allProducts);
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")))
                .andExpect(content().string(containsString("bambang")));

        when(service.deleteProductById(product.getProductId())).thenReturn(allProducts.remove(product));
        mvc.perform(post("/product/delete").param("idToBeDelete",product.getProductId()))
                .andExpect(status().is3xxRedirection());

        assertEquals(0, allProducts.size());
    }
}