package com.example.ex.controllers;

import com.example.ex.dto.*;
import com.example.ex.model.entity.User;
import com.example.ex.service.OrderService;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "user/registrationForm";
    }

    @PostMapping("/registration/save")
    public String registration(@Valid UserDto userDto, @RequestParam("imageProduct") MultipartFile imageProduct,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/user/registrationForm";
        }
        userService.saveUser(imageProduct, userDto);
        return "redirect:/login";
    }

    @GetMapping("/user/update")
    public String showUserForm(Principal principal, Model model) {
        User existingUser = userService.findUserByEmail(principal.getName());
        model.addAttribute("existingUser", existingUser);
        return "user/update-user";
    }


    @PostMapping("/user/update")
    public String processUpdate(UserDto userDto, @RequestParam("imageProduct") MultipartFile imageProduct) {
        try {
            userService.update(imageProduct, userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/personal";
    }

    @GetMapping("/user/personal")
    public String personalInformation(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user/personal";
    }

    @GetMapping("/user/ban")
    public String banInformation() {
        return "/information/ban";
    }


}
