package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor

public class AuthenticationController {

    private final OrganisationService organisationService;

    private final LocationService locationService;

    @GetMapping("/login")
    public String orgLogin() {

        return "login";
    }


    @GetMapping("/register")
    public String register(Model model ,@ModelAttribute("errorMessage") String errorMessage){
        model.addAttribute("org" , new OrganisationDTO());
        model.addAttribute("errorMessage" , errorMessage);
        model.addAttribute("locations" , locationService.getAllLocationsToString());
        return "reg";
    }



    @PostMapping("submit")
    public ModelAndView submitRegister(@Valid OrganisationDTO org , BindingResult bindingResult){
        String nameError ="";
        String emailError ="";
        String passwordError ="";
        if(bindingResult.hasErrors()){
            ModelAndView mav = new ModelAndView("reg");
            mav.addObject("org" , org);
            mav.addObject("locations" ,locationService.getAllLocationsToString());
            if( bindingResult.hasFieldErrors("name")){
                nameError =bindingResult.getFieldError("name").getDefaultMessage();
                mav.addObject("name" , nameError);
            }
            if(bindingResult.hasFieldErrors("email")){
                emailError = bindingResult.getFieldError("email").getDefaultMessage();
                mav.addObject("email" , emailError);
            }
            if(bindingResult.hasFieldErrors("password")){
                passwordError = bindingResult.getFieldError("password").getDefaultMessage();
                mav.addObject("password", passwordError);
            }

            return mav;
        }
        organisationService.registration(org);
        return new ModelAndView("menu");
    }

    @PostMapping("/logout")
    public String logout() {
        return "menu";
    }
}