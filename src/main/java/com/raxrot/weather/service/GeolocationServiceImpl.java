package com.raxrot.weather.service;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import com.raxrot.weather.exception.GeolocationException;
import com.raxrot.weather.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class GeolocationServiceImpl implements GeolocationService {

    private final String DBPath="src/main/resources/ip2locdb/IP2LOCATION-LITE-DB3.BIN";
    private IP2Location ip2Locator=new IP2Location();

    public GeolocationServiceImpl() {
        try {
            ip2Locator.Open(DBPath);
        }catch (IOException ex){
            log.error(ex.getMessage(),ex);
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
