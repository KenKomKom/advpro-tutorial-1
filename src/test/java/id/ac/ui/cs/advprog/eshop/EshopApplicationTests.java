package id.ac.ui.cs.advprog.eshop;

import id.ac.ui.cs.advprog.eshop.controller.CarController;
import id.ac.ui.cs.advprog.eshop.controller.OrderController;
import id.ac.ui.cs.advprog.eshop.controller.PaymentController;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import id.ac.ui.cs.advprog.eshop.controller.ProductController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EshopApplicationTests {

	@Resource(name="productController")
	private ProductController productController;
	@Resource(name="carController")
	private CarController carController;
	@Resource(name="orderController")
	private OrderController orderController;

	@Resource(name="paymentController")
	private PaymentController paymentController;

	@Test
	void contextLoads() {
		EshopApplication.main(new String[] {});
		assertThat(productController).isNotNull();
		assertThat(carController).isNotNull();
		assertThat(paymentController).isNotNull();
		assertThat(orderController).isNotNull();
	}

}
