package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController extends ProductController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "createcar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute("product") Car car, Model model){
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String listCarPage(Model model){
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "listCar";
    }

    @GetMapping(value="/editCar/{carId}")
    public String editProductPage(Model model, @PathVariable("carId") String productId){
        Car car = carService.findById(productId);
        model.addAttribute("car", car);
        return "editcar";
    }

    @PostMapping("/editCar")
    public String editProductPost(@ModelAttribute("car") Car car, Model model){
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);
        return "redirect:listCar";
    }
    @PostMapping("/deleteCar")
    public String deleteCar(Model model, @RequestParam("carId") String carId){
        carService.deleteCarById(carId);
        return "redirect:listCar";
    }
}
