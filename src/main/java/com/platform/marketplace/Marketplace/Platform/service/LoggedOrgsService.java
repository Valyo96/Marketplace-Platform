package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.exceptions.WrongPasswordException;
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

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.notAuthorizeExceptionMessage;
import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.wrongPassword;

@Service
@RequiredArgsConstructor
public class LoggedOrgsService {

    private final UserService userService;

    private final OrganisationService organisationService;

    private final EventService eventService;

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

    public void createEventByLoggedOrganisation(EventDTO eventDTO) {
        if (!auth.isAuthenticated()) {
            throw new NotAuthorizeException(notAuthorizeExceptionMessage);
        }
        User user = userService.getUserByEmail(auth.getName());
        Organisation org = organisationService.findOrganisationByUserId(user.getId());
        if (auth.getAuthorities().stream().anyMatch(autha -> "ORGANISATION".equals(autha.getAuthority()))) {
            eventService.createEvent(eventDTO, org);
        }

    }

    public void deleteCurrentLoggedAccount(boolean confirmation , String password) {
        if (!auth.isAuthenticated()) {
            throw new NotAuthorizeException(notAuthorizeExceptionMessage);
        }
        User user = userService.getUserByEmail(auth.getName());
        Organisation org = organisationService.findOrganisationByUserId(user.getId());
        if(confirmation){
            if(Utility.verifyPassword(password , user.getPassword())){
                organisationService.deleteOrganisationAccount(org);
            } else {
                throw new WrongPasswordException(wrongPassword);
            }
        }

    }


//    public void updateEventById(EventDTO eventDTO){
//        Event event = eventRepository.findById(eventDTO.getId()).orElseThrow(()-> new NotFoundException(eventByNameNotFound));
//        event.se
//    }
}
