package com.example.ex.controllers;

import com.example.ex.dto.GenreDto;
import com.example.ex.model.entity.Product;
import com.example.ex.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class GenreUserController {
    private final GenreService genreService;




}
