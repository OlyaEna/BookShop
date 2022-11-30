package com.example.ex.controllers.admin;

import com.example.ex.dto.*;
import com.example.ex.model.entity.*;
import com.example.ex.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
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
        List<GenreDto> genres = genreService.findAll();
        List<SeriesDto> series = seriesService.findAll();
        List<AuthorDto> authors = authorService.findAll();
        List<CategoryDto> categories = categoryService.findAll();
        List<PublisherDto> publishers = publisherService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("series", series);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);
        model.addAttribute("product", new ProductDto());
        return "admin/product/create-product";
    }


    @PostMapping("/save")
    public String createProduct(@ModelAttribute("product") ProductDto productDto,
                                @RequestParam("imageProduct") MultipartFile imageProduct,
                                RedirectAttributes redirectAttributes) {
        try {
            productService.save(imageProduct, productDto);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/products/0";

    }

    @GetMapping("/update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        List<GenreDto> genres = genreService.findAll();
        List<SeriesDto> series = seriesService.findAll();
        List<AuthorDto> authors = authorService.findAll();
        List<CategoryDto> categories = categoryService.findAll();
        List<PublisherDto> publishers = publisherService.findAll();
        ProductDto productDto = productService.getById(id);
        model.addAttribute("productDto", productDto);
        model.addAttribute("genres", genres);
        model.addAttribute("series", series);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);
        model.addAttribute("productDto", productDto);
        return "admin/product/update-product";
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
        return "redirect:/admin/products/0";
    }

    @GetMapping("/{pageNo}")
    public String productsPage(@PathVariable("pageNo") int pageNo, Model model) {
        Page<ProductDto> products = productService.pageProducts(pageNo);
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("products", products);
        return "admin/product/products";
    }


    @RequestMapping(value = "/enable-product/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enabledProduct(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            productService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enabled!");
        }
        return "redirect:/admin/products/0";
    }

    @RequestMapping(value = "/delete-product/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deletedProduct(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            productService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to deleted");
        }
        return "redirect:/admin/products/0";
    }

    @GetMapping("/search-result/{pageNo}")
    public String searchProducts(@PathVariable("pageNo") int pageNo,
                                 @RequestParam("keyword") String keyword,
                                 Model model) {
        Page<ProductDto> products = productService.searchProducts(pageNo, keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", products.getTotalPages());
        return "/admin/product/search-products";
    }

    @PostMapping("/bestseller/{id}")
    public String bestseller(@PathVariable("id") Long id) {
        productService.bestsellerById(id);
        return "redirect:/admin/products/0";
    }

    @PostMapping("/novelty/{id}")
    public String novelty(@PathVariable("id") Long id) {
        productService.noveltyById(id);
        return "redirect:/admin/products/0";
    }


}
