package id.ac.ui.cs.advprog.eshop.controllers;

import id.ac.ui.cs.advprog.eshop.controller.OrderController;
import id.ac.ui.cs.advprog.eshop.controller.PaymentController;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.service.OrderServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.PaymentServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private PaymentServiceImpl paymentService;
    @MockBean
    private CarServiceImpl carService;
    @MockBean
    private ProductServiceImpl productService ;
    @MockBean
    private OrderServiceImpl orderService ;
    @InjectMocks
    private PaymentController controller;

    @Test
    public void testGetCreatePage() throws Exception{
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductName("a");
        productList.add(product);
        when(productService.findAll()).thenReturn(productList);
        mvc.perform(get("/order/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create Order")));
    }

    @Test
    public void testPostCreatePage() throws Exception{
        OrderDTO dto = new OrderDTO();
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductName("a");
        productList.add(product);
        dto.setProducts(productList);
        when(productService.findById(product.getProductId())).thenReturn(product);
        mvc.perform(post("/order/create").flashAttr("form", dto))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testGetHistory() throws Exception{
        mvc.perform(get("/order/history"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostHistory() throws Exception{
        mvc.perform(post("/order/history").flashAttr("owner", new OrderDTO()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPayId() throws Exception{
        mvc.perform(get("/order/pay/a"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostOrderPayBank() throws Exception{
        PaymentDTO dto = new PaymentDTO();
        dto.setMethod(PaymentMethod.BANK.getValue());
        dto.setReferenceCode("a");
        dto.setBankName("v");
        dto.setOrderId("a");

        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product());

        HashMap<String,String>paymentData = new HashMap<>();
        paymentData.put("referenceCode", "a");
        paymentData.put("bankName", "a");

        Order order = new Order("a",productList,1l,"a");
        Payment payment = new PaymentBank("a",order,PaymentMethod.BANK.getValue(), paymentData);

        Map<String, Object> map = new HashMap<>();
        map.put("paymentinfo", dto);

        when(orderService.findById(order.getId())).thenReturn(order);
        when(paymentService.addPayment(any(Order.class),anyString(),any(Map.class))).thenReturn(payment);
        mvc.perform(post("/order/pay/"+order.getId()).flashAttrs(map))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostOrderPayVoucher() throws Exception{
        PaymentDTO dto = new PaymentDTO();
        dto.setMethod(PaymentMethod.VOUCHER.getValue());
        dto.setVoucherCode("ESHOP12345678AAA");

        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product());

        HashMap<String,String>paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678AAA");

        Order order = new Order("a",productList,1l,"a");
        Payment payment = new PaymentVoucher("a",order,PaymentMethod.VOUCHER.getValue(), paymentData);

        Map<String, Object> map = new HashMap<>();
        map.put("paymentinfo", dto);

        when(orderService.findById(order.getId())).thenReturn(order);
        when(paymentService.addPayment(any(Order.class),anyString(),any(Map.class))).thenReturn(payment);
        mvc.perform(post("/order/pay/"+order.getId()).flashAttrs(map))
                .andExpect(status().isOk());
    }
}
