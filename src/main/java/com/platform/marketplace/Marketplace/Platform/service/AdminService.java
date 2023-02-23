package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.exceptions.WrongPasswordException;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.notAuthorizeExceptionMessage;
import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.wrongPassword;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final OrganisationService organisationService;

    private final UserService userService;

    private final Utility utility;


    public List<Organisation> getAllOrganisations() {
        return organisationService.getAllOrganisations();
    }

    public List<Organisation> listAllOrganisationsByRegistrationDateOrder(String order) {
        if (order.equals("desc")) {
            return organisationService.findOrganisationsRegistrationDateByDescOrder();
        } else if (order.equals("asc")) {
            return organisationService.findOrganisationsRegistrationDateByAscOrder();
        } else {
            return getAllOrganisations();
        }
    }

    public void updateOrganisationStatus(Organisation org, boolean status) {
        organisationService.updateOrganisationStatus(org, status);
    }

    public void deleteOrganisationAccountById(Long id, String password) {
        utility.authorizationCheck(password);
        organisationService.deleteOrganisationAccountById(id);
    }

    public void deleteAllAccounts(String password) {
        utility.authorizationCheck(password);
        organisationService.deleteAllOrganisationsAndUsers();
    }


}
