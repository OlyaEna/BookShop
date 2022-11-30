package com.example.ex.controllers.admin;

import com.example.ex.dto.SeriesDto;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.entity.Series;
import com.example.ex.model.repository.SeriesRepository;
import com.example.ex.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/series")
public class SeriesController {
    private final SeriesService seriesService;

    @GetMapping()
    public String series(Model model) {
        List<SeriesDto> series = seriesService.findAll();
        model.addAttribute("series", series);
        model.addAttribute("size", series.size());
        model.addAttribute("title", "Series");
        model.addAttribute("newSeries", new SeriesDto());
        return "admin/series/series";
    }

    @PostMapping("/create")
    public String createSeries(@ModelAttribute("newSeries") SeriesDto series, RedirectAttributes redirectAttributes) {
        try {
            seriesService.saveSeries(series);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("duplicatedFailed", "Duplicated name");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed");
        }
        return "redirect:/admin/series";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        SeriesDto series = seriesService.findById(id);
        model.addAttribute("series", series);
        return "admin/series/update-series";
    }

    @PostMapping("/update/{id}")
    public String updateSeries(@PathVariable("id") long id, SeriesDto series, RedirectAttributes attributes) {
        try {
            seriesService.update(series);
            attributes.addFlashAttribute("success", "Updated successfully");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Failed to update because duplicate name");
            return "redirect:/admin/series";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("failed", "Error server");
            return "redirect:/admin/series";
        }
        return "redirect:/admin/series";
    }

    @GetMapping("/delete/{id}")
    public String deleteSeries(@PathVariable(value = "id") Long id) {
        seriesService.deleteSeries(id);
        return "redirect:/admin/series";
    }

    @RequestMapping(value = "/enable-series/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enabledSeries(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            seriesService.enableById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enabled!");
        }
        return "redirect:/admin/series";
    }

    @RequestMapping(value = "/delete-series{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deletedSeries(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            seriesService.deleteById(id);
            attributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to deleted");
        }
        return "redirect:/admin/series";
    }
}

