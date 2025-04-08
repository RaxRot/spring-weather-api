package com.raxrot.weather.service;

import com.raxrot.weather.exception.LocationNotFoundException;
import com.raxrot.weather.model.Location;
import com.raxrot.weather.model.RealtimeWeather;
import com.raxrot.weather.repository.LocationRepository;
import com.raxrot.weather.repository.RealtimeWeatherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RealtimeWeatherServiceImpl implements RealtimeWeatherService {

    private RealtimeWeatherRepository realtimeWeatherRepo;
    private LocationRepository locationRepo;
    public RealtimeWeatherServiceImpl(RealtimeWeatherRepository realtimeWeatherRepo,
                                      LocationRepository locationRepo) {
        this.realtimeWeatherRepo = realtimeWeatherRepo;
        this.locationRepo = locationRepo;
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

    @Override
    @Transactional
    public RealtimeWeather update(String locationCode, RealtimeWeather input) throws LocationNotFoundException {
        Location location = locationRepo.findByCode(locationCode);
        if (location == null || location.isTrashed()) {
            throw new LocationNotFoundException("Location not found or trashed");
        }

        RealtimeWeather existing = realtimeWeatherRepo.findByLocationCode(locationCode);
        if (existing == null) {
            throw new LocationNotFoundException("No existing weather for location");
        }

        existing.setTemperature(input.getTemperature());
        existing.setHumidity(input.getHumidity());
        existing.setPrecipitation(input.getPrecipitation());
        existing.setWindSpeed(input.getWindSpeed());
        existing.setStatus(input.getStatus());

        return realtimeWeatherRepo.save(existing);
    }
}
