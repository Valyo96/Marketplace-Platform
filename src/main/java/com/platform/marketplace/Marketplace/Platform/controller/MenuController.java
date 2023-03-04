package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final EventService eventService;
    @GetMapping("/menu")
    public String main(Model model){
        model.addAttribute("events" , eventService.showAllEvents());
        return "menu";
    }


}
