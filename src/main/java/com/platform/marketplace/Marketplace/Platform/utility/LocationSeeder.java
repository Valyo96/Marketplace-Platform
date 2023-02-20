package com.platform.marketplace.Marketplace.Platform.utility;

import com.platform.marketplace.Marketplace.Platform.consts.Cities;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LocationSeeder implements CommandLineRunner {
    @Autowired
    private LocationRepository locationRepository;


    @Override
    public void run(String... args) throws Exception {
        for (Cities city : Cities.values()) {
            Location location = new Location();
            location.setCity(city);
            locationRepository.save(location);
        }
    }
}