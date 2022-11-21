package com.example.ex.service;

import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> listProducts(String title);

    void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3, Author author) throws IOException;

    void deleteProduct(Long id);

    Product getProductById(Long id);

}
