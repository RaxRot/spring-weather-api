package com.raxrot.weather.controller;

import com.raxrot.weather.model.Location;
import com.raxrot.weather.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/locations")
public class LocationApiController {
    private final LocationService locationService;
    public LocationApiController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody @Valid Location location) {
        Location addedLocation= locationService.add(location);
        URI uri=URI.create("/v1/locations/"+location.getCode());
        return ResponseEntity.created(uri).body(addedLocation);
    }
}
