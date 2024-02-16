package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("")
    public String homePage(Model model){
        return "homepage";
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createproduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute("product") Product product, Model model){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping(value="/product/edit/{id}")
    public String editProductPage(Model model, @PathVariable("id") String productId){
        try{
            Product product = service.findById(productId);
            model.addAttribute("product", product);
            return "editproduct";
        }catch (Exception e){
            return "redirect:../list";
        }
    }

    @PostMapping(value="/product/edit/{id}")
    public String editProductPost(@ModelAttribute("product") Product product, Model model, @PathVariable("id") String productId){
        product.setProductId(productId); // Reasoning: After posted by form, id becomes null
        service.update(product);
        return "redirect:../list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productlist";
    }

    @PostMapping("/product/delete")
    public String deleteProductPost(Model model, @RequestParam String idToBeDelete){
        service.deleteProductById(idToBeDelete);
        return "redirect:list";
    }
}
