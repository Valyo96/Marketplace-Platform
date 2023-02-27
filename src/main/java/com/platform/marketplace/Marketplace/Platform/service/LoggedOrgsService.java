package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.WrongPasswordException;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.NOT_AUTHORIZE_EXCEPTION_MESSAGE;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.WRONG_PASSWORD;

@Service
@RequiredArgsConstructor
public class LoggedOrgsService {

    private final UserService userService;

    private final OrganisationService organisationService;

    private final EventService eventService;

    private final Utility utility;

    private final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public List<Event> getEventsOfLoggedOrganisationById() {
        if (!auth.isAuthenticated()) {
            return Collections.emptyList();
        }

        User user = userService.getUserByEmail(auth.getName());
        List<Event> eventList = null;

        if (auth.getAuthorities().stream().anyMatch(autha -> "ORGANISATION".equals(autha.getAuthority()))) {
            Organisation org = organisationService.findOrganisationByUserId(user.getId());
            eventList = eventService.findEventsByOrgId(org.getId());
        }

        return eventList != null ? eventList : Collections.emptyList();
    }

    public void updateOrganisation(OrganisationDTO organisationDTO){
        organisationService.updateCurrentLoggedOrganisation(organisationDTO);
    }

    public void createEventByLoggedOrganisation(EventDTO eventDTO) {
        if (!auth.isAuthenticated()) {
            throw new NotAuthorizeException(NOT_AUTHORIZE_EXCEPTION_MESSAGE);
        }
        User user = userService.getUserByEmail(auth.getName());
        Organisation org = organisationService.findOrganisationByUserId(user.getId());
        if (auth.getAuthorities().stream().anyMatch(autha -> "ORGANISATION".equals(autha.getAuthority()))) {
            eventService.createEvent(eventDTO, org);
        }

    }

    public void deleteCurrentLoggedAccount(boolean confirmation , String password) {
        if (!auth.isAuthenticated()) {
            throw new NotAuthorizeException(NOT_AUTHORIZE_EXCEPTION_MESSAGE);
        }
        User user = userService.getUserByEmail(auth.getName());
        Organisation org = organisationService.findOrganisationByUserId(user.getId());
        if(confirmation){
            if(utility.verifyPassword(password , user.getPassword())){
                organisationService.deleteOrganisationAccount(org);
            } else {
                throw new WrongPasswordException(WRONG_PASSWORD);
            }
        }

    }


//    public void updateEventById(EventDTO eventDTO){
//        Event event = eventRepository.findById(eventDTO.getId()).orElseThrow(()-> new NotFoundException(eventByNameNotFound));
//        event.se
//    }
}
