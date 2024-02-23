package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createproduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute("product") Product product, Model model){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping(value="/edit/{id}")
    public String editProductPage(Model model, @PathVariable("id") String productId){
        try{
            Product product = service.findById(productId);
            model.addAttribute("product", product);
            return "editproduct";
        }catch (Exception e){
            return "redirect:../list";
        }
    }

    @PostMapping(value="/edit/{id}")
    public String editProductPost(@ModelAttribute("product") Product product, Model model, @PathVariable("id") String productId){
        product.setProductId(productId); // Reasoning: After posted by form, id becomes null
        service.update(product);
        return "redirect:../list";
    }

    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productlist";
    }

    @PostMapping("/delete")
    public String deleteProductPost(Model model, @RequestParam("idToBeDelete") String idToBeDelete){
        service.deleteProductById(idToBeDelete);
        return "redirect:list";
    }
}
