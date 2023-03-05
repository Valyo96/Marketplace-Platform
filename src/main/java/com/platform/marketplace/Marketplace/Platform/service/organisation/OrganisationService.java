package com.platform.marketplace.Marketplace.Platform.service.organisation;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.user.UserService;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.AlreadyExistException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDtoToOrganisationMapper;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    private final OrganisationRegDtoToOrganisationMapper mapper;

    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }


    public Organisation findOrganisationByUserId(Long id) {
        return organisationRepository.findOrganisationByUserId(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    public Organisation findByEmail(String email) {
        return organisationRepository.findOrganisationByEmail(email).orElseThrow(() -> new NotFoundException(ORGANISATION_NOT_FOUND));
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
            if (!utility.checkIfEmailExists(orgDto.getEmail()) && utility.passwordConfirmation(orgDto.getPassword(), orgDto.getConfirmPassword())) {
                org.getUser().setPassword(utility.encodePassword(org.getUser().getPassword()));
                userService.saveUser(org.getUser());
                organisationRepository.save(org);
                return;
            }

        throw new AlreadyExistException(EMAIL_ALREADY_TAKEN);

    }

    public void updateOrganisationAccount(OrganisationUpdateDTO updatedOrganisation, User user) {
//        List<Location> cities = locationService.findLocationsByValues(updatedOrganisation.getLocations());
        if (!utility.checkIfEmailExists(updatedOrganisation.getEmail()) || updatedOrganisation.getEmail().equals(user.getUsername())) {
            Organisation org = findOrganisationByUserId(user.getId());
            org.setOrganisationName(updatedOrganisation.getName());
//            org.setLocations(cities);
            user.setUsername(updatedOrganisation.getEmail());
            organisationRepository.save(org);
            userService.saveUser(user);
            return;
        }
        throw new AlreadyExistException(EMAIL_ALREADY_TAKEN);
    }


    public void updateOrganisationStatus(Organisation organisation, boolean status) {
        organisation.getUser().setEnabled(status);
        userService.saveUser(organisation.getUser());
    }

    public void deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime date) {
        List<Organisation> orgs = organisationRepository.findByIsEnabledFalseAndDisabledPeriodEquals(date);
        organisationRepository.deleteAll(orgs);
        orgs.stream().forEach(org -> {
            userService.deleteUserByOrganizationId(org.getId());
        });
    }


    public void deleteOrganisationAccountById(Long id) {
        Organisation org = findOrganisationById(id);
        organisationRepository.delete(org);
        userService.deleteUser(org.getUser());
    }

    public void deleteAllOrganisationsAndUsers() {
        List<Organisation> orgs = getAllOrganisations();
        eventRepository.deleteAll();
        organisationRepository.deleteAll();

        orgs.stream().forEach(org -> {
            userService.deleteUserByOrganizationId(org.getId());
        });
    }

}
