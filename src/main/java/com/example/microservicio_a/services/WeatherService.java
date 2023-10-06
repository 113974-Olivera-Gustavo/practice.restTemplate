package com.example.microservicio_a.services;

import com.example.microservicio_a.dtos.WeatherResponse;
import com.example.microservicio_a.models.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public interface WeatherService {
    Location getLocation(Long id);

    Wind getWind(Long id);
    List<AirQuality> getAirQuality();
    List<Temperature> getTemperature(Long id);
    List<Cloudiness> getCloudiness(Long id);
    WeatherResponse getWeather(Long id, LocalDateTime dateTime);
    List<Temperature> getTemperatureByDate(Long id, LocalDateTime date);
    List<Cloudiness> getCloudinessByDate(Long id, LocalDateTime date);
}
