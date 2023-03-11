package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrgPasswordChange;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.LoggedOrganisationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/organisation")
public class OrganisationController {

    private final OrganisationService organisationService;

    private final LoggedOrganisationService loggedOrganisationService;

    private final LocationService locationService;

    private final EventService eventService;


    @GetMapping("/settings")
    public String orgSettings(Model model ,@ModelAttribute("errorMessage") String errorMessage ,@ModelAttribute("nameError")String nameError ,@ModelAttribute("emailError")String emailError,@ModelAttribute("invalidPassword")String invalidPassword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Organisation org = organisationService.findByEmail(authentication.getName());
        model.addAttribute("organisation" , org);
        model.addAttribute("org" , new OrganisationUpdateDTO());
        model.addAttribute("errorMessage" , errorMessage);
        model.addAttribute("nameError" ,nameError);
        model.addAttribute("emailError" , emailError);
        model.addAttribute("invalidPassword" , invalidPassword);
//        model.addAttribute("locations" , locationService.getAllLocationsToString());
        return "organisationSettings";
    }




    @PostMapping("update")
    public ModelAndView updateOrg(@Valid OrganisationUpdateDTO org ,BindingResult result , @ModelAttribute("organisation") Organisation organisation){
        String nameError ="";
        String emailError="";
        if(result.hasErrors()) {
            ModelAndView mav = new ModelAndView("redirect:/organisation/settings");
            mav.addObject("organisation", organisation);
            if (result.hasFieldErrors("name")) {
                nameError = result.getFieldError("name").getDefaultMessage();
                mav.addObject("nameError", nameError);
            }
            if (result.hasFieldErrors("email")) {
                emailError = result.getFieldError("email").getDefaultMessage();
                mav.addObject("emailError", emailError);
            }
            return mav;
        }
           loggedOrganisationService.updateLoggedOrganisationAccount(org);
        return new ModelAndView("redirect:/logout");
    }


    @PostMapping("change-password")
    public ModelAndView changePassword(@Valid OrgPasswordChange orgPas , BindingResult bindingResult){
        String invalidPassword ="";
        if(bindingResult.hasErrors()){
            invalidPassword=bindingResult.getFieldError("newPassword").getDefaultMessage();
            return new ModelAndView("redirect:/organisation/settings")
                    .addObject("invalidPassword" , invalidPassword);
        }
            loggedOrganisationService.changeLoggedOrganisationPassword(orgPas);

        return new ModelAndView("redirect:/login");
    }



    @PostMapping("disable")
    public ModelAndView disabledOrganisationAccount(@RequestParam("password") String password) {
                loggedOrganisationService.disableCurrentLoggedAccount(password);
            return new ModelAndView("redirect:/login");
    }

    @GetMapping("/event-management")
    public String eventManagement(Model model, @ModelAttribute("errorMessage")String errorMessage){
        model.addAttribute("errorMessage" ,errorMessage);
        model.addAttribute("events" ,loggedOrganisationService.getEventsOfLoggedOrganisationById());
        return "eventManagement";
    }

    @GetMapping("/create-event")
    public String createEvent(Model model ,@ModelAttribute("errorMessage")String errorMessage){
        model.addAttribute("event" , new EventDTO());
        model.addAttribute("errorMessage" ,errorMessage);
        model.addAttribute("locations" , locationService.getAllLocations());
        model.addAttribute("entrance" , EntranceType.values());
        return "createEvent";
    }

