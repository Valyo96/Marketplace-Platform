package com.platform.marketplace.Marketplace.Platform.service.location;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platform.marketplace.Marketplace.Platform.mapper.LocationToStringMapper;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LocationService.class})
@ExtendWith(SpringExtension.class)
class LocationServiceTest {
    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    @MockBean
    private LocationToStringMapper locationToStringMapper;

    /**
     * Method under test: {@link LocationService#getAllLocations()}
     */
    @Test
    void testGetAllLocations() {
        ArrayList<Location> locationList = new ArrayList<>();
        when(locationRepository.findAll()).thenReturn(locationList);
        List<Location> actualAllLocations = locationService.getAllLocations();
        assertSame(locationList, actualAllLocations);
        assertTrue(actualAllLocations.isEmpty());
        verify(locationRepository).findAll();
    }

    /**
     * Method under test: {@link LocationService#getAllLocationsToString()}
     */
    @Test
    void testGetAllLocationsToString() {
        when(locationRepository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<String> stringList = new ArrayList<>();
        when(locationToStringMapper.apply((List<Location>) any())).thenReturn(stringList);
        List<String> actualAllLocationsToString = locationService.getAllLocationsToString();
        assertSame(stringList, actualAllLocationsToString);
        assertTrue(actualAllLocationsToString.isEmpty());
        verify(locationRepository).findAll();
        verify(locationToStringMapper).apply((List<Location>) any());
    }

    /**
     * Method under test: {@link LocationService#findLocationsByValues(List)}
     */
    @Test
    void testFindLocationsByValues() {
        ArrayList<Location> locationList = new ArrayList<>();
        when(locationRepository.findLocationsByValue((List<String>) any())).thenReturn(locationList);
        List<Location> actualFindLocationsByValuesResult = locationService.findLocationsByValues(new ArrayList<>());
        assertSame(locationList, actualFindLocationsByValuesResult);
        assertTrue(actualFindLocationsByValuesResult.isEmpty());
        verify(locationRepository).findLocationsByValue((List<String>) any());
    }
}

