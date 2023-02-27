package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.service.LoggedOrgsService;
import com.platform.marketplace.Marketplace.Platform.service.OrganisationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final LoggedOrgsService loggedOrgsService;


    @GetMapping
    public String showOrganisations(Model model){
        model.addAttribute("orgs" , organisationService.getAllOrganisations());
        return "admin";
    }

    @GetMapping("/settings")
    public String orgSettings(Model model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("currentLogged" ,auth.getName());
        String name =auth.getName();
        model.addAttribute("org" , new OrganisationUpdateDTO());
        return "organisationSettings";
    }

    @PostMapping("update")
    public ModelAndView updateOrg(@Valid OrganisationUpdateDTO org ){
        try{
            loggedOrgsService.updateOrganisation(org);
        }catch (Exception e) {
            return new ModelAndView("redirect:/organisation/settings")
                    .addObject("errorMessage" , e.getMessage());
        }

        return new ModelAndView("redirect:/organisation/settings");
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
