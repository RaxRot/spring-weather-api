package com.raxrot.weather.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "realtime_weather")
public class RealtimeWeather {
    @Id
    @Column(name = "location_code")
    private String locationCode;
    private int temperature;
    private int humidity;
    private int precipitation;
    private int windSpeed;
    @Column(length = 50)
    private String status;
    @UpdateTimestamp
    private Timestamp lastUpdate;

    @OneToOne
    @JoinColumn(name = "location_code")
    @MapsId
    private Location location;
}
