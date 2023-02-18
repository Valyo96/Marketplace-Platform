package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.consts.Role;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationRegDTO;
import com.platform.marketplace.Marketplace.Platform.model.User;

import java.util.function.Function;

public class OrganisationRegDtoToUser implements Function<OrganisationRegDTO , User> {
    @Override
    public User apply(OrganisationRegDTO organisationRegDTO) {
        return new User(organisationRegDTO.getEmail(),organisationRegDTO.getPassword(), Role.ORGANISATION, true);
    }
}
