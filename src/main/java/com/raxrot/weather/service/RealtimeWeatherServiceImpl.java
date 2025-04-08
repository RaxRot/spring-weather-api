package com.raxrot.weather.service;

import com.raxrot.weather.exception.LocationNotFoundException;
import com.raxrot.weather.model.Location;
import com.raxrot.weather.model.RealtimeWeather;
import com.raxrot.weather.repository.RealtimeWeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class RealtimeWeatherServiceImpl implements RealtimeWeatherService {

    private RealtimeWeatherRepository realtimeWeatherRepo;
    public RealtimeWeatherServiceImpl(RealtimeWeatherRepository realtimeWeatherRepo) {
        this.realtimeWeatherRepo = realtimeWeatherRepo;
    }

    @Override
    public RealtimeWeather getByLocation(Location location) throws LocationNotFoundException {
        String countryCode = location.getCountryCode();
        String cityName= location.getCityName();

        RealtimeWeather realtimeWeather = realtimeWeatherRepo.findByCountryCodeAndCity(countryCode, cityName);
        if (realtimeWeather == null) {
            throw new LocationNotFoundException("No location found");
        }
        return realtimeWeather;
    }
}
