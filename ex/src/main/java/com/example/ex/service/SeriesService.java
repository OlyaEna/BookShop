package com.example.ex.service;

import com.example.ex.dto.GenreDto;
import com.example.ex.dto.ProductDto;
import com.example.ex.dto.SeriesDto;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.entity.Series;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

public interface SeriesService {
    List<SeriesDto> findAll();
    SeriesDto findById(Long id);

    Series saveSeries(SeriesDto seriesDto);

    void deleteSeries(Long id);

    void deleteById(Long id);

    void enableById(Long id);

    Series update(SeriesDto seriesDto);
}
