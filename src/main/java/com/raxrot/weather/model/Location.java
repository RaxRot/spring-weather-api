package com.raxrot.weather.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @Column(length = 12, nullable = false, unique = true)
    @NotBlank(message = "Code must not be blank")
    @Size(min = 3, max = 12, message = "Code must be between 3 and 12 characters")
    private String code;

    @Column(length = 128, nullable = false)
    @NotBlank(message = "City name must not be blank")
    @Size(min = 3, max = 128, message = "City name must be between 3 and 128 characters")
    private String cityName;

    @Column(length = 128)
    @NotBlank(message = "Region name must not be blank")
    @Size(min = 3, max = 128, message = "Region name must be between 3 and 128 characters")
    private String regionName;

    @Column(length = 64, nullable = false)
    @NotBlank(message = "Country name must not be blank")
    @Size(min = 2, max = 64, message = "Country name must be between 2 and 64 characters")
    private String countryName;

    @Column(length = 2, nullable = false)
    @NotBlank(message = "Country code must not be blank")
    @Size(max = 2, message = "Country code must be at most 2 characters")
    private String countryCode;

    private boolean enabled;
    private boolean trashed;

    @OneToOne(mappedBy = "location",cascade = CascadeType.ALL)
    private RealtimeWeather realtimeWeather;
}