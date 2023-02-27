package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.AlreadyExistException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDTOToOrganisation;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;

@Service
@RequiredArgsConstructor
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    private final UserService userService;

    private final EventRepository eventRepository;

    private final LocationService locationService;


    private final Utility utility;


    private final OrganisationRegDTOToOrganisation mapper;

    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }


//    public List<Organisation> getAllOrgs() {
//         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!auth.isAuthenticated()) {
//            return Collections.emptyList();
//        }
//
//        User user = userRepository.getUserByEmail(auth.getName());
//
//        return auth.getAuthorities().stream()
//                .filter(authority -> "ADMIN".equals(authority.getAuthority()))
//                .map(authority -> organisationRepository.findAll())
//                .findFirst()
//                .orElseGet(() -> organisationRepository.findOrganisationsByUserId(user.getId()));
//    }

    public Organisation findOrganisationByUserId(Long id) {
        return organisationRepository.findOrganisationByUserId(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    public List<Organisation> findOrganisationsRegistrationDateByDescOrder() {

        return organisationRepository.findOrganisationsByRegistrationDateDesc();
    }

    public List<Organisation> findOrganisationsRegistrationDateByAscOrder() {
        return organisationRepository.findOrganisationsByRegistrationDateAsc();
    }

    public Organisation findOrganisationById(Long id) {
        return organisationRepository.findById(id).orElseThrow(() -> new NotFoundException(ORGANISATION_NOT_FOUND));
    }


    public void registration(OrganisationDTO orgDto) {
        Organisation org = mapper.apply(orgDto);
        if (organisationRepository.findAll().stream().noneMatch(o -> o.getUser().getUsername().equals(orgDto.getEmail()))) {

            org.getUser().setPassword(utility.encodePassword(org.getUser().getPassword()));
            userService.saveUser(org.getUser());
            organisationRepository.save(org);
        } else {
            throw new AlreadyExistException(EMAIL_ALREADY_TAKEN);
        }
    }

    public void updateCurrentLoggedOrganisation(OrganisationUpdateDTO updatedOrganisation, User user) {
        List<Location> cities = locationService.findLocationByValues(updatedOrganisation.getLocations());
        organisationRepository.updateOrganisationAndUser(user.getUsername(),
                updatedOrganisation.getName(),
                cities,
                updatedOrganisation.getEmail(),
                utility.encodePassword(updatedOrganisation.getNewPassword())
        );
    }

    public void updateOrganisationStatus(Organisation organisation, boolean status) {
        organisation.getUser().setEnabled(status);
        userService.saveUser(organisation.getUser());
    }

    public void deleteOrganisationAccount(Organisation org) {
        organisationRepository.delete(org);
        userService.deleteUser(org.getUser());
    }

    public void deleteOrganisationAccountById(Long id) {
        Organisation org = findOrganisationById(id);
        organisationRepository.delete(org);
        userService.deleteUser(org.getUser());
    }

    public void deleteAllOrganisationsAndUsers() {
        List<Organisation> orgs = getAllOrganisations();
        organisationRepository.deleteAll();
        orgs.stream().forEach(org -> {
            userService.deleteUserByOrganizationId(org.getId());
        });
    }

}
