package com.example.ex.controllers;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Product;
import com.example.ex.model.repository.ProductRepository;
import com.example.ex.utils.ShoppingCart;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ShoppingCart cart;

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("orderItems", cart.getOrderItems());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id, HttpServletRequest request) {
        cart.addProductById(id);

        return getPreviousPageByRequest(request).orElse("/");
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

}