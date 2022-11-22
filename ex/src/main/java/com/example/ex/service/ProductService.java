package com.example.ex.service;
import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    void enableProduct(Long id);

    void deleteProduct(Long id);

    Product save(ProductDto productDTO);

    Product update(ProductDto productDTO);


}
