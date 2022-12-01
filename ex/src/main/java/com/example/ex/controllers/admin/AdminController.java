package com.example.ex.controllers.admin;

import com.example.ex.dto.UserDto;
import com.example.ex.model.entity.User;
import com.example.ex.model.repository.UserRepository;
import com.example.ex.service.OrderService;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderService orderService;

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping()
    public String adminPage() {
        return "admin/admin";
    }

    @PostMapping("/users/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/orders")
    public String showOrdersAdmin(Model model, Principal principal) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "ordersAdmin";
    }
}
