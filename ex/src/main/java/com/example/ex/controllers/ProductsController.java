package com.example.ex.controllers;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Category;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.AuthorRepository;
import com.example.ex.model.repository.PublisherRepository;
import com.example.ex.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")

public class ProductsController {
    private final ProductService productService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;


    @GetMapping()
    public String products(Model model) {
        var products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("size", products.size());
        return "admin/product/products";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Genre> genres = genreService.findAll();
        List<Author> authors = authorService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Publisher> publishers = publisherService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("publishers", publishers);
        model.addAttribute("categories", categories);
        model.addAttribute("product", new ProductDto());
        return "admin/product/create-product";
    }



    @PostMapping("/save")
    public String createGenre(@ModelAttribute("product") ProductDto productDto,
                              @RequestParam("imageProduct") MultipartFile imageProduct,
                              RedirectAttributes redirectAttributes) {
        try {
            productService.save(imageProduct, productDto);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/products";

    }



}
