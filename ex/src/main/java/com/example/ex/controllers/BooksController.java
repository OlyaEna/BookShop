package com.example.ex.controllers;

import com.example.ex.dto.ProductDto;
import com.example.ex.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")

public class BooksController {
    private final ProductService productService;

//    @GetMapping()
//    public String books(Model model) {
//        List<ProductDto> productDto= productService.findAll();
//        model.addAttribute("productDto", productDto);
//        return "allbooks";
//    }

//    @GetMapping("/search")
//    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
//        model.addAttribute("products", productService.listProducts(title));
//        return "search-books";
//    }

    @GetMapping()
    public String books(Model model) {
        List<ProductDto> productDto= productService.findAll();
        model.addAttribute("productDto", productDto);
        return "boooks";
    }

    @GetMapping("/sea")
    public String productInfo(@RequestParam(name = "id", required = false) Long id, Model model) {
        ProductDto product = productService.getById(id);
        model.addAttribute("product", product);
        return "book-information";
    }



}
