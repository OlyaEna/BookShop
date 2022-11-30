package com.example.ex.controllers;

import com.example.ex.dto.GenreDto;
import com.example.ex.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class GenreUserController {
    private final GenreService genreService;

//    @GetMapping("/genres")
//    public String getGenres(Model model) {
//        List<GenreDto> genres = genreService.findAll();
//        model.addAttribute("genres", genres);
//        return "gen";
//    }


}
