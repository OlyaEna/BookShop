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

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.example.ex.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImageUpload imageUpload;

    @Override
    public List<ProductDto> findAll() {
//        List<ProductDto> productDtoList = new ArrayList<>();
//        var products = productRepository.findAll();
//        for (Product product: products) {
//            ProductDto productDto = new ProductDto();
//            productDto.setId(product.getId());
//            productDto.setTitle(product.getTitle());
//            productDto.setDescription(product.getDescription());
//            productDto.setPrice(product.getPrice());
////            productDto.setPublishers(product.getPublishers());
//            productDto.setAuthors(product.getAuthors());
//            productDto.setGenres(product.getGenres());
//            productDto.setImage(product.getImage());
////            productDto.setActivated(product.is_activated());
////            productDto.setDeleted(product.is_deleted());
//            productDto.setISBN(product.getISBN());
//            productDtoList.add(productDto);
//        }
//        return productDtoList;
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
    public Page<ProductDto> pageProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<ProductDto> products = transfer(productRepository.findAll());
        Page<ProductDto> productPages = toPage(products, pageable);
        return productPages;
    }

    @Override
    public Page<ProductDto> searchProducts(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo, 5);
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


//    public List<UserDto> findAllUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream()
//                .map((user) -> mapToUserDto(user))
//                .collect(Collectors.toList());
//    }
//    private UserDto mapToUserDto(User user){
//        UserDto userDto = new UserDto();
//        String[] str = user.getName().split(" ");
//        userDto.setFirstName(str[0]);
//        userDto.setLastName(str[1]);
//        userDto.setEmail(user.getEmail());
//        return userDto;
//    }

//    private ProductDto mapToProductDto(Product product){
//        ProductDto productDto=new ProductDto();
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
//        return  productDto;
//    }


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
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setAuthor(productDto.getAuthor());
            product.setGenre(productDto.getGenre());
            product.setISBN(productDto.getISBN());
            product.setSeries(productDto.getSeries());
            product.setCategory(productDto.getCategory());
            product.setPublisher(productDto.getPublisher());
            product.set_activated(true);
            product.set_deleted(false);
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @PostMapping("/save")
//    public ModelAndView createGenre(@ModelAttribute("product") ProductDto productDto,
//                                    @RequestParam("imageProduct") MultipartFile imageProduct,
//                                    @RequestParam(value = "newAuthors") ArrayList<Long> authors,
//                                    RedirectAttributes redirectAttributes) {
//        try {
//            final List<Author> newAuthors =
//                    authors.stream()
//                            .map(id -> authorService.findById(id))
//                            .collect(Collectors.toList());
//            productDto.setAuthors(newAuthors);
//            productService.save(imageProduct, productDto);
//            redirectAttributes.addFlashAttribute("success", "Added successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("failed", "Failed");
//        }
//        return new ModelAndView("redirect:/admin/products");
//
//    }

//    @Override
//    public void enableProduct(Long id) {
//
//    }
//
//    @Override
//    public void deleteProduct(Long id) {
//
//    }


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
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setAuthor(productDto.getAuthor());
            product.setGenre(productDto.getGenre());
            product.setISBN(productDto.getISBN());
            product.setSeries(productDto.getSeries());
            product.setCategory(productDto.getCategory());
            product.setPublisher(productDto.getPublisher());
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
        return productDto;
    }

    private List<ProductDto> transfer(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
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
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

}