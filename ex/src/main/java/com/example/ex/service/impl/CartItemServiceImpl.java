package com.example.ex.service.impl;

import com.example.ex.model.repository.CartItemRepository;
import com.example.ex.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
}
