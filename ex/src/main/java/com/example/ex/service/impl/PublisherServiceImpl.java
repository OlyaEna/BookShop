package com.example.ex.service.impl;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.PublisherRepository;
import com.example.ex.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

//    @Override
//    public Product save(MultipartFile imageProduct, ProductDto productDto) {
//        try {
//            Product product = new Product();
//            if (imageProduct == null) {
//                product.setImage(null);
//            } else {
//                imageUpload.uploadImage(imageProduct);
//                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
//            }
//            product.setTitle(productDto.getTitle());
//            product.setDescription(productDto.getDescription());
//            product.setPrice(productDto.getPrice());
//            product.setPublishers(productDto.getPublishers());
//            product.setAuthors(productDto.getAuthors());
//            product.setGenres(productDto.getGenres());
//            product.setISBN(productDto.getISBN());
//            return productRepository.save(product);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//
//        }
//
//    }

//    private ProductDto mapToProductDto(Product product) {
//        ProductDto productDto = new ProductDto();
//        productDto.setId(product.getId());
//        productDto.setTitle(product.getTitle());
//        productDto.setDescription(product.getDescription());
//        productDto.setPrice(product.getPrice());
//        productDto.setPublishers(product.getPublishers());
//        productDto.setAuthors(product.getAuthors());
//        productDto.setGenres(product.getGenres());
//        productDto.setImage(product.getImage());
//        productDto.setActivated(product.is_activated());
//        productDto.setDeleted(product.is_deleted());
//        productDto.setISBN(product.getISBN());
//        return productDto;
//    }
}