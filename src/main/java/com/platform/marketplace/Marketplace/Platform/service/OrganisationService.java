package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationRegDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationResponse;
import com.platform.marketplace.Marketplace.Platform.exceptions.AlreadyExistException;
import com.platform.marketplace.Marketplace.Platform.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDTOToOrganisation;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationToOrganisationResponse;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.*;

@Service
@RequiredArgsConstructor
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    private final UserService userService;

    private final EventRepository eventRepository;

    private final OrganisationToOrganisationResponse convertToResponse = new OrganisationToOrganisationResponse() ;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final OrganisationRegDTOToOrganisation mapper;

    public List<OrganisationResponse> getAllOrganisations() {
        return organisationRepository.findAll().stream().map(convertToResponse).collect(Collectors.toList());
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

    public List<OrganisationResponse> findOrganisationsRegistrationDateByDescOrder() {

        return organisationRepository.findOrganisationsByRegistrationDateDesc().stream().map(convertToResponse).collect(Collectors.toList());
    }

    public List<OrganisationResponse> findOrganisationsRegistrationDateByAscOrder() {
        return organisationRepository.findOrganisationsByRegistrationDateASC().stream().map(convertToResponse).collect(Collectors.toList());
    }

    public Organisation findOrganisationById(Long id) {
        return organisationRepository.findById(id).orElseThrow(() -> new NotFoundException(organisationNotFound));
    }


    public void registration(OrganisationRegDTO orgDto) {
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

    public void deleteOrganisationAccount(Organisation org) {
        organisationRepository.delete(org);
        userService.deleteUser(org.getUser());
    }

    public void deleteOrganisationAccountById(Long id) {
        Organisation org = findOrganisationById(id);
        organisationRepository.delete(org);
    }

    public void deleteAllAccounts() {
        organisationRepository.deleteAll();
    }
}
