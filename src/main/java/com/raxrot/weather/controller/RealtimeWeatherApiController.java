package com.raxrot.weather.controller;

import com.raxrot.weather.dto.RealtimeWeatherDTO;
import com.raxrot.weather.exception.GeolocationException;
import com.raxrot.weather.exception.LocationNotFoundException;
import com.raxrot.weather.model.Location;
import com.raxrot.weather.model.RealtimeWeather;
import com.raxrot.weather.service.GeolocationService;
import com.raxrot.weather.service.RealtimeWeatherService;
import com.raxrot.weather.utility.CommonUtility;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/realtime")
public class RealtimeWeatherApiController {

   private GeolocationService locationService;
   private RealtimeWeatherService realtimeWeatherService;
   private ModelMapper modelMapper;

   public RealtimeWeatherApiController(RealtimeWeatherService realtimeWeatherService,
                                       GeolocationService locationService,
                                       ModelMapper modelMapper) {
       this.realtimeWeatherService = realtimeWeatherService;
       this.locationService = locationService;
       this.modelMapper = modelMapper;
   }

   @GetMapping
    public ResponseEntity<?> getRealtimeWeatherByIP(HttpServletRequest request)  {
      String ipAddress = CommonUtility.getIpAddress(request);

       try {
          Location locationFromIP = locationService.getLocation(ipAddress);
          RealtimeWeather realtimeWeather = realtimeWeatherService.getByLocation(locationFromIP);

           RealtimeWeatherDTO dto=modelMapper.map(realtimeWeather, RealtimeWeatherDTO.class);
          return ResponseEntity.ok(dto);
       } catch (GeolocationException e) {
           log.error(e.getMessage());
           return ResponseEntity.badRequest().build();
       } catch (LocationNotFoundException e) {
           log.error(e.getMessage());
           return ResponseEntity.notFound().build();
       }
   }

}
