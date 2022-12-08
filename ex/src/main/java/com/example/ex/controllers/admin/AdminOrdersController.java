package com.example.ex.controllers.admin;

import com.example.ex.model.repository.UserRepository;
import com.example.ex.service.OrderService;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminOrdersController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderService orderService;

    @GetMapping("/orders")
    public String showOrdersAdmin(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/ordersAdmin";
    }

    @GetMapping("/orders/remove/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return "redirect:/admin/orders";
    }

    @GetMapping("/done-order/{id}")
    public String readMessage(@PathVariable("id") Long id, RedirectAttributes attributes, HttpServletRequest request) {
        try {
            orderService.doneById(id);
            attributes.addFlashAttribute("success", "Done successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to done!");
        }
        return getPreviousPageByRequest(request).orElse("/");
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }
}
