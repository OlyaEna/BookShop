package com.example.ex.service.impl;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Image;
import com.example.ex.model.entity.Product;
import com.example.ex.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ex.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> findAll() {
        var products = productRepository.findAll();
        var productsDto = new ArrayList<ProductDto>();
        products.forEach(product -> {
            var productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setPublisher(product.getPublishers());
            productDto.setAuthors(product.getAuthors());
            productDto.setGenres(product.getGenres());
            productDto.setImage(product.getImage());
            productDto.setActivated(product.is_activated());
            productDto.setDeleted(product.is_deleted());
            productDto.setISBN(product.getISBN());
            productsDto.add(productDto);
        });
        return productsDto;
    }

    @Override
    public void enableProduct(Long id) {

    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product save(ProductDto productDTO) {
        return null;
    }

    @Override
    public Product update(ProductDto productDTO) {
        return null;
    }

}