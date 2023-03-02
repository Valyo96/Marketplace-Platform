package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.utility.consts.Role;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class OrganisationRegDtoUserMapper implements Function<OrganisationDTO, User> {
    @Override
    public User apply(OrganisationDTO organisationDTO) {
        return new User(organisationDTO.getEmail(), organisationDTO.getPassword(), Role.ORGANISATION, true);
    }
}
