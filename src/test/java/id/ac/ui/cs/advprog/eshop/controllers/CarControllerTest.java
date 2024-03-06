package id.ac.ui.cs.advprog.eshop.controllers;

import id.ac.ui.cs.advprog.eshop.controller.CarController;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.openmbean.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductServiceImpl service;
    @MockBean
    private CarServiceImpl carService;

    @InjectMocks
    private CarController controller;


    private List<Car> allCars;

    private  Car mockAddCarToRepository(Car car){
        allCars.add(car);
        return car;
    }

    private boolean mockEditCarToRepository(Car car){
        for(Car datum : allCars){
            if(datum.getCarId().equals(car.getCarId())){
                datum.setCarName(car.getCarName());
                datum.setCarQuantity(car.getCarQuantity());
                return true;
            }
        }
        return false;
    }

    Car createAndSaveCar(){
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("bambang");
        return car;
    }

    @BeforeEach
    void setUp(){
        allCars = new ArrayList<>();
    }

    @AfterEach
    void deleteRepository(){
        allCars = null;
    }

    @Test
    public void createPageTest() throws Exception{
        mvc.perform(get("/car/createCar"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create New Car")));
    }
    @Test
    public void listPageTest() throws Exception{
        mvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Car' List")));
    }
    @Test
    public void createCarPostTest() throws Exception{
        Car car = createAndSaveCar();

        when(carService.create(car)).thenReturn(mockAddCarToRepository(car));
        mvc.perform(post("/car/createCar").flashAttr("car",car))
                .andExpect(status().is3xxRedirection());

        when(carService.findAll()).thenReturn(allCars);
        mvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Car' List")))
                .andExpect(content().string(containsString("bambang")));
    }

    @Test
    public void editCarGetTest() throws Exception{

        Car car = createAndSaveCar();

        when(carService.create(car)).thenReturn(mockAddCarToRepository(car));
        mvc.perform(post("/car/createCar").flashAttr("car",car))
                .andExpect(status().is3xxRedirection());
        when(carService.findById(car.getCarId())).thenReturn(car);
        mvc.perform(get("/car/editCar/"+car.getCarId()))
                .andExpect(status().isOk()).andExpect(content().string(containsString("Edit Car")));
    }

    @Test
    public void editCarPostTest() throws Exception{

        Car car = createAndSaveCar();

        when(carService.create(car)).thenReturn(mockAddCarToRepository(car));
        mvc.perform(post("/car/createCar").flashAttr("car",car))
                .andExpect(status().is3xxRedirection());

        Car car2 = new Car();
        car2.setCarId(car.getCarId());
        car2.setCarName("adi");
        mvc.perform(post("/car/editCar").flashAttr("car",car2))
                .andExpect(status().is3xxRedirection());
        mockEditCarToRepository(car2); // assume carcarService.update is functionally good

        when(carService.findAll()).thenReturn(allCars);
        mvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Car' List")))
                .andExpect(content().string(containsString("adi")));
    }
    @Test
    public void editCarGetNotFoundTest() throws Exception{

        Car car2 = new Car();
        car2.setCarId("some ID");
        car2.setCarName("adi");
        when(carService.findById("some ID")).thenReturn(null);
        mvc.perform(get("/car/editCar/"+car2.getCarId()))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void deleteCarGetTest() throws Exception{

        Car car = createAndSaveCar();

        when(carService.create(car)).thenReturn(mockAddCarToRepository(car));
        mvc.perform(post("/car/createCar").flashAttr("car",car))
                .andExpect(status().is3xxRedirection());

        when(carService.findAll()).thenReturn(allCars);
        mvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Car' List")))
                .andExpect(content().string(containsString("bambang")));

        mvc.perform(post("/car/deleteCar").param("carId",car.getCarId()))
                .andExpect(status().is3xxRedirection());
        allCars.remove(car); // assume deletion is good

        assertEquals(0, allCars.size());
    }
}
