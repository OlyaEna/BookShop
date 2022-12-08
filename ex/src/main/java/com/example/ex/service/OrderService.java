package com.example.ex.service;

import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.OrderItem;
import com.example.ex.model.entity.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


public interface OrderService {

    Order createOrderFromItems(User user, List<OrderItem> orderItems);

    Order getOrderById(Long id);

    List<Order> getOrderByUser(User user);

    List<Order> getAllOrders();

    void deleteOrderById(Long id);

    List<Order> getCustomOrders(Principal principal);

    void doneById(Long id);
}
