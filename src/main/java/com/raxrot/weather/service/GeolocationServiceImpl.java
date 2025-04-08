package com.raxrot.weather.service;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import com.raxrot.weather.exception.GeolocationException;
import com.raxrot.weather.model.Location;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class GeolocationServiceImpl implements GeolocationService {

    @Value("${ip2location.db.path}")
    private String DBPath;

    private IP2Location ip2Locator=new IP2Location();

    @PostConstruct
    public void init() {
        try {
            ip2Locator.Open(DBPath);
            log.info("IP2Location DB loaded: {}", DBPath);
        } catch (IOException ex) {
            log.error("Failed to load IP2Location DB", ex);
        }
    }

    @Override
    public Location getLocation(String ipAddress) throws GeolocationException {
        try {
            IPResult ipResult=ip2Locator.IPQuery(ipAddress);
            if (!"OK".equals(ipResult.getStatus())) {
                throw new GeolocationException("Geolocation failed " +ipResult.getStatus());
            }
            return new Location(ipResult.getCity(),ipResult.getRegion(),ipResult.getCountryLong(),ipResult.getCountryShort());
        } catch (IOException e) {
          throw new GeolocationException("Geolocation Failed");
        }
    }
}
