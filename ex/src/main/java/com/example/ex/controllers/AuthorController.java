package com.example.ex.controllers;

import com.example.ex.dto.AuthorDto;
import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Series;
import com.example.ex.model.repository.AuthorRepository;
import com.example.ex.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @GetMapping()
    public String authors(Model model) {
        var authors = authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("size", authors.size());
        model.addAttribute("title", "Author");
        model.addAttribute("newAuthor", new AuthorDto());
        return "admin/author/authors";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute("newAuthor") AuthorDto author, RedirectAttributes redirectAttributes) {
        try {
            authorService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("duplicatedFailed", "Duplicated name");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/authors";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        AuthorDto author = authorService.findById(id);
        model.addAttribute("author", author);
        return "admin/author/update-author";
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable("id") long id, AuthorDto author, RedirectAttributes attributes) {
        try {
            authorService.update(author);
            attributes.addFlashAttribute("success", "Updated successfully");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
            return "redirect:/admin/authors";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
            return "redirect:/admin/authors";
        }
        return "redirect:/admin/authors";
    }

        @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable(value = "id") Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/admin/authors";
    }

    @RequestMapping(value = "/enable-author/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enabledAuthor(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            authorService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enabled!");
        }
        return "redirect:/admin/authors";
    }

    @RequestMapping(value = "/delete-author/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deletedAuthor(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            authorService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to deleted");
        }
        return "redirect:/admin/authors";
    }

    @GetMapping("/{pageNo}")
    public String page(@PathVariable("pageNo") int pageNo, Model model){
        Page<AuthorDto> authors = authorService.page(pageNo);
        model.addAttribute("size", authors.getSize());
        model.addAttribute("totalPages", authors.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("authors", authors);
        return "admin/author/authors";
    }

    @GetMapping("/search-result/{pageNo}")
    public String search(@PathVariable("pageNo")int pageNo,
                                 @RequestParam("keyword") String keyword,
                                 Model model){
        Page<AuthorDto> authors = authorService.search(pageNo, keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("products", authors);
        model.addAttribute("size", authors.getSize());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", authors.getTotalPages());
        return "/admin/author/search-authors";
    }
}
