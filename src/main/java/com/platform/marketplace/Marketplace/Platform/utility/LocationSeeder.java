package com.platform.marketplace.Marketplace.Platform.utility;

import com.platform.marketplace.Marketplace.Platform.consts.ListOfCities;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LocationSeeder implements CommandLineRunner {
    @Autowired
    private LocationRepository locationRepository;


    @Override
    public void run(String... args) throws Exception {
        for (String city : ListOfCities.cities) {
            Location location = new Location();
            location.setCity(city);
            locationRepository.save(location);
        }
    }
}