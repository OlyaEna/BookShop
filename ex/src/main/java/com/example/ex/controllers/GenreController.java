package com.example.ex.controllers;

import com.example.ex.dto.GenreDto;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.repository.GenreRepository;
import com.example.ex.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping()
    public String genres(Model model) {
        List<GenreDto> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("size", genres.size());
        model.addAttribute("title", "Genre");
        model.addAttribute("newGenre", new GenreDto());
        return "admin/genre/genres";
    }

    @PostMapping("/create")
    public String createGenre(@ModelAttribute("newGenre") GenreDto genreDto, RedirectAttributes redirectAttributes) {
        try {
            genreService.saveGenre(genreDto);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("duplicatedFailed", "Duplicated name");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable(value = "id") Long id) {
        genreService.deleteGenre(id);
        return "redirect:/admin/genres";
    }
//    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
//    @ResponseBody
//    public Genre findById(Long id) {
//        return genreService.findById(id);
//    }
//
//
//    @GetMapping("/update-genre")
//    public String update(Genre genre, RedirectAttributes attributes){
//        try {
//            genreService.update(genre);
//            attributes.addFlashAttribute("success","Updated successfully");
//        }catch (DataIntegrityViolationException e){
//            e.printStackTrace();
//            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
//        }catch (Exception e){
//            e.printStackTrace();
//            attributes.addFlashAttribute("failed", "Error server");
//        }
//        return "redirect:/genres";
//    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        GenreDto genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        return "admin/genre/update-genre";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, GenreDto genre, RedirectAttributes attributes) {
        try {
            genreService.update(genre);
            attributes.addFlashAttribute("success", "Updated successfully");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
            return "redirect:/admin/genres";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
            return "redirect:/admin/genres";
        }
        return "redirect:/admin/genres";
    }

    @RequestMapping(value = "/enable-genre/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enabledAuthor(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            genreService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enabled!");
        }
        return "redirect:/admin/genres";
    }

    @RequestMapping(value = "/delete-genre/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deletedAuthor(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            genreService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to deleted");
        }
        return "redirect:/admin/genres";
    }

}
