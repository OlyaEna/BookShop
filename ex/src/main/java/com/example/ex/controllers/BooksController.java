package com.example.ex.controllers;

import com.example.ex.dto.*;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import com.example.ex.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")

public class BooksController {
    private final ProductService productService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final SeriesService seriesService;
    private final CategoryService categoryService;

    @GetMapping()
    public String books(Model model) {
        List<ProductDto> productDto = productService.findAll();
        model.addAttribute("productDto", productDto);
        return "books";
    }

    @GetMapping("/{id}")
    public String productInfo(@PathVariable(value = "id") Long id, Model model) {
        ProductDto product = productService.getById(id);
        model.addAttribute("product", product);
        return "item";
    }

    @GetMapping("/genre/{id}")
    public String getProductsInGenre(@PathVariable("id") Long genreId, Model model) {
        GenreDto genre = genreService.findById(genreId);
        List<GenreDto> genres = genreService.findAll();
        List<ProductDto> products = productService.findProductByGenre(genreId);
        model.addAttribute("products", products);
        model.addAttribute("genre", genre);
        model.addAttribute("genres", genres);
        return "products-in-genre";
    }

    @GetMapping("/author/{id}")
    public String getProductsInAuthor(@PathVariable("id") Long authorId, Model model) {
        AuthorDto author = authorService.findById(authorId);
        List<AuthorDto> authors = authorService.findAll();
        List<ProductDto> products = productService.findProductsByAuthor(authorId);
        model.addAttribute("products", products);
        model.addAttribute("author", author);
        model.addAttribute("authors", authors);
        return "products-in-author";
    }

    @GetMapping("/category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId, Model model) {
        CategoryDto category = categoryService.findById(categoryId);
        List<CategoryDto> categories = categoryService.findAll();
        List<ProductDto> products = productService.findProductsByCategory(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        return "products-in-category";
    }

    @GetMapping("/series/{id}")
    public String getProductsInSeries(@PathVariable("id") Long seriesId, Model model) {
        SeriesDto ser = seriesService.findById(seriesId);
        List<SeriesDto> series = seriesService.findAll();
        List<ProductDto> products = productService.findProductsBySeries(seriesId);
        model.addAttribute("products", products);
        model.addAttribute("ser", ser);
        model.addAttribute("series", series);
        return "products-in-series";
    }

}
