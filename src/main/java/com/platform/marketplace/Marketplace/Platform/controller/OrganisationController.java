package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/organisation")
public class OrganisationController {

    private final OrganisationService organisationService;

    @GetMapping
    public String showOrganisations(Model model){
        model.addAttribute("orgs" , organisationService.getAllOrganisations());
        return "admin";
    }

    @GetMapping("/settings")
    public String orgSettings(){
        return "organisationSettings";
    }

//    @PostMapping("")
    @PostMapping("/delete/{id}")
    public ModelAndView deleteOrganisation(@PathVariable("id") Long id) {
            try {
                organisationService.deleteOrganisationAccountById(id);
            } catch (Exception e) {
                return new ModelAndView("redirect:/organisations")
                        .addObject("errorMessage" , e.getMessage());
            }
            return new ModelAndView("redirect:/organisations");
    }
}
