package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationResponse;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class OrganisationToOrganisationResponse implements Function<Organisation , OrganisationResponse> {
    @Override
    public OrganisationResponse apply(Organisation organisation) {
        List<String> cities = organisation.getLocations().stream()
                .map(Location::getCity).toList();
        return new OrganisationResponse(organisation.getId(),
                organisation.getOrganisationName(),
                cities);
    }
}
