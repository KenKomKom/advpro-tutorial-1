//package id.ac.ui.cs.advprog.eshop.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import id.ac.ui.cs.advprog.eshop.model.Product;
//import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
//import org.mockito.InjectMocks;
//import org.springframework.ui.Model;
//import id.ac.ui.cs.advprog.eshop.controller.ProductController;
//import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc(addFilters = false)
//@WebMvcTest(controllers = ProductController.class)
//public class ProductControllersTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private Model model;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    private ProductServiceImpl service;
//
//    @MockBean
//    ProductRepository productRepository;
//
//    @Autowired
//    private ProductController controller;
//
//    @Test
//    public void homepageTest() throws Exception{
//            mvc.perform(get(""))
//                    .andExpect(status().isOk())
//                    .andExpect(content().string(containsString("uWu")));
//    }
//
//    @Test
//    public void createPageTest() throws Exception{
//        mvc.perform(get("/product/create"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Create New Product")));
//    }
//    @Test
//    public void listPageTest() throws Exception{
//        mvc.perform(get("/product/list"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Product' List")));
//    }
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
