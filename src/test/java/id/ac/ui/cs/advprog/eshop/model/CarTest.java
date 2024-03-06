package id.ac.ui.cs.advprog.eshop.model;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    Car car;
    @BeforeEach
    void setUp(){
        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Sampo cap Bambang");
        car.setCarColor("aa");
        car.setCarQuantity(100);
    }

    @Test
    void testGetCarId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", car.getCarId());
    }

    @Test
    void testGetCarName(){
        assertEquals("Sampo cap Bambang", car.getCarName());
    }

    @Test
    void testGetCarQuantity(){
        assertEquals(100, this.car.getCarQuantity());
    }
    @Test
    void testGetCarColor(){
        assertEquals("aa", this.car.getCarColor());
    }
    @Test
    void testCarUpdate(){
        Car car2 = new Car();
        car2.setCarColor("bb");
        car2.setCarName("b");
        car2.setCarQuantity(10);

        car.update(car2);
        assertEquals(car.getCarColor(), car2.getCarColor());
        assertEquals(car.getCarQuantity(), car2.getCarQuantity());
        assertEquals(car.getCarName(), car2.getCarName());
    }
}
