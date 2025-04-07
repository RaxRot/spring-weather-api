package com.raxrot.weather.service;

import com.raxrot.weather.exception.LocationNotFoundException;
import com.raxrot.weather.model.Location;
import com.raxrot.weather.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location add(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> findUnTrashed() {
        return locationRepository.findUnTrashed();
    }

    @Override
    public Location findByCode(String code) {
        return locationRepository.findByCode(code);
    }

    @Override
    @Transactional
    public Location update(Location locationInRequest) throws LocationNotFoundException {
        String code = locationInRequest.getCode();
        Location locationInDb = locationRepository.findByCode(code);
        if (locationInDb == null) {
            throw new LocationNotFoundException("No location found with this code");
        }
        locationInDb.setCityName(locationInRequest.getCityName());
        locationInDb.setRegionName(locationInRequest.getRegionName());
        locationInDb.setCountryCode(locationInRequest.getCountryCode());
        locationInDb.setCountryName(locationInRequest.getCountryName());
        locationInDb.setEnabled(locationInRequest.isEnabled());
        return locationRepository.save(locationInDb);
    }

    @Override
    @Transactional
    public void softDelete(String code) throws LocationNotFoundException {
        if (!locationRepository.existsById(code)) {
            throw new LocationNotFoundException("No location found with this code");
        }
        locationRepository.trashedByCode(code);
    }

}
