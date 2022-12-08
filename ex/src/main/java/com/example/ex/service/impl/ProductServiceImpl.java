package com.example.ex.service.impl;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import com.example.ex.model.repository.ProductRepository;
import com.example.ex.utils.ImageUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import com.example.ex.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImageUpload imageUpload;

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }


    @Override
    public void deleteById(Long id) {
        Product product = productRepository.getReferenceById(id);
        product.set_deleted(true);
        product.set_activated(false);
        productRepository.save(product);
    }

    @Override
    public void enableById(Long id) {
        Product product = productRepository.getReferenceById(id);
        product.set_activated(true);
        product.set_deleted(false);
        productRepository.save(product);
    }

    @Override
    public void bestsellerById(Long id) {
        Product product = productRepository.getReferenceById(id);
        if (product != null) {
            if (product.isBestseller()) {
                product.setBestseller(false);
            } else {
                product.setBestseller(true);
            }
        }
        productRepository.save(product);
    }

    @Override
    public void noveltyById(Long id) {
        Product product = productRepository.getReferenceById(id);
        if (product != null) {
            if (product.isNovelty()) {
                product.setNovelty(false);
            } else {
                product.setNovelty(true);
            }
        }
        productRepository.save(product);
    }


    @Override
    public Page<ProductDto> pageProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        List<ProductDto> products = transfer(productRepository.findAll());
        Page<ProductDto> productPages = toPage(products, pageable);
        return productPages;
    }

    @Override
    public Page<ProductDto> searchProducts(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        List<ProductDto> productDtoList = transfer(productRepository.searchProductsList(keyword));
        Page<ProductDto> products = toPage(productDtoList, pageable);
        return products;
    }

    @Override
    public List<ProductDto> listProducts(String title) {
        List<Product> products = productRepository.findByTitle(title);
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductByGenre(Long id) {
        List<Product> products = productRepository.findProductsByGenre_Id(id);
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductsByAuthor(Long id) {
        List<Product> products = productRepository.findProductsByAuthor_Id(id);
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductsByCategory(Long id) {
        List<Product> products = productRepository.findProductsByCategory_Id(id);
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductsBySeries(Long id) {
        List<Product> products = productRepository.findProductsBySeries_Id(id);
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }


    private Page toPage(List<ProductDto> list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }

    @Override
    public Optional<Product> get(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductDto> selection() {
        List<Product> products = productRepository.selection();
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public void selectionById(Long id) {
        Product product = productRepository.getReferenceById(id);
        if (product != null) {
            if (product.isSelection()) {
                product.setSelection(false);
            } else {
                product.setSelection(true);
            }
        }
        productRepository.save(product);
    }


    @Override
    public Product save(MultipartFile imageProduct, ProductDto productDto) {
        try {
            Product product = new Product();
            if (imageProduct == null) {
                product.setImage(null);
            } else {
                imageUpload.uploadImage(imageProduct);
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            mapperTo(product, productDto);
            product.set_activated(true);
            product.set_deleted(false);
            product.setBestseller(false);
            product.setNovelty(true);
            product.setSelection(false);
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Product update(MultipartFile imageProduct, ProductDto productDto) {

        try {
            Product product = productRepository.getReferenceById(productDto.getId());
            if (imageProduct == null) {
                product.setImage(product.getImage());
            } else {
                if (imageUpload.checkExisted(imageProduct) == false) {
                    imageUpload.uploadImage(imageProduct);
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            mapperTo(product, productDto);
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.getReferenceById(id);
        ProductDto productDto = new ProductDto();
        mapper(product, productDto);
        return productDto;
    }

    private List<ProductDto> transfer(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            mapper(product, productDto);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    private void mapper(Product product, ProductDto productDto) {
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setAuthor(product.getAuthor());
        productDto.setGenre(product.getGenre());
        productDto.setSeries(product.getSeries());
        productDto.setISBN(product.getISBN());
        productDto.setImage(product.getImage());
        productDto.setCategory(product.getCategory());
        productDto.setPublisher(product.getPublisher());
        productDto.setDeleted(product.is_deleted());
        productDto.setActivated(product.is_activated());
        productDto.setBestseller(product.isBestseller());
        productDto.setNovelty(product.isNovelty());
        productDto.setSelection(product.isSelection());
    }

    private void mapperTo(Product product, ProductDto productDto) {
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAuthor(productDto.getAuthor());
        product.setGenre(productDto.getGenre());
        product.setISBN(productDto.getISBN());
        product.setSeries(productDto.getSeries());
        product.setCategory(productDto.getCategory());
        product.setPublisher(productDto.getPublisher());

    }

    @Override
    public List<ProductDto> listViewProducts() {
        List<Product> products = productRepository.listViewProducts();
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> listNewProducts() {
        List<Product> products = productRepository.listNewProducts();
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> bestseller() {
        List<Product> products = productRepository.bestseller();
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> novelty() {
        List<Product> products = productRepository.novelty();
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> exampleProducts() {
        List<Product> products = productRepository.exampleProducts();
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }

    @Override
    public List<ProductDto> search(String keyword) {
        List<Product> products = productRepository.search(keyword);
        List<ProductDto> productDtoList = transfer(products);
        return productDtoList;
    }
}

