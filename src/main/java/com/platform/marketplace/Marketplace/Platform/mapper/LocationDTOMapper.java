package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.LocationDTO;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LocationDTOMapper implements Function<LocationDTO , Location> {
    private final AddressDTOMapperToList addressDTOMapperToList;
    @Override
    public Location apply(LocationDTO locationDTO) {
        return new Location(locationDTO.getCity(),
               addressDTOMapperToList.apply(locationDTO.getAddresses()));
    }
}