    @PostMapping("create")
    public ModelAndView createEvent(@Valid EventDTO event , BindingResult bindingResult){
        String categoryError ="";
        String nameError = "";
        String descriptionError = "";
        String linkError = "";
        String locationError="";
        String startsAtError ="";
        String endsAtError="";
        String keywordsError="";
        String imageError = "";
        String addressError ="";
        if(bindingResult.hasErrors()){
            if(bindingResult.hasFieldErrors("eventCategories")){
                categoryError = bindingResult.getFieldError("eventCategories").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("name")){
                nameError = bindingResult.getFieldError("name").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("description")){
                descriptionError = bindingResult.getFieldError("description").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("linkToApplicationForm")){
                linkError = bindingResult.getFieldError("linkToApplicationForm").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("locations")){
                locationError = bindingResult.getFieldError("locations").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("startsAt")){
                startsAtError = bindingResult.getFieldError("startsAt").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("endsAt")){
                endsAtError = bindingResult.getFieldError("endsAt").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("keywords")){
                keywordsError = bindingResult.getFieldError("keywords").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("imagePath")){
                imageError = bindingResult.getFieldError("imagePath").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("address")){
                addressError = bindingResult.getFieldError("address").getDefaultMessage();
            }
            return new ModelAndView("createEvent").addObject("event" ,event)
                    .addObject("categoryError" , categoryError)
                    .addObject("nameError" ,nameError)
                    .addObject("descriptionError" ,descriptionError)
                    .addObject("linkError" ,linkError)
                    .addObject("locationError",locationError)
                    .addObject("startsAtError",startsAtError)
                    .addObject("endsAtError",endsAtError)
                    .addObject("keywordsError" , keywordsError)
                    .addObject("imageError" , imageError)
                    .addObject("addressError" , addressError)
                    .addObject("locations" , locationService.getAllLocations());
        }
            loggedOrganisationService.createEventByLoggedOrganisation(event);

        return new ModelAndView("redirect:/menu");
    }

    @GetMapping("update-event/{id}")
    public String updateEvent(@PathVariable Long id , @ModelAttribute("errorMessage")String errorMessage ,Model model){
        model.addAttribute("errorMessage" ,errorMessage);
        model.addAttribute("event" ,eventService.getEventDTOById(id));
        model.addAttribute("locations" ,locationService.getAllLocations());
        String previousUrl = "organisation/update-event/"+id;
        model.addAttribute("previousUrl" , previousUrl);
        return "updateEvent";
    }

    @PostMapping("update-event/{id}")
    public ModelAndView updateEvent(@PathVariable Long id,@Valid EventDTO event,BindingResult bindingResult) {
        String categoryError ="";
        String nameError = "";
        String descriptionError = "";
        String linkError = "";
        String locationError="";
        String startsAtError ="";
        String endsAtError="";
        String keywordsError="";
        String imageError = "";
        String addressError ="";
        if(bindingResult.hasErrors()){
            if(bindingResult.hasFieldErrors("eventCategories")){
                categoryError = bindingResult.getFieldError("eventCategories").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("name")){
                nameError = bindingResult.getFieldError("name").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("description")){
                descriptionError = bindingResult.getFieldError("description").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("linkToApplicationForm")){
                linkError = bindingResult.getFieldError("linkToApplicationForm").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("locations")){
                locationError = bindingResult.getFieldError("locations").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("startsAt")){
                startsAtError = bindingResult.getFieldError("startsAt").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("endsAt")){
                endsAtError = bindingResult.getFieldError("endsAt").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("keywords")){
                keywordsError = bindingResult.getFieldError("keywords").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("imageUrl")){
                imageError = bindingResult.getFieldError("imageUrl").getDefaultMessage();
            }
            if(bindingResult.hasFieldErrors("address")){
                addressError = bindingResult.getFieldError("address").getDefaultMessage();
            }
            return new ModelAndView("updateEvent").addObject("event" ,event)
                    .addObject("categoryError" , categoryError)
                    .addObject("nameError" ,nameError)
                    .addObject("descriptionError" ,descriptionError)
                    .addObject("linkError" ,linkError)
                    .addObject("locationError",locationError)
                    .addObject("startsAtError",startsAtError)
                    .addObject("endsAtError",endsAtError)
                    .addObject("keywordsError" , keywordsError)
                    .addObject("imageError" , imageError)
                    .addObject("addressError" , addressError)
                    .addObject("locations" , locationService.getAllLocations());
        }
        loggedOrganisationService.updateEventByOrgIdAndEventId(id , event);
        return new ModelAndView("redirect:/organisation/event-management");
    }

    @PostMapping("delete-event/{id}")
    public ModelAndView deleteEvent(@PathVariable Long id ,@RequestParam String password ){
        loggedOrganisationService.deleteEventPermanent(id , password);
        return new ModelAndView("redirect:/organisation/event-management");
    }

    @GetMapping("/event-details/{id}")
    public String showEventDetails(@PathVariable Long id,Model model){
        model.addAttribute("event" ,eventService.getEventDTOById(id));
        model.addAttribute("eventId" ,eventService.getEventDTOById(id).getEventId());
        return "eventDetails";
    }

}
