package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationRegDTO;
import com.platform.marketplace.Marketplace.Platform.exceptions.AlreadyExistException;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDTOToOrganisation;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.consts.ExceptionMessages.emailAlreadyTaken;

@Service
@RequiredArgsConstructor
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    private OrganisationRegDTOToOrganisation mapper =new OrganisationRegDTOToOrganisation();


    public List<Organisation> getAllOrgs() {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated()) {
            return Collections.emptyList();
        }

        User user = userRepository.getUserByEmail(auth.getName());

        return auth.getAuthorities().stream()
                .filter(authority -> "ADMIN".equals(authority.getAuthority()))
                .map(authority -> organisationRepository.findAll())
                .findFirst()
                .orElseGet(() -> organisationRepository.findOrganisationsByUserId(user.getId()));
    }

    public List<Event> getEventsOfLoggedOrganisationById() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated()) {
            return Collections.emptyList();
        }

        User user = userRepository.getUserByEmail(auth.getName());
        List<Event> eventList = null;

        if (auth.getAuthorities().stream().anyMatch(autha -> "ORGANISATION".equals(autha.getAuthority()))) {
            Organisation org = organisationRepository.findOrganisationByUserId(user.getId());
            eventList = eventRepository.findEventsByOrganisationId(org.getId());
        }

        return eventList != null ? eventList : Collections.emptyList();
    }



    public void registration(OrganisationRegDTO orgDto){
        Organisation org = mapper.apply(orgDto);
       if(organisationRepository.findAll().stream().noneMatch(o-> o.getUser().getUsername().equals(orgDto.getEmail()))){
           String encodedPassword =passwordEncoder.encode(org.getUser().getPassword());
           org.getUser().setPassword(encodedPassword);
           userRepository.save(org.getUser());
           organisationRepository.save(org);
       } else {
           throw new AlreadyExistException(emailAlreadyTaken);
       }
    }
}
