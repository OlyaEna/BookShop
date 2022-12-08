package com.example.ex.controllers.admin;

import com.example.ex.dto.MessageDto;
import com.example.ex.model.entity.User;
import com.example.ex.service.MessageService;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMessagesController {
    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/messages")
    public String showMessagesAdmin(Model model) {
        model.addAttribute("messages", messageService.getAllMessages());
        return "admin/messages-admin";
    }

    @RequestMapping(value = "/read-message/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String readMessage(@PathVariable("id") Long id, RedirectAttributes attributes, HttpServletRequest request) {
        try {
            messageService.readById(id);
            attributes.addFlashAttribute("success", "Read successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to read!");
        }
        return getPreviousPageByRequest(request).orElse("/");
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    @GetMapping("/message-details/{id}")
    public String messageDetails(Model model, @PathVariable("id") Long id, Principal principal) {
        MessageDto selectedMessage = messageService.getMessageById(id);
        model.addAttribute("selectedMessage", selectedMessage);
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "admin/message-details";
    }
}
