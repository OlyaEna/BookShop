package com.example.ex.controllers;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.AuthorRepository;
import com.example.ex.model.repository.PublisherRepository;
import com.example.ex.service.AuthorService;
import com.example.ex.service.GenreService;
import com.example.ex.service.ProductService;
import com.example.ex.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")

public class ProductsController {
    private final ProductService productService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

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
        List<Publisher> publishers = publisherService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("publishers", publishers);
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
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("duplicatedFailed", "Duplicated name");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/products";
//    }
    }
}
