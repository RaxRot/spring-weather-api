package com.raxrot.weather.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private Date timestamp;
    private int status;
    private String path;
    private List<String> errors =new ArrayList<>();
}
