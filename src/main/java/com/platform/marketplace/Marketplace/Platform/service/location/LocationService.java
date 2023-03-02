package com.platform.marketplace.Marketplace.Platform.service.location;

import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public List<Location> findLocationByValues(List<String>values){
        return locationRepository.findLocationsByValue(values);
    }
}
