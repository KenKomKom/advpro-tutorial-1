package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.service.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private PaymentServiceImpl paymentService;

    @GetMapping("/create")
    public String createOrderPage(Model model){
        List<Product> productList = productService.findAll();
        OrderDTO dto = new OrderDTO();
        for (Product p : productList){
            dto.addProduct(p);
        }
        model.addAttribute("form", dto);
        System.out.println("zczc"+productList);
        return "createorder";
    }

    @PostMapping("/create")
    public String createOrderPagePost(@ModelAttribute("form") OrderDTO orders,Model model){
        List<Product> products = new ArrayList<>();
        for (Product p : orders.getProducts()){
            Product temp = productService.findById(p.getProductId());
            p.setProductName(temp.getProductName());
            products.add(p);
            System.out.println(p.getProductId()+"zczc "+p.getProductName()+" "+p.getProductQuantity());
        }
        System.out.println("zczc"+orders.getOwner());
        LocalTime now = LocalTime.now();
        Time orderTime = Time.valueOf(now);
        long orderTimeL = orderTime.getTime();
        Order newOrder = new Order(UUID.randomUUID().toString(), products, orderTimeL, orders.getOwner());
        orderService.createOrder(newOrder);

        return "redirect:history";
    }

    @GetMapping("/history")
    public String getOrderAuthorHistory(Model model){
        model.addAttribute("owner", new OrderDTO());
        return "orderhistoryget";
    }

    @PostMapping("/history")
    public String postOrderAuthorHistory(@ModelAttribute("owner") OrderDTO orders, Model model){
        String owner = orders.getOwner();
        model.addAttribute("orders", orderService.findAllByAuthor(owner));
        return "orderhistory";
    }

    @GetMapping("/pay/{id}")
    public String getOrderPay( Model model, @PathVariable("id") String id){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId(id);

        model.addAttribute("paymentinfo", paymentDTO);
        model.addAttribute("order", id);

        return "orderbeforepay";
    }

    @PostMapping("/pay/{id}")
    public String postOrderPay(@ModelAttribute("paymentinfo") PaymentDTO paymentInfo, Model model, @PathVariable("id") String id) {
        HashMap<String, String> paymentDesc = new HashMap<>();
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId(id);
        paymentDTO.setMethod(paymentInfo.getMethod());
        System.out.println("zczc "+paymentInfo.getMethod());
        if (paymentInfo.getMethod().equals(PaymentMethod.VOUCHER.getValue())){
            if (paymentInfo.getVoucherCode()==null){
                paymentInfo.setVoucherCode("");
            }
            paymentDesc.put("voucherCode", paymentInfo.getVoucherCode());
            paymentDTO.setVoucherCode(paymentInfo.getVoucherCode());
        }
        else if(paymentInfo.getMethod().equals(PaymentMethod.BANK.getValue())){
            if (paymentInfo.getBankName()==null){
                paymentInfo.setBankName("");
            }
            if (paymentInfo.getReferenceCode()==null){
                paymentInfo.setReferenceCode("");
            }
            System.out.println("zczc"+paymentInfo.getBankName()+" "+paymentInfo.getReferenceCode()+" "+paymentInfo.getVoucherCode());
            paymentDesc.put("bankName", paymentInfo.getBankName());
            paymentDesc.put("referenceCode", paymentInfo.getReferenceCode());
            paymentDTO.setBankName(paymentInfo.getBankName());
            paymentDTO.setReferenceCode(paymentInfo.getReferenceCode());
        }

        Payment payment = paymentService.addPayment(orderService.findById(id), paymentInfo.getMethod(), paymentDesc);

        model.addAttribute("payment", payment);
        model.addAttribute("paymentinfo", paymentDTO);
        return "orderafterpay";
    }
}
