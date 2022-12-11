package com.example.ex.controllers.user;

import com.example.ex.dto.MessageDto;
import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.User;
import com.example.ex.service.MessageService;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/support")
    public String support(Model model) {
        model.addAttribute("message", new MessageDto());
        return "user/support";
    }

    @PostMapping("/support")
    public String createMessage(MessageDto messageDto, Model model, Principal principal,
                                RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findUserByEmail(principal.getName());
            model.addAttribute("user", user);
            messageService.save(messageDto, user);
            redirectAttributes.addFlashAttribute("success", "Send successfully");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/support";

    }
    @GetMapping("/user/my-messages")
    public String userMessages(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("messages", messageService.getUserMessage(principal));
        return "user/my-messages";
    }
    @GetMapping("/message-details/{id}")
    public String messageDetails(Model model, @PathVariable("id") Long id, Principal principal) {
        MessageDto selectedMessage = messageService.getMessageById(id);
        model.addAttribute("selectedMessage", selectedMessage);
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user/message-details";
    }



}
