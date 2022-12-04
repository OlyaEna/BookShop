package com.example.ex.controllers;

import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.OrderItem;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.User;
import com.example.ex.service.OrderService;
import com.example.ex.service.ProductService;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Scope("session")
public class CartController {
    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    List<OrderItem> orderItems = new ArrayList<>();
    Order order = new Order();

    @PostMapping("/cart")
    public String addCart(@RequestParam Long id, @RequestParam int quantity, Model model, HttpServletRequest request) {

        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        double sumTotal = 0;

        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();

        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice());
        orderItem.setTitle(product.getTitle());
        orderItem.setTotalPrice(product.getPrice() * quantity);
        orderItem.setProduct(product);

        Long idProduct = product.getId();
        boolean added = orderItems.stream().anyMatch(p -> p.getProduct().getId() == idProduct);

        if (!added) {
            orderItems.add(orderItem);
        }

        sumTotal = orderItems.stream().mapToDouble(dt -> dt.getTotalPrice()).sum();

        order.setTotalPrice(sumTotal);
        model.addAttribute("cart", orderItems);
        model.addAttribute("order", order);

        return getPreviousPageByRequest(request).orElse("/");
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }


    @GetMapping("/getCart")
    public String getCart(Model model) {

        model.addAttribute("cart", orderItems);
        model.addAttribute("order", order);


        return "user/user-cart";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Long id, Model model) {

        List<OrderItem> items = new ArrayList<>();

        for (OrderItem item : items) {
            if (item.getProduct().getId() != id) {
                items.add(item);
            }
        }

        orderItems = items;

        double sumTotal = 0;
        sumTotal = items.stream().mapToDouble(dt -> dt.getTotalPrice()).sum();

        order.setTotalPrice(sumTotal);
        model.addAttribute("cart", orderItems);
        model.addAttribute("order", order);

        return "user/user-cart";
    }

    @GetMapping("/order")
    public String order(Model model, Principal principal) {
        model.addAttribute("cart", orderItems);
        model.addAttribute("order", order);
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);

        return "user/order";
    }

    @PostMapping("/order/create")
    public String createOrder(Model model, Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        Order orderFromItems = orderService.createOrderFromItems(user, orderItems);
        model.addAttribute("user", user);
        return "redirect:/user/my-orders";
    }

    @GetMapping("/user/my-orders")
    public String showMyOrders(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getCustomOrders(principal));
        return "user/my-orders";
    }

    @GetMapping("/order-details/{id}")
    public String toOrderDetails(Model model, @PathVariable("id") Long id) {
        Order selectedOrder = orderService.getOrderById(id);
        model.addAttribute("selectedOrder", selectedOrder);
        return "user/order-details";
    }



}