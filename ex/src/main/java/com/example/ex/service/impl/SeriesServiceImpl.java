package com.example.ex.service.impl;

import com.example.ex.model.entity.Series;
import com.example.ex.model.repository.SeriesRepository;
import com.example.ex.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;

    @Override
    public List<Series> findAll() {
        return seriesRepository.findAll();
    }

    @Override
    public Series saveSeries(Series series) {
        return seriesRepository.save(series);
    }

    @Override
    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }
}
