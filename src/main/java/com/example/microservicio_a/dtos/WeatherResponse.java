package com.example.microservicio_a.dtos;

import com.example.microservicio_a.models.Cloudiness;
import com.example.microservicio_a.models.Location;
import com.example.microservicio_a.models.Temperature;
import com.example.microservicio_a.models.Wind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponse {
    private LocationResponse location;
    private TemperatureResponse temperature;
    private WindResponse wind;
    private CloudinessResponse cloudiness;
}
