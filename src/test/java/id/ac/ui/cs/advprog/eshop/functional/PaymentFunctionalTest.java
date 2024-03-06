package id.ac.ui.cs.advprog.eshop.functional;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class PaymentFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    void createProduct(ChromeDriver driver, String name, int quantity){
        driver.get(String.format(baseUrl+"/product/create"));
        fillNameQuantity(driver, name, quantity);
    }

    void fillNameQuantity(ChromeDriver driver, String name, int quantity){
        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys(name);
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(quantity));
        driver.findElement(By.className("btn")).click();
    }

    void createOrder(ChromeDriver driver){
        createProduct(driver, "lala", 0);

        driver.get(baseUrl+"/order/create");
        String pageTitle = driver.getTitle();
        assertEquals("Create Order", pageTitle);

        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(10));

        driver.findElement(By.id("owner")).clear();
        driver.findElement(By.id("owner")).sendKeys("a");
        driver.findElement(By.className("btn")).click();

        pageTitle = driver.getTitle();
        assertEquals("Get Order History", pageTitle);

        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys("a");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

    }

    @BeforeEach
    void setUpTest(){
        baseUrl=String.format("%s:%d",testBaseUrl, serverPort);
    }

    @Test
    void testPaymentDetail(ChromeDriver driver) throws  Exception{
        driver.get(baseUrl+"/payment/detail");
        String pageTitle = driver.getTitle();
        assertEquals("Payment Info", pageTitle);
        String info = driver.findElements(By.tagName("h1")).get(0).getText();
        assertEquals("Payment Criterias", info);
    }

    @Test
    void testPaymentDetailId(ChromeDriver driver) throws  Exception{
        createOrder(driver);
        driver.findElements(By.className("btn")).get(1).click(); // pay
        driver.findElement(By.id("vouchercode")).sendKeys("ESHOP00000000AAA");
        driver.findElement(By.className("btn")).click();

        driver.get(baseUrl+"/payment/admin/list");

        driver.findElement(By.className("btn")).click();
        String pageTitle = driver.getTitle();
        assertEquals("Payment Info", pageTitle);
        String method = driver.findElement(By.xpath("//p[text()='Method : ']")).getText();
        assertEquals("Method : VOUCHER", method);
    }

    @Test
    void testCreatePaymentDetailSetStatus(ChromeDriver driver) throws  Exception{
        createOrder(driver);
        driver.findElements(By.className("btn")).get(1).click(); // pay
        driver.findElement(By.id("vouchercode")).sendKeys("ESHOP00000000AAA");
        driver.findElement(By.className("btn")).click();

        driver.get(baseUrl+"/payment/admin/list");

        driver.findElement(By.className("btn")).click();
        String pageTitle = driver.getTitle();
        assertEquals("Payment Info", pageTitle);

        driver.findElement(By.xpath("//button[text()='Success']")).click();
        String status = driver.findElement(By.id("status")).getText();
        assertEquals("SUCCESS", status);

        driver.findElement(By.className("btn")).click();
        pageTitle = driver.getTitle();
        assertEquals("Payment Info", pageTitle);

        driver.findElement(By.xpath("//button[text()='Reject']")).click();
        status = driver.findElement(By.id("status")).getText();
        assertEquals("REJECTED", status);

    }
}
