package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.exceptions.WrongPasswordException;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.notAuthorizeExceptionMessage;
import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.wrongPassword;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final OrganisationService organisationService;

    private final UserService userService;

    private final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public List<Organisation> getAllOrganisations(){
        return organisationService.getAllOrganisations();
    }

    public List<Organisation>listAllOrganisationsByRegistrationDateOrder(String order){
       if(order.equals("Подреди по низходящ ред")){
           return organisationService.findOrganisationsRegistrationDateByDescOrder();
       } else if (order.equals("Подреди по възходящ ред")){
           return organisationService.findOrganisationsRegistrationDateByAscOrder();
       } else {
           return getAllOrganisations();
       }
    }

    public void updateOrganisationStatus(Organisation org , boolean status){
        organisationService.updateOrganisationStatus(org, status);
    }

    public void deleteOrganisationAccountById(Long id){
        organisationService.deleteOrganisationAccountById(id);
    }

    public void deleteAllAccounts(boolean confirmation ,String password ,String passwordConfirmation){
        if(!auth.isAuthenticated()){
            throw new NotAuthorizeException(notAuthorizeExceptionMessage );
        }
        User admin = userService.getUserByEmail(auth.getName());
        if(confirmation){
            if(Utility.verifyPassword(password , admin.getPassword()) && Utility.verifyPassword(passwordConfirmation ,admin.getPassword())){
                organisationService.deleteAllAccounts();
            } else {
            throw new WrongPasswordException(wrongPassword);
            }




        }

    }
}
