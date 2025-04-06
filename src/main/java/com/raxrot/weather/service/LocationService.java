package com.raxrot.weather.service;

import com.raxrot.weather.exception.LocationNotFoundException;
import com.raxrot.weather.model.Location;

import java.util.List;

public interface LocationService {
    Location add(Location location);
    List<Location> findUnTrashed();
    Location findByCode(String code);
    Location update(Location locationInRequest) throws LocationNotFoundException;
}
