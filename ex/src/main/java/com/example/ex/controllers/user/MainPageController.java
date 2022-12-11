package com.example.ex.controllers.user;

import com.example.ex.dto.ProductDto;
import com.example.ex.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        List<ProductDto> productDto = productService.findAll();
        List<ProductDto> listViewProducts = productService.listViewProducts();
        List<ProductDto> listNewProducts = productService.listNewProducts();
        model.addAttribute("listNewProducts", listNewProducts);
        model.addAttribute("viewProducts", listViewProducts);
        model.addAttribute("productDto", productDto);
        return "books/index";
    }

}
