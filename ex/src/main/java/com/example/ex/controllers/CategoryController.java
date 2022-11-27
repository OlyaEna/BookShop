package com.example.ex.controllers;

import com.example.ex.model.entity.Category;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.CategoryRepository;
import com.example.ex.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping()
    public String publishers(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("size", categories.size());
        model.addAttribute("title", "Category");
        model.addAttribute("newCategory", new Category());
        return "admin/category/categories";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute("newCategory") Category category, RedirectAttributes redirectAttributes) {
        try {
            categoryService.saveCategory(category);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("duplicatedFailed", "Duplicated name");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));

        model.addAttribute("category", category);
        return "admin/category/update-category";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") long id, Category category, RedirectAttributes attributes) {
        try {
            categoryService.saveCategory(category);
            attributes.addFlashAttribute("success", "Updated successfully");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
            return "redirect:/admin/categories";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
            return "redirect:/admin/categories";
        }
        return "redirect:/admin/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(value = "id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

}
