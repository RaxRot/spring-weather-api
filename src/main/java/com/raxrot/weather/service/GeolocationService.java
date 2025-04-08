package com.raxrot.weather.service;

import com.raxrot.weather.exception.GeolocationException;
import com.raxrot.weather.model.Location;

public interface GeolocationService {
  Location getLocation(String ipAddress) throws GeolocationException;
}
