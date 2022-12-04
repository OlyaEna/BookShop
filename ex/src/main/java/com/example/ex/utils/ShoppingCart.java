package com.example.ex.utils;

import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.OrderItem;
import com.example.ex.model.entity.Product;
import com.example.ex.model.repository.ProductRepository;
import com.example.ex.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.*;

//@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@RequiredArgsConstructor
//@Data
public class ShoppingCart {
//    List<OrderItem> orderItems=new ArrayList<>();
//    Order order=new Order();
//
//    public void add(Long id, int quantity){
//        OrderItem orderItem = new OrderItem();
//        Product product = new Product();
//        double sumTotal = 0;
//
//        Optional<Product> optionalProduct = productService.get(id);
//        product = optionalProduct.get();
//
//        orderItem.setQuantity(quantity);
//        orderItem.setPrice(product.getPrice());
//        orderItem.setTitle(product.getTitle());
//        orderItem.setTotalPrice(product.getPrice() * quantity);
//        orderItem.setProduct(product);
//
//        Long idProduct=product.getId();
//        boolean added=orderItems.stream().anyMatch(p -> p.getProduct().getId()==idProduct);
//
//        if (!added) {
//            orderItems.add(orderItem);
//        }
//
//        sumTotal = orderItems.stream().mapToDouble(dt -> dt.getTotalPrice()).sum();
//        order.setTotalPrice(sumTotal);

    }