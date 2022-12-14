package com.example.ex.service;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAll();

    Product save(MultipartFile imageProduct, ProductDto productDto);

    Product update(MultipartFile imageProduct, ProductDto productDto);

    ProductDto getById(Long id);

    void deleteById(Long id);

    void enableById(Long id);

    Page<ProductDto> pageProducts(int pageNo);

    Page<ProductDto> searchProducts(int pageNo, String keyword);

    List<ProductDto> listProducts(String title);

    List<ProductDto> findProductByGenre(Long id);

    List<ProductDto> findProductsByAuthor(Long id);

    List<ProductDto> findProductsByCategory(Long id);

    List<ProductDto> findProductsBySeries(Long id);

    void noveltyById(Long id);

    void bestsellerById(Long id);

    List<ProductDto> listViewProducts();

    List<ProductDto> listNewProducts();

    List<ProductDto> bestseller();

    List<ProductDto> novelty();

    List<ProductDto> exampleProducts();

    List<ProductDto> search(String keyword);

    Optional<Product> get(Long id);

    List<ProductDto> selection();

    void selectionById(Long id);


}
