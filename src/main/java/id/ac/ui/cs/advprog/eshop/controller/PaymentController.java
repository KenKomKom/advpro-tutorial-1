package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.OrderDTO;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.PaymentDTO;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private PaymentServiceImpl paymentService;
    @GetMapping("/detail")
    public String getPaymentDetail(Model model){
        return "paymentdetail";
    }
    @GetMapping("/detail/{id}")
    public String getPaymentDetailId(@PathVariable("id")String paymentId, Model model){
        Payment temp = paymentService.getPayment(paymentId);
        model.addAttribute("payment", temp);
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setReferenceCode(temp.getPaymentData().get("referenceCode"));
        paymentDTO.setReferenceCode(temp.getPaymentData().get("bankName"));
        model.addAttribute("paymentinfo", paymentDTO);
        return "paymentdetail";
    }
    @GetMapping("/admin/list")
    public String getPaymentList(Model model){
        model.addAttribute("payments", paymentService.getAllPayment());
        return "paymentlist";
    }
    @GetMapping("/admin/detail/{id}")
    public String getPaymentIdAdmin(@PathVariable("id")String paymentId,Model model){
        Payment temp = paymentService.getPayment(paymentId);
        model.addAttribute("payment", temp);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setReferenceCode(temp.getPaymentData().get("referenceCode"));
        paymentDTO.setReferenceCode(temp.getPaymentData().get("bankName"));
        model.addAttribute("paymentinfo", paymentDTO);

        model.addAttribute("admin", true);
        return "paymentdetail";
    }
    @PostMapping("/admin/set-status/{id}")
    public String postPaymentAdminStatus(@PathVariable("id")String paymentId, @ModelAttribute("status") String status, Model model){
        System.out.println("zczc "+status);
        Payment payment = paymentService.getPayment(paymentId);
        paymentService.setStatus(payment,status);
        return "redirect:../list";
    }
}
