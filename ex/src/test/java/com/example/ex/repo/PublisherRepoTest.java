package com.example.ex.repo;

import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.AuthorRepository;
import com.example.ex.model.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest

public class PublisherRepoTest {

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    void isPublisherExitsById() {
        Publisher publisher = new Publisher(5L, "Акта", false, true);
        publisherRepository.save(publisher);
        Boolean actualResult = publisherRepository.existsById(5L);
        assertThat(actualResult).isTrue();
    }

}
