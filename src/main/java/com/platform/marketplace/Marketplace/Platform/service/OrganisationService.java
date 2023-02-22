package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.exceptions.AlreadyExistException;
import com.platform.marketplace.Marketplace.Platform.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.exceptions.WrongPasswordException;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDTOToOrganisation;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.*;

@Service
@RequiredArgsConstructor
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    private final UserService userService;

    private final EventRepository eventRepository;

    private final LocationRepository locationRepository;


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        return organisationRepository.findOrganisationByUserId(id).orElseThrow(() -> new NotFoundException(userNotFound));
    }

    public List<Organisation> findOrganisationsRegistrationDateByDescOrder() {

        return organisationRepository.findOrganisationsByRegistrationDateDesc();
    }

    public List<Organisation> findOrganisationsRegistrationDateByAscOrder() {
        return organisationRepository.findOrganisationsByRegistrationDateASC();
    }

    public Organisation findOrganisationById(Long id) {
        return organisationRepository.findById(id).orElseThrow(() -> new NotFoundException(organisationNotFound));
    }


    public void registration(OrganisationDTO orgDto) {
        Organisation org = mapper.apply(orgDto);
        if (organisationRepository.findAll().stream().noneMatch(o -> o.getUser().getUsername().equals(orgDto.getEmail()))) {
            String encodedPassword = passwordEncoder.encode(org.getUser().getPassword());
            org.getUser().setPassword(encodedPassword);
            userService.saveUser(org.getUser());
            organisationRepository.save(org);
        } else {
            throw new AlreadyExistException(emailAlreadyTaken);
        }
    }

    public void updateCurrentLoggedOrganisation(OrganisationDTO organisationDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            User loggedUser = userService.getUserByEmail(auth.getName());
            Organisation organisation = findOrganisationByUserId(loggedUser.getId());
            if (userService.getUserByEmail(organisationDTO.getEmail()) == null) {
                if(Utility.passwordConfirmation(organisationDTO.getPassword() , organisationDTO.getConfirmPassword())) {
                    List<Location> cities = locationRepository.findLocationsByValue(organisationDTO.getLocations());
                    loggedUser.setUsername(organisationDTO.getEmail());
                    String encodedPassword = passwordEncoder.encode(organisationDTO.getPassword());
                    loggedUser.setPassword(encodedPassword);
                    organisation.setOrganisationName(organisationDTO.getName());
                    organisation.setLocations(cities);
                    organisation.setUser(loggedUser);
                    userService.saveUser(loggedUser);
                    organisationRepository.save(organisation);
                } else {
                    throw new BadCredentialsException("Паролите не съвпадат");
                }
            } else {
                throw new AlreadyExistException(emailAlreadyTaken);
            }

        } else {
            throw new NotAuthorizeException(notAuthorizeExceptionMessage);
        }

    }

    public void updateOrganisationStatus(Organisation organisation , boolean status){
        organisation.getUser().setEnabled(status);
        organisationRepository.save(organisation);
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

    public void deleteAllAccounts() {
        organisationRepository.deleteAll();
    }
}
