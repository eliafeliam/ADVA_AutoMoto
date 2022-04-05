package com.adva.project.controller;

import com.adva.project.model.*;
import com.adva.project.repository.TransportRepository;
import com.adva.project.utilities.CSVAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/announcement")
public class TransportController {

    static List<? extends Announcement> foundList;

    private final TransportRepository transportRepository;

    final CSVAssistant csvAssistant;

    @Autowired
    public TransportController(TransportRepository transportRepository, CSVAssistant csvAssistant) {
        this.transportRepository = transportRepository;
        this.csvAssistant = csvAssistant;
    }

    @GetMapping()
    private String getAll(Model model) {
        globalModelAttributes(model);
        model.addAttribute("announcementList", transportRepository.findAll());
        return "main/startPage";
    }
    @GetMapping("/userAnnouncement")
    private String getAllForUser(@ModelAttribute("user") String email, Model model) {
        globalModelAttributes(model);
        model.addAttribute("announcementList", transportRepository.findByEmail(email));
        model.addAttribute("likeUser", true);
        return "employee/allProducts";
    }
    @GetMapping("/forEmployee")
    private String forAdmin(Model model) {
        globalModelAttributes(model);
        model.addAttribute("announcementList", transportRepository.findAll());
        return "employee/allProducts";
    }

    @GetMapping("/{id}")
    private String getById(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        model.addAttribute("selectedElement", transportRepository.getById(id));
        return "main/show";
    }

    @GetMapping("/new")
    private String newAnnouncement(@ModelAttribute("announcement") TransportImpl transport) {
        return "employee/new";
    }

    @PostMapping()
    private String create(@ModelAttribute("announcement") @Valid TransportImpl announcement,
                          BindingResult bindingResult,HttpServletRequest request) {
        String userEmail = request.getUserPrincipal().getName();
        if (bindingResult.hasErrors()) {
            return "employee/new";
        } else {
            announcement.setEmail(userEmail);
            transportRepository.save(announcement);
            return "redirect:/announcement";
        }
    }

    @GetMapping("/edit/{id}")
    private String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("selectedElement", transportRepository.getById(id));
        return "employee/edit";
    }

    @PutMapping("edit/{id}")
    private String update(@ModelAttribute("selectedElement") @Valid TransportImpl announcement,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/edit";
        } else {
            transportRepository.save(announcement);
            return "redirect:/announcement";
        }
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") int id) {
        transportRepository.deleteById(id);
        return "redirect:/announcement";
    }
    @PutMapping("/find")
    private String find(@ModelAttribute("searchEntity") SearchTransportEntity searchTransportEntity,
                        Model model) {

        foundList = transportRepository.
                findByTypeInAndMileageGreaterThanEqualAndMileageLessThanEqualAndPriceGreaterThanEqualAndPriceLessThanEqual(
                searchTransportEntity.getSearchedTypes(), searchTransportEntity.getMinMileage(), searchTransportEntity.getMaxMileage(),
                        searchTransportEntity.getMinPrice(), searchTransportEntity.getMaxPrice());

        if(foundList.isEmpty()) {
            globalModelAttributes(model);
            model.addAttribute("nothingFound",true);
            return "main/startPage";

        } else {
            globalModelAttributes(model);
            model.addAttribute("nothingFound",false);
            model.addAttribute("resultsExist",true);
            model.addAttribute("announcementList",foundList);
            return "main/startPage";
        }
    }
    @GetMapping("/exportToCSV")
    private void exportToCSV(HttpServletResponse response) throws IOException {
        csvAssistant.exportToCSV(response,foundList);
    }
    private void globalModelAttributes(Model model) {
        model.addAttribute("searchEntity", new SearchTransportEntity());
        model.addAttribute("listOfTypes", TypeOfTransport.values());
    }
}