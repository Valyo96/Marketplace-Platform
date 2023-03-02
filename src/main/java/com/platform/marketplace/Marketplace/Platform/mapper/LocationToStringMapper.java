package com.platform.marketplace.Marketplace.Platform.mapper;


import com.platform.marketplace.Marketplace.Platform.model.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
public class LocationToStringMapper implements Function< List<Location>,List<String>> {


    @Override
    public List<String> apply(List<Location> locations) {
        return locations.stream().map(Location :: getCity).collect(Collectors.toList());
    }
}
