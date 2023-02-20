package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationRegDTO;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import com.platform.marketplace.Marketplace.Platform.service.LocationService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
@RequiredArgsConstructor
@Component
public class OrganisationRegDTOToOrganisation implements Function<OrganisationRegDTO , Organisation> {
    OrganisationRegDtoToUser userConverter = new OrganisationRegDtoToUser();
    LocationMapper locationMapper = new LocationMapper();

    private final LocationRepository locationRepository;


    @Override
    public Organisation apply(OrganisationRegDTO organisationRegDTO) {
        if(Utility.passwordConfirmation(organisationRegDTO.getPassword() , organisationRegDTO.getConfirmPassword())) {
            User user = userConverter.apply(organisationRegDTO);
            List<Location> cities = locationRepository.findLocationsByValue(organisationRegDTO.getLocations());

            return new Organisation( organisationRegDTO.getName(), user,  cities);
        } else {
            throw new RuntimeException("Паролата за потвърждение не отговаря на горе подадената.");
        }
    }
}
