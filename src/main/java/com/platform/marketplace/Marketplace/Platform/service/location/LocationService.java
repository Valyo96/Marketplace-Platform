package com.platform.marketplace.Marketplace.Platform.service.location;

import com.platform.marketplace.Marketplace.Platform.dto.AddressDTO;
import com.platform.marketplace.Marketplace.Platform.dto.LocationDTO;
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

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public List<Location> findLocationsByCityAndAddressIn(List<LocationDTO>values){
        List<String> cities = values.stream().map(LocationDTO::getCity).collect(Collectors.toList());
        List<String> addresses = values.stream()
                .flatMap(locationDTO -> locationDTO.getAddresses().stream())
                .map(AddressDTO::getAddress)
                .collect(Collectors.toList());
        return locationRepository.findLocationsByCityAndAddressIn(cities , addresses);

    }

    public List<Location> findLocationsByValues(List<String>values){
        return locationRepository.findLocationsByValue(values);
    }
}
