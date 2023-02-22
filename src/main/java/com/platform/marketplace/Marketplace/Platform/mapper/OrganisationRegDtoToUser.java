package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.consts.Role;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.model.User;

import java.util.function.Function;

public class OrganisationRegDtoToUser implements Function<OrganisationDTO, User> {
    @Override
    public User apply(OrganisationDTO organisationDTO) {
        return new User(organisationDTO.getEmail(), organisationDTO.getPassword(), Role.ORGANISATION, true);
    }
}
