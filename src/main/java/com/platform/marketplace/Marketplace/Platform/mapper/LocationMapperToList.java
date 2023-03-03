package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.LocationDTO;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class LocationMapperToList implements Function<List<Location>, List<LocationDTO>> {

    private final LocationMapper locationMapper;
    @Override
    public List<LocationDTO> apply(List<Location> locations) {
        return locations.stream().map(locationMapper).collect(Collectors.toList());
    }
}
