package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentBankTest {
    List<Payment> payments;

    List<Order> orders;

    List<Product> products;

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

        this.orders = new ArrayList<>();

        Order order1 = new Order("bbbbaaaa-4a37-4664-83c7-f32db8620155", products ,100L, "Budi");
        Order order2 = new Order("aaaabbbb-4a37-4664-83c7-f32db8620155", products ,100L, "Budi");
        Order order3 = new Order("ccccbbbb-4a37-4664-83c7-f32db8620155", products ,100L, "Andi");
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
    }

    @Test
    void testCreatePaymentBankSuccess(){
        Map<String, String> paymentDataBank = new HashMap<>();
        paymentDataBank.put("bankName", "a");
        paymentDataBank.put("referenceCode", "0");
        Payment payment1 = new PaymentBank("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",orders.get(0), "BANK", paymentDataBank);
        assertSame(this.orders.get(0), payment1.getOrder());
        assertEquals(paymentDataBank, payment1.getPaymentData());
        assertEquals("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa", payment1.getId());
        assertEquals(PaymentMethod.BANK.getValue(), payment1.getMethod());

    }

    @Test
    void testCreatePaymentFailBankName(){
        Map<String, String> paymentDataBank = new HashMap<>();
        paymentDataBank.put("bankName", "");
        paymentDataBank.put("referenceCode", "0");
        assertThrows(IllegalArgumentException.class, ()-> {new PaymentBank("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",orders.get(1),
                "BANK", paymentDataBank);
        });
    }

    @Test
    void testCreatePaymentFailReferenceCode(){
        Map<String, String> paymentDataBank = new  HashMap<>();
        paymentDataBank.put("bankName", "a");
        paymentDataBank.put("referenceCode", "");
        assertThrows(IllegalArgumentException.class, ()-> {new PaymentBank("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",orders.get(1),
                "BANK", paymentDataBank);
        });
    }
}
