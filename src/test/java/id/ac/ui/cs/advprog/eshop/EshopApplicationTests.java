package id.ac.ui.cs.advprog.eshop;

import id.ac.ui.cs.advprog.eshop.controller.CarController;
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
	@Test
	void contextLoads() {
		EshopApplication.main(new String[] {});
		assertThat(productController).isNotNull();
		assertThat(carController).isNotNull();
	}

}
