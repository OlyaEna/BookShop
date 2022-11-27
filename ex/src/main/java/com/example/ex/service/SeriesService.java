package com.example.ex.service;

import com.example.ex.model.entity.Publisher;
import com.example.ex.model.entity.Series;

import java.util.List;

public interface SeriesService {
    List<Series> findAll();
    Series saveSeries(Series series);
    void deleteSeries(Long id);
}
