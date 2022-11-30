package com.example.ex.controllers.admin;

import com.example.ex.dto.PublisherDto;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.PublisherRepository;
import com.example.ex.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/publishers")
public class PublisherController {
    private final PublisherService publisherService;
    private final PublisherRepository publisherRepository;

    @GetMapping()
    public String publishers(Model model) {
        List<PublisherDto> publishers = publisherService.findAll();
        model.addAttribute("publishers", publishers);
        model.addAttribute("size", publishers.size());
        model.addAttribute("title", "Publisher");
        model.addAttribute("newPublisher", new PublisherDto());
        return "admin/publisher/publishers";
    }

    @PostMapping("/create")
    public String createPublisher(@ModelAttribute("newPublisher") PublisherDto publisher, RedirectAttributes redirectAttributes) {
        try {
            publisherService.savePublisher(publisher);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("duplicatedFailed", "Duplicated name");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/publishers";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        PublisherDto publisher = publisherService.findById(id);
        model.addAttribute("publisher", publisher);
        return "admin/publisher/update-publisher";
    }

    @PostMapping("/update/{id}")
    public String updatePublisher(@PathVariable("id") long id, PublisherDto publisher, RedirectAttributes attributes) {
        try {
            publisherService.update(publisher);
            attributes.addFlashAttribute("success", "Updated successfully");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
            return "redirect:/admin/publishers";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
            return "redirect:/admin/publishers";
        }
        return "redirect:/admin/publishers";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable(value = "id") Long id) {
        publisherService.deletePublisher(id);
        return "redirect:/admin/publishers";
    }

    //    @RequestMapping(value = "/delete-publisher/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
//    public String delete(Long id, RedirectAttributes attributes) {
//        try {
//            publisherService.deleteById(id);
//            attributes.addFlashAttribute("success", "Deleted successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//            attributes.addFlashAttribute("failed", "Failed to deleted");
//        }
//        return "redirect:/admin/publishers";
//    }
//
//    @RequestMapping(value = "/enable-publisher{id}", method = {RequestMethod.PUT, RequestMethod.GET})
//    public String enable(Long id, RedirectAttributes attributes) {
//        try {
//            publisherService.enabledById(id);
//            attributes.addFlashAttribute("success", "Enabled successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//            attributes.addFlashAttribute("failed", "Failed to enabled");
//        }
//        return "redirect:/admin/publishers";
//    }
    @RequestMapping(value = "/enable-publisher/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enabledPublisher(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            publisherService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enabled!");
        }
        return "redirect:/admin/publishers";
    }

    @RequestMapping(value = "/delete-publisher/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deletedPublisher(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            publisherService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to deleted");
        }
        return "redirect:/admin/publishers";
    }
}
