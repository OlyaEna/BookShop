package com.example.ex.controllers.user;

import com.example.ex.dto.*;
import com.example.ex.model.repository.ProductRepository;
import com.example.ex.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")

public class BooksController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final SeriesService seriesService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping()
    public String books(Model model) {
        List<ProductDto> productDto = productService.findAll();
        model.addAttribute("productDto", productDto);

        return "books/books";
    }


    @GetMapping("/{id}")
    public String productInfo(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        List<ProductDto> exampleProducts = productService.exampleProducts();
        model.addAttribute("exampleProducts", exampleProducts);
        ProductDto product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("user", userService.findUserByEmail(principal.getName()));
        return "books/item";
    }

    @GetMapping("/genre/{id}")
    public String getProductsInGenre(@PathVariable("id") Long genreId, Model model) {
        GenreDto genre = genreService.findById(genreId);
        List<GenreDto> genres = genreService.findAll();
        List<ProductDto> products = productService.findProductByGenre(genreId);
        model.addAttribute("products", products);
        model.addAttribute("genre", genre);
        model.addAttribute("genres", genres);
        return "books/products-in-genre";
    }

    @GetMapping("/author/{id}")
    public String getProductsInAuthor(@PathVariable("id") Long authorId, Model model) {
        AuthorDto author = authorService.findById(authorId);
        List<AuthorDto> authors = authorService.findAll();
        List<ProductDto> products = productService.findProductsByAuthor(authorId);
        model.addAttribute("products", products);
        model.addAttribute("author", author);
        model.addAttribute("authors", authors);
        return "books/products-in-author";
    }

    @GetMapping("/category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId, Model model) {
        CategoryDto category = categoryService.findById(categoryId);
        List<CategoryDto> categories = categoryService.findAll();
        List<ProductDto> products = productService.findProductsByCategory(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        return "books/products-in-category";
    }

    @GetMapping("/series/{id}")
    public String getProductsInSeries(@PathVariable("id") Long seriesId, Model model) {
        SeriesDto ser = seriesService.findById(seriesId);
        List<SeriesDto> series = seriesService.findAll();
        List<ProductDto> products = productService.findProductsBySeries(seriesId);
        model.addAttribute("products", products);
        model.addAttribute("ser", ser);
        model.addAttribute("series", series);
        return "books/products-in-series";
    }

    @GetMapping("/bestseller")
    public String bestseller(Model model) {
        List<ProductDto> bestseller = productService.bestseller();
        model.addAttribute("bestseller", bestseller);
        return "books/bestseller";
    }

    @GetMapping("/christmas")
    public String christmas(Model model) {
        List<ProductDto> christmas = productService.selection();
        model.addAttribute("christmas", christmas);
        return "books/christmas";
    }

    @GetMapping("/new")
    public String novelty(Model model) {
        List<ProductDto> novelty = productService.novelty();
        model.addAttribute("novelty", novelty);
        return "books/novelty";
    }

    @GetMapping("/search/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model) {
        Page<ProductDto> products = productService.pageProducts(pageNo);
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        return "books/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<ProductDto> search = productService.search(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("search", search);
        return "books/search";
    }


}
