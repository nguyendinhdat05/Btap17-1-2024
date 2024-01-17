package com.example.springboot.controller;

import com.example.springboot.model.Product;
import com.example.springboot.repository.CategoryRepository;
import com.example.springboot.repository.ProductRepository;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductService productService;

//    @GetMapping
//    public ModelAndView findAll() {
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("list", productRepository.findAll());
//        return modelAndView;
//    }

    @GetMapping
    public ModelAndView showAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "3") int size){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("page",productService.getAllProduct(page,size));
        return modelAndView;
    }
    @GetMapping("/add")
    public ModelAndView showFormAdd() {
        ModelAndView modelAndView = new ModelAndView("add");
        modelAndView.addObject("listCategory", categoryRepository.findAll());
        modelAndView.addObject("item", new Product());
        return modelAndView;
    }

    @PostMapping("/add")
    public String add(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("item", productRepository.findById(id).get());
        modelAndView.addObject("listCategory", categoryRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/update")
    public String edit(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return "redirect:/products";
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productRepository.findAllByNameContaining(name);
        modelAndView.addObject("list", products);
        return modelAndView;
    }

    @PostMapping("/searchPrice")
    public ModelAndView searchPrice(@RequestParam double min, double max) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productRepository.findAllByPriceBetween(min, max);
        modelAndView.addObject("list", products);
        return modelAndView;
    }

    @GetMapping("/sortPriceASC")
    public ModelAndView sortPrice() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> products = productRepository.findAllByOrderByPrice();
        modelAndView.addObject("list", products);
        return modelAndView;
    }

//    @GetMapping
//    public ModelAndView findAll(@RequestParam(defaultValue = "0") int pageNo,
//                                @RequestParam(defaultValue = "6") int pageSize) {
//        ModelAndView modelAndView = new ModelAndView("list");
//
//        // Tạo đối tượng Pageable cho phân trang
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//
//        // Sử dụng phương thức phân trang trong repository
//        Page<Product> page = productRepository.findAllProduct(pageable);
//
//        // Lấy danh sách sản phẩm từ trang phân trang
//        List<Product> products = page.getContent();
//
//        modelAndView.addObject("list", products);
//
//        return modelAndView;
//    }
}
