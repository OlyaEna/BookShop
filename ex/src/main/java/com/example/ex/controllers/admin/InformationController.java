package com.example.ex.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class InformationController {

    @GetMapping("/about")
    public String aboutUs() {
        return "information/about-us";
    }

    @GetMapping("/help")
    public String payAndQuestions() {
        return "information/delivery";
    }

    @GetMapping("/order-information")
    public String order() {
        return "information/order-information";
    }

}
