package com.example.ex.services;


import com.example.ex.dto.PublisherDto;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.PublisherRepository;
import com.example.ex.service.impl.PublisherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;
    @InjectMocks
    private PublisherServiceImpl publisherService;

    @BeforeEach
    void setUp() {
        this.publisherService
                = new PublisherServiceImpl(this.publisherRepository);
    }

    @Test
    void getAllPublishers() {
        publisherService.findAll();
        verify(publisherRepository).findAll();
    }

    @Test
    public void getAllPublishersTest() {
        List<Publisher> list = new ArrayList<>();
        Publisher empOne = new Publisher(6L, "Glob", false, true);
        Publisher empTwo = new Publisher(7L, "Enter", false, true);
        Publisher empThree = new Publisher(8L, "Белпечать", false, true);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(publisherRepository.findAll()).thenReturn(list);

        //test
        List<PublisherDto> empList = publisherService.findAll();

        assertEquals(3, empList.size());
        verify(publisherRepository, times(1)).findAll();
    }

    @Test
    public void getPublisherByIdTest() {
        when(publisherRepository.getReferenceById(1L)).thenReturn(new Publisher(1L, "OAP", false, true));

        PublisherDto pub = publisherService.findById(1L);

        assertEquals("OAP", pub.getName());
        assertEquals(false, pub.isDeleted());
        assertEquals(true, pub.isActivated());
    }


    @Test
    public void deletePublisherByIdTest() {
        final Long id = 1L;
        publisherService.deletePublisher(1L);
        publisherService.deletePublisher(1L);
        verify(publisherRepository, times(2)).deleteById(id);

    }

    @Test
    public void updatePublisherByIdTest() {

    }
}
