package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.consts.Cities;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationRegDTO;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.OrganisationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor

public class AuthenticationController {

    private final OrganisationService organisationService;

    @GetMapping("/login")
    public String orgLogin(){
        return "login";
    }

    @GetMapping("/registration")
    public String orgRegister(Model model){
        model.addAttribute("registration" , new OrganisationRegDTO());
        model.addAttribute("locations" , Cities.values());
        return "registration";
    }

    @PostMapping("submit")
    public ModelAndView reg(@Valid OrganisationRegDTO organisationRegDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration");
        }
        try{
            organisationService.registration(organisationRegDTO);
        } catch (Exception e) {
            return new ModelAndView("registration")
                    .addObject("errorMessage",e.getMessage());
        }
        return new ModelAndView("redirect:/main");
    }
}
