package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
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
public class PageFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest(){
        baseUrl=String.format("%s:%d",testBaseUrl, serverPort);
    }

    void deleteAll(ChromeDriver driver, int productsMade){
        for (int i=0; i<productsMade; i++){
            List<WebElement> links = driver.findElements(By.tagName("a"));
            WebElement linkDelete = links.get(2);
            linkDelete.click();
        }
    }

    void fillNameQuantity(ChromeDriver driver, String name, int quantity){
        driver.findElement(By.id("nameInput")).sendKeys(name);
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(quantity));
        driver.findElement(By.className("btn")).click();
    }

    void createProduct(ChromeDriver driver, String name, int quantity){
        driver.get(String.format(baseUrl+"/product/create"));
        fillNameQuantity(driver, name, quantity);
    }
    boolean checkForFirstProductInList(ChromeDriver driver, String name, int quantity){
        boolean success = true;

        String productList = driver.findElement(By.tagName("h2")).getText();
        success = success && "Product' List".equals(productList);
        System.out.println("cek product' list: "+success);

        List<WebElement> productInfo = driver.findElements(By.tagName("td"));
        WebElement productName = productInfo.get(0);
        success = success && name.equals(productName.getText());
        System.out.println(name+" "+productName.getText());
        System.out.println("cek nama: "+success);

        WebElement productQuantity = productInfo.get(1);
        success = success && String.valueOf(quantity).equals(productQuantity.getText());
        System.out.println(String.valueOf(quantity)+" "+productQuantity.getText());
        System.out.println("cek kuantitas: "+success);

        return success;
    }

    @Test
    void pageTitleIsCorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();

        assertEquals("ADV Shop", pageTitle);
    }

    @Test
    void welcomeMessageHomepageIsCorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);
        String welcomeMessage = driver.findElement(By.tagName("h3")).getText();

        assertEquals("Welcome", welcomeMessage);
    }

    @Test
    void createProductIsCorrect(ChromeDriver driver) throws Exception{
        String name = "lalal";
        int quantity = 1230;

        createProduct(driver, name, quantity);
        assertTrue(checkForFirstProductInList(driver, name, quantity));

        deleteAll(driver,1);
    }

    @Test
    void createEditProductIsCorrect(ChromeDriver driver) throws Exception{
        String name = "lala";
        int quantity = 1231;

        String name2 = "budiPekerti";
        int quantity2= 84021;

        createProduct(driver, name, quantity);
        assertTrue(checkForFirstProductInList(driver, name, quantity));

        List<WebElement> links = driver.findElements(By.tagName("a"));
        WebElement linkEdit = links.get(1);
        linkEdit.click();

        fillNameQuantity(driver, name2, quantity2);

        assertTrue(checkForFirstProductInList(driver, name2, quantity2));

        deleteAll(driver,1);
    }

    @Test
    void createDeleteIsCorrect(ChromeDriver driver) throws Exception{
        String name = "lala";
        int quantity = 1231;

        createProduct(driver, name, quantity);
        assertTrue(checkForFirstProductInList(driver, name, quantity));

        List<WebElement> links = driver.findElements(By.tagName("a"));
        WebElement linkDelete = links.get(2);
        linkDelete.click();

        List<WebElement> productInfo = driver.findElements(By.tagName("td"));
        assertEquals(0,productInfo.size());
    }
}
