package com.example.ex.controllers;

import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.User;
import com.example.ex.model.repository.OrderRepository;
import com.example.ex.service.OrderService;
import com.example.ex.service.UserService;
import com.example.ex.utils.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    private final UserService userService;



//    @GetMapping("")
//    public String showOrders(Model model, Principal principal) {
//        User user = userService.findUserByEmail(principal.getName());
//        model.addAttribute("user", user);
//        model.addAttribute("orders", orderService.getCustomOrders(principal));
//        return "orders";
//    }

//
//    @GetMapping("/create_order")
//    public String createOrder(Principal principal) {
//        User user = userService.findUserByEmail(principal.getName());
//        Order orderFromItems = orderService.createOrderFromItems(user, CartController.class.);
//        return "redirect:order-details/" + orderFromItems.getId();
//    }
//
//

}