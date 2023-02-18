package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.consts.Role;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationRegDTO;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.function.Function;

public class OrganisationRegDTOToOrganisation implements Function<OrganisationRegDTO , Organisation> {
    OrganisationRegDtoToUser userConverter = new OrganisationRegDtoToUser();

    @Override
    public Organisation apply(OrganisationRegDTO organisationRegDTO) {
        if(Utility.passwordConfirmation(organisationRegDTO.getPassword() , organisationRegDTO.getConfirmPassword())) {
            User user = userConverter.apply(organisationRegDTO);
            return new Organisation( organisationRegDTO.getName(), user, organisationRegDTO.getDateOfFoundation(), organisationRegDTO.getLocations());
        } else {
            throw new RuntimeException("Паролата за потвърждение не отговаря на горе подадената.");
        }
    }
}
