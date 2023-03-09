package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final EventService eventService;



    @GetMapping("/menu")
    public String main(Model model, HttpSession session , @ModelAttribute("errorMessage") String errorMessage) {
        List<EventDTO> events = (List<EventDTO>) session.getAttribute("events");
        session.removeAttribute("events");
        if (events == null || events.size() == 0) {
            model.addAttribute("errorMessage" ,errorMessage);
            return "menu";
        }
        model.addAttribute("events", events);
        model.addAttribute("eventCounterFromSearchResults" , eventService.eventCounter(events));
        return "menu";
    }

    @GetMapping("/all-active-events")
    public String showAllActiveEvents(Model model){
        model.addAttribute("events" ,eventService.getAllActiveEvents());

        return "menu";
    }

    @PostMapping("inactive")
    public ModelAndView getInactiveEvents(HttpSession session) {
        session.setAttribute("events", eventService.getAllInactiveEvents());
        return new ModelAndView("redirect:/menu");
    }

    @PostMapping("filterByStartDate")
    public ModelAndView filterEventsByStartDate(@RequestParam String filter, HttpSession session) {
        session.setAttribute("events", eventService.filterEventsByStartDate(filter));
        return new ModelAndView("redirect:/menu");
    }

    @PostMapping("filterByEndDate")
    public ModelAndView filterEventsByEndDate(@RequestParam String filter, HttpSession session) {
        session.setAttribute("events", eventService.filterEventsByEndDate(filter));
        return new ModelAndView("redirect:/menu");
    }

    @PostMapping("filterByCreatedDate")
    public ModelAndView filterEventsByCreateDate(@RequestParam String filter, HttpSession session) {
        session.setAttribute("events", eventService.filterByCreatedDate(filter));
        return new ModelAndView("redirect:/menu");
    }


    @PostMapping("uniqueFilter")
    public ModelAndView getEvents(@RequestParam(name = "name", required = false)String name,
                                  @RequestParam(name = "organisationName" ,required = false)String organisationName,
                                  @RequestParam(name = "address", required = false)String address,
                                  @RequestParam(name = "location", required = false)String location,
                                  @RequestParam(name = "entrance", required = false)String entrance,
                                  @RequestParam(name = "category", required = false)String category,
                                  @RequestParam(name = "keyword", required = false)String keyword,
                                  @RequestParam(name = "date" , required = false)String date,
                                  HttpSession session) {

        session.setAttribute("events", eventService.returnSpecificFilteredEvents(name, organisationName,address, location, entrance, category, keyword,date));
        return new ModelAndView("redirect:/menu");
    }

    @GetMapping("/event-details/{id}")
    public String showEventDetails(@PathVariable Long id,Model model){
        model.addAttribute("event" ,eventService.getEventDTOById(id));
        model.addAttribute("eventId" ,eventService.getEventDTOById(id).getEventId());
        return "eventDetails";
    }
}
