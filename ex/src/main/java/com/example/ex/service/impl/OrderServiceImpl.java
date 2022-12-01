package com.example.ex.service.impl;

import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.OrderItem;
import com.example.ex.model.entity.Role;
import com.example.ex.model.entity.User;
import com.example.ex.model.repository.OrderRepository;
import com.example.ex.model.repository.RoleRepository;
import com.example.ex.service.OrderService;
import com.example.ex.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final UserService userService;
    private final RoleRepository roleRepository;


    public Order createOrderFromItems(User user, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setOrderItems(new ArrayList<>());
        order.setUser(user);
        orderItems.stream().forEach(orderItem -> {
            order.getOrderItems().add(orderItem);
            orderItem.setOrder(order);
        });
        orderItems.clear();
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.getReferenceById(id);
    }

    public List<Order> getOrderByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getCustomOrders(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());

//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        if (role.equals("ROLE_ADMIN")) {
//            return getAllOrders();
//        }
        return getOrderByUser(user);
    }
}
