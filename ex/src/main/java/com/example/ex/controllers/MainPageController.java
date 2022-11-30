package com.example.ex.controllers;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Product;
import com.example.ex.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        List<ProductDto> productDto = productService.findAll();
        model.addAttribute("productDto", productDto);
        return "index";
    }


//    @GetMapping("/trying")
//    public String trying(Model model) {
//        List<Product> product = new ArrayList<>();
//        List<Product> productNew= productService.getRandomElement(product, 4);
//        model.addAttribute("productNew", productNew);
//        return "try";
//    }
}
