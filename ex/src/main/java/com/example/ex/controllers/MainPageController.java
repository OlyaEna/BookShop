package com.example.ex.controllers;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.OrderItem;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.User;
import com.example.ex.service.ProductService;
import com.example.ex.utils.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return "index";
    }

}
