package com.example.ex.controllers;

import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.*;
import com.example.ex.service.*;
import lombok.RequiredArgsConstructor;
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
    private final CategoryService categoryService;
    private final SeriesService seriesService;

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
        List<Series> series = seriesService.findAll();
        List<Author> authors = authorService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Publisher> publishers = publisherService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("series", series);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
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
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/products";

    }

    //    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        List<Genre> genres = genreService.findAll();
//        List<Series> series = seriesService.findAll();
//        List<Author> authors = authorService.findAll();
//        List<Category> categories = categoryService.findAll();
//        List<Publisher> publishers = publisherService.findAll();
//        ProductDto productDto=productService.getById(id);
//        model.addAttribute("genres", genres);
//        model.addAttribute("series", series);
//        model.addAttribute("authors", authors);
//        model.addAttribute("categories", categories);
//        model.addAttribute("publishers", publishers);
//        model.addAttribute("productDto", productDto);
//        return "admin/product/update-product";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String processUpdate(@PathVariable("id") Long id,
//                                @ModelAttribute("productDto") ProductDto productDto,
//                                @RequestParam("imageProduct")MultipartFile imageProduct,
//                                RedirectAttributes attributes){
//        try {
//            productService.update(imageProduct, productDto);
//            attributes.addFlashAttribute("success", "Update successfully!");
//        }catch (Exception e){
//            e.printStackTrace();
//            attributes.addFlashAttribute("error", "Failed to update!");
//        }
//        return "redirect:/admin/products";
//
//    }
    @GetMapping("/update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update products");
        List<Genre> genres = genreService.findAll();
        List<Series> series = seriesService.findAll();
        List<Author> authors = authorService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Publisher> publishers = publisherService.findAll();
        ProductDto productDto = productService.getById(id);
        model.addAttribute("productDto", productDto);
        model.addAttribute("genres", genres);
        model.addAttribute("series", series);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);
        model.addAttribute("productDto", productDto);
        return "redirect:/admin/products";
    }


    @PostMapping("/update/{id}")
    public String processUpdate(@PathVariable("id") Long id,
                                @ModelAttribute("productDto") ProductDto productDto,
                                @RequestParam("imageProduct") MultipartFile imageProduct,
                                RedirectAttributes attributes
    ) {
        try {
            productService.update(imageProduct, productDto);
            attributes.addFlashAttribute("success", "Update successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update!");
        }
        return "redirect:/admin/products";

    }


//    @PostMapping("/save")
//    public ModelAndView createGenre(@ModelAttribute("product") ProductDto productDto,
//                                    @RequestParam("imageProduct") MultipartFile imageProduct,
//                                    @ModelAttribute("auth") ArrayList<Long> authorId,
//                                    RedirectAttributes redirectAttributes) {
//        try {
//            final List<Author> newAuthors =
//                    authorId.stream()
//                            .map(id -> authorRepository.getReferenceById(id))
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


}
