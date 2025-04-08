package com.raxrot.weather.repository;

import com.raxrot.weather.model.Location;
import com.raxrot.weather.model.RealtimeWeather;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class RealtimeWeatherRepositoryTest {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RealtimeWeatherRepository realtimeWeatherRepository;

    @DisplayName("Test realtime weather")
    @Test
    public void testRealtimeWeatherUpdate(){
        String locationCode = "NYC_USA";
        RealtimeWeather realtimeWeather=realtimeWeatherRepository.findById(locationCode).get();
        realtimeWeather.setTemperature(-2);
        realtimeWeather.setHumidity(32);
        realtimeWeather.setPrecipitation(43);
        realtimeWeather.setStatus("Snowy");
        realtimeWeather.setWindSpeed(12);

        RealtimeWeather updatedRealtimeWeather=realtimeWeatherRepository.save(realtimeWeather);
        assertThat(updatedRealtimeWeather).isNotNull();
        assertThat(updatedRealtimeWeather.getTemperature()).isEqualTo(-2);
    }

    @DisplayName("Test add weather in location")
    @Test
    public void testAddWeatherToLocation(){
        String code="DELHI_IN";

        Location location=locationRepository.findByCode(code);
        RealtimeWeather realtimeWeather = RealtimeWeather.builder()
                .temperature(10)
                .humidity(60)
                .status("Sunny")
                .windSpeed(10)
                .precipitation(70)
                .location(location)
                .build();

        location.setRealtimeWeather(realtimeWeather);
        Location updatedLocation=locationRepository.save(location);

        assertThat(updatedLocation).isNotNull();
        assertThat(updatedLocation.getRealtimeWeather().getLocationCode()).isEqualTo(code);
    }

    @DisplayName("Test not found by country code and city")
    @Test
    public void testFindByCountryCodeAndCityNotFound(){
        String countryCode="JP";
        String cityName="Tokyo";
        RealtimeWeather foundedRealtimeWeather=
                realtimeWeatherRepository.findByCountryCodeAndCity(countryCode,cityName);

        assertThat(foundedRealtimeWeather).isNull();
    }

    @DisplayName("Test found by country code and city")
    @Test
    public void testFindByCountryCodeAndCity(){
        String countryCode="IN";
        String cityName="New Delhi";
        RealtimeWeather foundedRealtimeWeather=
                realtimeWeatherRepository.findByCountryCodeAndCity(countryCode,cityName);

        assertThat(foundedRealtimeWeather).isNotNull();
        assertThat(foundedRealtimeWeather.getLocation().getCityName()).isEqualTo(cityName);
    }

    @DisplayName("Test findByLocationCode with untrashed location")
    @Test
    public void testFindByLocationCode() {
        String locationCode="NYC_USA";
        RealtimeWeather realtimeWeather=realtimeWeatherRepository.findByLocationCode(locationCode);
        assertThat(realtimeWeather).isNotNull();
        assertThat(realtimeWeather.getLocation().getCode()).isEqualTo(locationCode);
    }
}