package com.raxrot.weather.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RealtimeWeatherDTO {

    private String location;
    private int temperature;
    private int humidity;
    private int precipitation;
    private int windSpeed;
    private String status;
    private Timestamp lastUpdate;
}