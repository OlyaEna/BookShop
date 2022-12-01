package com.example.ex.utils;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class ShoppingCart {
    private  List<OrderItem> orderItems;

    private final  ProductService productService;
    private final ProductRepository productRepository;


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @PostConstruct
    public void init() {
        orderItems = new ArrayList<>();
    }

    public void addProductById(Long id) {
        Product product = productRepository.getReferenceById(id);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItems.add(orderItem);
    }
}