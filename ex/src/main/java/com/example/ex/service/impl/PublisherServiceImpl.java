package com.example.ex.service.impl;

import com.example.ex.dto.PublisherDto;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.PublisherRepository;
import com.example.ex.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public List<PublisherDto> findAll() {
        List<Publisher> publishers = publisherRepository.findAll();
        List<PublisherDto> publisherDtoList = mapper(publishers);
        return publisherDtoList;
    }

    private List<PublisherDto> mapper(List<Publisher> publishers) {
        List<PublisherDto> publisherDtoList = new ArrayList<>();
        for (Publisher publisher : publishers) {
            var publisherDto = new PublisherDto();
            publisherDto.setId(publisher.getId());
            publisherDto.setName(publisher.getName());
            publisherDto.setDeleted(publisher.is_deleted());
            publisherDto.setActivated(publisher.is_activated());
            publisherDtoList.add(publisherDto);
        }
        return publisherDtoList;
    }

    @Override
    public Publisher savePublisher(PublisherDto publisherDto) {
        try {
            Publisher publisher = new Publisher();
            publisher.setName(publisherDto.getName());
            publisher.set_activated(true);
            publisher.set_deleted(false);
            return publisherRepository.save(publisher);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    //
    @Override
    public void deleteById(Long id) {
        Publisher publisher = publisherRepository.getReferenceById(id);
        publisher.set_activated(false);
        publisher.set_deleted(true);
        publisherRepository.save(publisher);

    }

    @Override
    public void enableById(Long id) {
        Publisher publisher = publisherRepository.getReferenceById(id);
        publisher.set_activated(true);
        publisher.set_deleted(false);
        publisherRepository.save(publisher);
    }

    @Override
    public PublisherDto findById(Long id) {
        Publisher publisher = publisherRepository.getReferenceById(id);
        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setId(publisher.getId());
        publisherDto.setName(publisher.getName());
        publisherDto.setDeleted(publisher.is_deleted());
        publisherDto.setActivated(publisher.is_activated());
        return publisherDto;
    }

    @Override
    public Publisher update(PublisherDto publisherDto) {
        try {
            Publisher publisher = publisherRepository.getReferenceById(publisherDto.getId());
            publisher.setName(publisherDto.getName());
            return publisherRepository.save(publisher);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}