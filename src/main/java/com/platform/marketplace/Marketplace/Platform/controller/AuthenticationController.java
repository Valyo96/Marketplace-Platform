package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.consts.Cities;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationRegDTO;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.service.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.OrganisationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;


@Controller
@RequiredArgsConstructor

public class AuthenticationController {

    private final OrganisationService organisationService;

    private final LocationService locationService;

    @GetMapping("/login")
    public String orgLogin(){
        return "login";
    }

    @GetMapping("/registration")
    public String orgRegister(Model model){
        model.addAttribute("registration" , new OrganisationRegDTO());
        model.addAttribute("cities" , locationService.getAllLocations());
        return "registration";
    }

    @PostMapping("submit")
    public ModelAndView reg(@Valid OrganisationRegDTO organisationRegDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/registration");
        }
        try{
            organisationService.registration(organisationRegDTO);
        } catch (Exception e) {
            return new ModelAndView("redirect:/registration")
                    .addObject("errorMessage",e.getMessage());
        }
        return new ModelAndView("mainPage");
    }
}
