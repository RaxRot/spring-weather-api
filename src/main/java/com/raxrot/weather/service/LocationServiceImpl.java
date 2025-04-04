package com.raxrot.weather.service;

import com.raxrot.weather.model.Location;
import com.raxrot.weather.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location add(Location location) {
        return locationRepository.save(location);
    }
}
