package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @MockBean
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl service;

    private List<Car> allCars;

    @BeforeEach
    void setUp(){
        allCars = new ArrayList<>();
        createAndSaveCar("nama","id",1);
    }

    Car createAndSaveCar(String name, String id, int quantity){
        Car car = createCar(name,id,quantity);
        service.create(car);
        allCars.add(car);

        return car;
    }

    @AfterEach
    void deleteCar(){
        allCars = null;
    }

    Car editRepoMock(Car car){
        for(Car datum : allCars){
            if (datum.getCarId().equals(car.getCarId())){
                datum.setCarName(car.getCarName());
                datum.setCarQuantity(car.getCarQuantity());
                return datum;
            }
        }
        return null;
    }

    Car createCar(String name, String id, int quantity){
        Car car = new Car();
        car.setCarId(id);
        car.setCarName(name);
        car.setCarQuantity(quantity);

        return car;
    }

    @Test
    void testCreateCar(){
        createAndSaveCar("nama","id",1);
        when(carRepository.findAll()).thenReturn(allCars.iterator());
        List<Car> list = service.findAll();

        assertEquals("id", list.getFirst().getCarId());
        assertEquals("nama", list.getFirst().getCarName());
        assertEquals(1, list.getFirst().getCarQuantity());
    }

    @Test
    void testFindAllCar(){
        createAndSaveCar("nomu","uwu",2);
        when(carRepository.findAll()).thenReturn(allCars.iterator());
        List<Car> list = service.findAll();

        assertEquals("id", list.get(0).getCarId());
        assertEquals("nama", list.get(0).getCarName());
        assertEquals(1, list.get(0).getCarQuantity());
        assertEquals("uwu", list.get(1).getCarId());
        assertEquals("nomu", list.get(1).getCarName());
        assertEquals(2, list.get(1).getCarQuantity());
    }

    @Test
    void testEditGetCar(){
        Car car2 = createAndSaveCar("aa", "id2",20);
        Car car3 = createCar("nomu","id2",2); // Data to edit car2

        when(carRepository.update(car3.getCarId(), car3)).thenReturn(editRepoMock(car3));
        service.update(car3.getCarId(), car3);
        when(carRepository.findById(car2.getCarId())).thenReturn(car3);
        Car resultEdit = service.findById(car2.getCarId());

        assertEquals("id2", resultEdit.getCarId());
        assertEquals("nomu", resultEdit.getCarName());
        assertEquals(2, resultEdit.getCarQuantity());
    }

    @Test
    void testCreateDeleteCar(){
        Car car2 = createAndSaveCar("aa", "id2",20);

        service.deleteCarById("id");
        allCars.remove(allCars.get(0)); // assume carRepo deletes id

        when(carRepository.findAll()).thenReturn(allCars.iterator());
        List<Car> list = service.findAll();
        assertEquals(1,list.size());
    }

}
