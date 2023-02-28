package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.AdminService;
import com.platform.marketplace.Marketplace.Platform.service.OrganisationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    private final OrganisationService organisationService;

    @GetMapping("/organisations")
    public String getAllOrgs( Model model , String errorMessage, HttpSession session){
        List<Organisation> orgsByDate = (List<Organisation>) session.getAttribute("orgs");
        if(orgsByDate == null || orgsByDate.size() == 0){
            errorMessage= (String) session.getAttribute("errorMessage");
            session.removeAttribute("errorMessage");
            model.addAttribute("errorMessage" , errorMessage);
            model.addAttribute("orgs" , adminService.getAllOrganisations());
            return "admin";
        }
       errorMessage= (String) session.getAttribute("errorMessage");
        session.removeAttribute("errorMessage");
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
    } //TODO: трябва да оправя в хтмл-а полето за въвеждане на парола


    @PostMapping("/deleteAllOrganisations")
    public ModelAndView deleteAllOrgs(@RequestParam String password , HttpSession session){
        try{
            adminService.deleteAllAccounts(password);

        }catch (Exception e) {
            session.setAttribute("errorMessage" ,e.getMessage());
            ModelAndView mav = new ModelAndView("redirect:/admin/organisations");
            mav.addObject("errorMessage" , e.getMessage());
            return mav;
        }
        return new ModelAndView("redirect:/admin/organisations");
    }
}
