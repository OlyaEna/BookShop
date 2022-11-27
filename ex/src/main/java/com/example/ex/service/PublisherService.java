package com.example.ex.service;

import com.example.ex.dto.CategoryDto;
import com.example.ex.dto.PublisherDto;
import com.example.ex.model.entity.Category;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Publisher;

import java.util.List;

public interface PublisherService {
    List<PublisherDto> findAll();

    Publisher savePublisher(PublisherDto publisherDto);

    void deletePublisher(Long id);

    void deleteById(Long id);

    void enableById(Long id);

    PublisherDto findById(Long id);

    Publisher update(PublisherDto publisherDto);

}