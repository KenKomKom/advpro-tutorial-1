package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest {
    @InjectMocks
    CarRepository carRepository;

    Car createAndSaveCar(String name, String id, int quantity){
        Car car = createCar(name,id,quantity);
        carRepository.createCar(car);
        car.setCarId(id); // carRepo.create changes the id

        return car;
    }

    Car createCar(String name, String id, int quantity){
        Car car = new Car();
        car.setCarId(id);
        car.setCarName(name);
        car.setCarQuantity(quantity);

        return car;
    }

    @Test
    void testCreateAndFind(){
        Car car = createAndSaveCar("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();

        assertEquals(savedCar.getCarId(), car.getCarId());
        assertEquals(savedCar.getCarName(), car.getCarName());
        assertEquals(savedCar.getCarQuantity(), car.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar(){
        Car car1 = createAndSaveCar("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        Car car2 = createAndSaveCar("Sampo Cap Usep","a0f9de45-90b1-437d-a0bf-d0821dde9096",50);

        Iterator<Car> carIterator = carRepository.findAll();

        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testCreateAndDelete(){
        Iterator<Car> carIterator = carRepository.findAll();

        createAndSaveCar("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        assertTrue(carIterator.hasNext());
        carRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testCreateAndEdit(){
        // Initializing first object
        Car car1 = createAndSaveCar("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        // Object with same id different attribute
        Car car2 = createCar("Lalalalala","eb558e9f-1c39-460e-8860-71af6af63bd6",200);

        carRepository.update(car1.getCarId(),car2);

        assertEquals(200, car1.getCarQuantity());
        assertEquals("Lalalalala", car1.getCarName());
    }

    @Test
    void testCreateEditDelete(){
        Iterator<Car> carIterator = carRepository.findAll();

        // Initializing first object
        Car car1 = createAndSaveCar("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        // Object with same id different attribute
        Car car2 = createCar("Lalalalala","eb558e9f-1c39-460e-8860-71af6af63bd6",200);

        carRepository.update(car1.getCarId(),car2);

        assertEquals(200, car1.getCarQuantity());
        assertEquals("Lalalalala", car1.getCarName());

        carRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testEditIfNotFound(){
        Car car1 = createAndSaveCar("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        // Object with same id different attribute
        Car car2 = createCar("Lalalalala","-",200);

        carRepository.update(car2.getCarId(), car2);

        assertNotEquals(200, car1.getCarQuantity());
        assertNotEquals("Lalalalala", car1.getCarName());
    }

    @Test
    void testDeleteIfNotFound(){
        Iterator<Car> carIterator = carRepository.findAll();

        createAndSaveCar("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        carRepository.delete("-");
        assertTrue(carIterator.hasNext());

    }

    @Test
    void testCreateGet(){
        Car car1 = createAndSaveCar("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);
        Car result = carRepository.findById(car1.getCarId());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getCarId());
        assertEquals("Sampo Cap Bambang", result.getCarName());
        assertEquals(100, result.getCarQuantity());
    }

    @Test
    void testCreateGetNotFound(){
        Car car = carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(car);
    }
    @Test
    void testCreateIfNoId(){
        Car car = new Car();
        assertNull(car.getCarId());
        Car carRes = carRepository.createCar(car);
        assertNotNull(carRes.getCarId());

    }
}
