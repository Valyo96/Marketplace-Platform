package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
@RequiredArgsConstructor
@Component
public class OrganisationRegDtoToOrganisationMapper implements Function<OrganisationDTO, Organisation> {
    private final OrganisationRegDtoUserMapper userConverter;

    private final LocationService locationService;



    @Override
    public Organisation apply(OrganisationDTO organisationDTO) {
            User user = userConverter.apply(organisationDTO);
            List<Location> cities = locationService.findLocationByValues(organisationDTO.getLocations());
            return new Organisation( organisationDTO.getName(), user,  cities);
    }
}
