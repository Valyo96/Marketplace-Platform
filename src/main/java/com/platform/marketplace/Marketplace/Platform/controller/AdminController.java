package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.admin.AdminService;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    private final OrganisationService organisationService;

    private final EventService eventService;

    @GetMapping("/organisations")
    public String getAllOrgs( Model model ,@ModelAttribute("errorMessage") String errorMessage, HttpSession session){
        List<Organisation> orgsByDate = (List<Organisation>) session.getAttribute("orgs");
        if(orgsByDate == null || orgsByDate.size() == 0){
            model.addAttribute("errorMessage" , errorMessage);
            model.addAttribute("orgs" , adminService.getAllOrganisations());
            return "admin";
        }
        model.addAttribute("errorMessage" , errorMessage);
            model.addAttribute("orgs" , orgsByDate);
        return "admin";
    }

    @PostMapping("filter")
    public String getOrgsByDate(@RequestParam("order")String order , HttpSession session){
        session.setAttribute("orgs" , adminService.listAllOrganisationsByRegistrationDateOrder(order));
        return "redirect:/admin/organisations";
    }

//    @GetMapping("/edit-organisation-status/{id}")
//    public String editOrgStatus(@PathVariable("id") Long id ,Model model){
//        Organisation org = organisationService.findOrganisationById(id);
//        model.addAttribute("org" , org);
//        return "admin";
//    }

    @PostMapping("update/{id}")
    public ModelAndView updateOrgStatus(@PathVariable Long id , @Param("status") boolean status){

        adminService.updateOrganisationStatus(organisationService.findOrganisationById(id) ,status);
        return new ModelAndView("redirect:/admin/organisations");
    }

    @PostMapping("delete/{id}")
    public ModelAndView deleteOrgStatus(@PathVariable("id") Long id , @RequestParam String password ){
        adminService.deleteOrganisationAccountById(id ,password);
        return new ModelAndView("redirect:/admin/organisations");
    }


    @PostMapping("/deleteAllOrganisations")
    public ModelAndView deleteAllOrgs(@RequestParam String password , HttpSession session){
            adminService.deleteAllAccounts(password);
        return new ModelAndView("redirect:/admin/organisations");
    }

    @GetMapping("/organisation-events-management/{id}")
    public String getEventsByOrganisationId(@PathVariable Long id , Model model){
        String previousUrl = "admin/organisation-events-management/"+id;
        model.addAttribute("events" , eventService.findEventsByOrgId(id));
        model.addAttribute("previousUrl" ,previousUrl);
        return "adminEventManagement";
    }

    @PostMapping("/delete-organisation-event/{id}")
    public ModelAndView deleteEvent(@PathVariable Long id , @RequestParam(required = false) String previousUrl){
        adminService.deleteEventByOrgId(id);
        return new ModelAndView("redirect:/"+previousUrl);
    }

    @PostMapping("/update-event-status/{id}")
    public ModelAndView updateEventStatus(@PathVariable Long id , @RequestParam boolean status , @RequestParam(required = false) String previousUrl){
        eventService.setIsEnabledEventField(id, status);
        return new ModelAndView("redirect:/"+previousUrl);
    }

    @GetMapping("/event-details/{id}")
    public String getEventDetails(@PathVariable Long id , Model model){
        model.addAttribute("event" , eventService.getEventDTOById(id));
        return "eventDetails";
    }

}
