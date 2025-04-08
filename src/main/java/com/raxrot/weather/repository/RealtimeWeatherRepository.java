package com.raxrot.weather.repository;

import com.raxrot.weather.model.RealtimeWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RealtimeWeatherRepository extends JpaRepository<RealtimeWeather, String> {
    @Query("select r from RealtimeWeather r where r.location.countryCode=:countryCode and r.location.cityName=:city")
    RealtimeWeather findByCountryCodeAndCity(@Param("countryCode") String countryCode,@Param("city") String city);
}
