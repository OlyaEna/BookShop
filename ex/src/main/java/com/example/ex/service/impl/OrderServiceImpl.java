package com.example.ex.service.impl;

import com.example.ex.model.repository.OrderRepository;
import com.example.ex.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
}
