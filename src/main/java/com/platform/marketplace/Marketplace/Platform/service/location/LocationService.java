package com.platform.marketplace.Marketplace.Platform.service.location;

import com.platform.marketplace.Marketplace.Platform.mapper.LocationToStringMapper;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationToStringMapper locationToStringMapper;

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public List<String> getAllLocationsToString(){
        return locationToStringMapper.apply(getAllLocations());
    }


    public List<Location> findLocationsByValues(List<String>values){
        return locationRepository.findLocationsByValue(values);
    }
}
