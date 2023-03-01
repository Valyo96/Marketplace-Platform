package com.platform.marketplace.Marketplace.Platform.utility.seeder;

import com.platform.marketplace.Marketplace.Platform.utility.consts.ListOfCities;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class LocationSeeder implements CommandLineRunner {

    private final LocationRepository locationRepository;

    private final ListOfCities cities;


    @Override
    public void run(String... args) throws Exception {
        if(locationRepository.findAll().size() == 0 &&locationRepository.findAll().size() <28) {
            for (String city : cities.getCities()) {
                Location location = new Location();
                location.setCity(city);
                locationRepository.save(location);

            }
        }
    }
}