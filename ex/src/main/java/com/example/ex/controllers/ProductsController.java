package com.example.ex.controllers;

import com.example.ex.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")

public class ProductsController {
    private final ProductService productService;

    @GetMapping("/products")
    public String products(Model model)  {
        var products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("size", products.size());
        return "admin/product/products";
    }
}
