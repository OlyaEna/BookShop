package com.example.ex.service.impl;

import com.example.ex.dto.GenreDto;
import com.example.ex.dto.ProductDto;
import com.example.ex.dto.SeriesDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.Series;
import com.example.ex.model.repository.SeriesRepository;
import com.example.ex.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;

    @Override
    public List<SeriesDto> findAll() {
        List<Series> series = seriesRepository.findAll();
        List<SeriesDto> seriesDtoList = mapper(series);
        return seriesDtoList;
    }

    @Override
    public SeriesDto findById(Long id) {
        Series ser = seriesRepository.getReferenceById(id);
        SeriesDto seriesDto = new SeriesDto();
        seriesDto.setId(ser.getId());
        seriesDto.setName(ser.getName());
        seriesDto.setDeleted(ser.is_deleted());
        seriesDto.setActivated(ser.is_activated());
        return seriesDto;
    }

    private List<SeriesDto> mapper(List<Series> series) {
        List<SeriesDto> seriesDtoList = new ArrayList<>();
        for (Series ser : series) {
            var seriesDto = new SeriesDto();
            seriesDto.setId(ser.getId());
            seriesDto.setName(ser.getName());
            seriesDto.setDeleted(ser.is_deleted());
            seriesDto.setActivated(ser.is_activated());
            seriesDtoList.add(seriesDto);
        }
        return seriesDtoList;
    }

    @Override
    public Series saveSeries(SeriesDto seriesDto) {
        try {
            Series series = new Series();
            series.setName(seriesDto.getName());
            series.set_activated(true);
            series.set_deleted(false);
            return seriesRepository.save(series);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }

    @Override
    public void deleteById(Long id) {
        Series series = seriesRepository.getReferenceById(id);
        series.set_deleted(true);
        series.set_activated(false);
        seriesRepository.save(series);
    }

    @Override
    public void enableById(Long id) {
        Series series = seriesRepository.getReferenceById(id);
        series.set_activated(true);
        series.set_deleted(false);
        seriesRepository.save(series);
    }

    @Override
    public Series update(SeriesDto seriesDto) {
        try {
            Series series = seriesRepository.getReferenceById(seriesDto.getId());
            series.setName(seriesDto.getName());
            return seriesRepository.save(series);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
