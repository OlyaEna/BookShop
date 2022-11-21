package com.example.ex.controllers;

import com.example.ex.model.entity.Genre;
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
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genres")
    public String genres(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("size", genres.size());
        model.addAttribute("title", "Genre");
        model.addAttribute("newGenre", new Genre());
        return "genres";
    }

    @PostMapping("/genres/create")
    public String createGenre(@ModelAttribute("newGenre") Genre genre, RedirectAttributes redirectAttributes) {
        try {
            genreService.saveGenre(genre);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("duplicatedFailed", "Duplicated name");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/genres";
    }

    @GetMapping("/genres/delete/{id}")
    public String deleteGenre(@PathVariable(value = "id") Long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres";
    }
    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Genre findById(Long id){
        return genreService.findById(id);
    }

    @GetMapping("/update-genre")
    public String update(Genre genre, RedirectAttributes attributes){
        try {
            genreService.update(genre);
            attributes.addFlashAttribute("success","Updated successfully");
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
        }
        return "redirect:/genres";
    }


}
