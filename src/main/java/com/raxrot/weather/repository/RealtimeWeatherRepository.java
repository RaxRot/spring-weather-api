package com.raxrot.weather.repository;

import com.raxrot.weather.model.RealtimeWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealtimeWeatherRepository extends JpaRepository<RealtimeWeather, String> {
}
