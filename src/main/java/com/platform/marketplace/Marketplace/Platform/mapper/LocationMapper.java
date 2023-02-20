package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.consts.Cities;
import com.platform.marketplace.Marketplace.Platform.model.Location;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LocationMapper implements Function<List<String>, List<Location>> {

   @Override
    public List<Location> apply(List<String> strings) {
        return strings.stream()
                .map(cityName -> new Location(Cities.valueOf(cityName)))
                .collect(Collectors.toList());
    }
}
