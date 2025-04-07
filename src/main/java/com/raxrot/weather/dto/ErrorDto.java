package com.raxrot.weather.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private Date timestamp;
    private int status;
    private String path;
    private String error;
}
