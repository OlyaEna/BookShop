package com.example.ex.service.impl;

import com.example.ex.model.repository.PublisherRepository;
import com.example.ex.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
}