package com.raxrot.weather.controller;

import com.raxrot.weather.exception.LocationNotFoundException;
import com.raxrot.weather.model.Location;
import com.raxrot.weather.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Location>> listLocations() {
        List<Location>locations=locationService.findUnTrashed();
        if (locations.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Location> findByCode(@PathVariable String code) {
        Location location=locationService.findByCode(code);
        if (location==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }

    @PutMapping
    public ResponseEntity<Location> update(@RequestBody @Valid Location location) {
        try {
           Location updatedLocation = locationService.update(location);
           return ResponseEntity.ok(updatedLocation);
        } catch (LocationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteLocation(@PathVariable String code) {
       try {
           locationService.softDelete(code);
           return ResponseEntity.noContent().build();
       } catch (LocationNotFoundException e) {
           return ResponseEntity.notFound().build();
       }
    }
}
