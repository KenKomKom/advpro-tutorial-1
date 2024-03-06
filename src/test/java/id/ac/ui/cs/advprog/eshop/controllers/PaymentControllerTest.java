package id.ac.ui.cs.advprog.eshop.controllers;

import id.ac.ui.cs.advprog.eshop.controller.PaymentController;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = PaymentController.class)
public class PaymentControllerTest {
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
    public void testGetPaymentDetail() throws Exception{
        mvc.perform(get("/payment/detail"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Payment Info")));
    }

    @Test
    public void testDetailIdBank() throws Exception{
        HashMap<String,String>paymentData = new HashMap<>();
        paymentData.put("referenceCode", "a");
        paymentData.put("bankName", "a");
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product());
        Payment payment = new PaymentBank("a",new Order("a",productList,1l,"a"),"BANK",paymentData);
        when(paymentService.getPayment(payment.getId())).thenReturn(payment);
        mvc.perform(get("/payment/detail/"+payment.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Payment Info")));
    }

    @Test
    public void testDetailIdVoucher() throws Exception{
        HashMap<String,String>paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678AAA");
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product());
        Payment payment = new PaymentVoucher("a",new Order("a",productList,1l,"a"),"VOUCHER",paymentData);
        when(paymentService.getPayment(payment.getId())).thenReturn(payment);
        mvc.perform(get("/payment/detail/"+payment.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Payment Info")));
    }

    @Test
    public void testPaymentList() throws Exception{
        mvc.perform(get("/payment/admin/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAdminDetailVoucher() throws Exception{
        HashMap<String,String>paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678AAA");
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product());
        Payment payment = new PaymentVoucher("a",new Order("a",productList,1l,"a"),"VOUCHER",paymentData);
        when(paymentService.getPayment(payment.getId())).thenReturn(payment);
        mvc.perform(get("/payment/admin/detail/"+payment.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAdminDetailIdBank() throws Exception{
        HashMap<String,String>paymentData = new HashMap<>();
        paymentData.put("referenceCode", "a");
        paymentData.put("bankName", "a");
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product());
        Payment payment = new PaymentBank("a",new Order("a",productList,1l,"a"),"BANK",paymentData);
        when(paymentService.getPayment(payment.getId())).thenReturn(payment);
        mvc.perform(get("/payment/admin/detail/"+payment.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Payment Info")));
    }

    @Test
    public void testPostSetStatus() throws Exception{
        HashMap<String,String>paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678AAA");
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product());
        Payment payment = new PaymentVoucher("a",new Order("a",productList,1l,"a"),"VOUCHER",paymentData);
        when(paymentService.getPayment(payment.getId())).thenReturn(payment);
        mvc.perform(post("/payment/admin/set-status/"+payment.getId()).flashAttr("status", PaymentStatus.SUCCESS.getValue()))
                .andExpect(status().is3xxRedirection());
    }
}
