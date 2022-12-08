package com.example.ex.services;

import com.example.ex.dto.PublisherDto;
import com.example.ex.dto.SeriesDto;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.entity.Series;
import com.example.ex.model.repository.PublisherRepository;
import com.example.ex.model.repository.SeriesRepository;
import com.example.ex.service.impl.PublisherServiceImpl;
import com.example.ex.service.impl.SeriesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class SeriesServiceTest {
    @Mock
    private SeriesRepository seriesRepository;
    @InjectMocks
    private SeriesServiceImpl seriesService;


    @BeforeEach
    void setUp() {
        this.seriesService
                = new SeriesServiceImpl(this.seriesRepository);
    }

    @Test
    public void getAllSeriesTest() {
        List<Series> list = new ArrayList<>();
        Series one = new Series(6L, "One", false, true);
        Series two = new Series(7L, "Two", false, true);
        Series three = new Series(8L, "Three", false, true);

        list.add(one);
        list.add(two);
        list.add(three);

        when(seriesRepository.findAll()).thenReturn(list);

        //test
        List<SeriesDto> dtoList = seriesService.findAll();

        assertEquals(3, dtoList.size());
        verify(seriesRepository, times(1)).findAll();
    }

    @Test
    public void getSeriesByIdTest() {
        when(seriesRepository.getReferenceById(1L)).thenReturn(new Series(6L, "One", false, true));

        SeriesDto seriesDto = seriesService.findById(1L);

        assertEquals("One", seriesDto.getName());
        assertEquals(false, seriesDto.isDeleted());
        assertEquals(true, seriesDto.isActivated());
    }


    @Test
    public void deleteSeriesByIdTest() {
        final Long id = 1L;
        seriesService.deleteSeries(1L);
        seriesService.deleteSeries(1L);
        verify(seriesRepository, times(2)).deleteById(id);

    }

    @Test
    public void createSeries() {
       Series series = new Series(6L, "One", false, true);
        seriesRepository.save(series);
        assertThat(series.getId()).isNotNull();
    }


}
