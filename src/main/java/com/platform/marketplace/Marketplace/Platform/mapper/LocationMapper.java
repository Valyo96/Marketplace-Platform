package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.LocationDTO;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
@RequiredArgsConstructor
public class LocationMapper implements Function<Location, LocationDTO> {

    private final AddressMapperToList addressMapperToList;

    @Override
    public LocationDTO apply(Location location) {
        return new LocationDTO(location.getId(),
                location.getCity(),
                addressMapperToList.apply(location.getAddresses()));
    }
}
