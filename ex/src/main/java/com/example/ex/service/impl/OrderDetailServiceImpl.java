package com.example.ex.service.impl;

import com.example.ex.model.repository.OrderDetailRepository;
import com.example.ex.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
}
