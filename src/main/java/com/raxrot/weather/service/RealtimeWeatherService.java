package com.raxrot.weather.service;

import com.raxrot.weather.exception.LocationNotFoundException;
import com.raxrot.weather.model.Location;
import com.raxrot.weather.model.RealtimeWeather;

public interface RealtimeWeatherService {
     RealtimeWeather getByLocation(Location location) throws LocationNotFoundException;
     RealtimeWeather update(String locationCode, RealtimeWeather realtimeWeather) throws LocationNotFoundException;
}
