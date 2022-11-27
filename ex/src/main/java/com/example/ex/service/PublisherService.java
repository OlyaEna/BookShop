package com.example.ex.service;

import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> findAll();
    Publisher savePublisher(Publisher publisher);
    void deletePublisher(Long id);

}