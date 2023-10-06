package com.example.microservicio_a.controllers;

import com.example.microservicio_a.clients.WeatherClient;
import com.example.microservicio_a.dtos.AirQualityResponse;
import com.example.microservicio_a.dtos.WeatherResponse;
import com.example.microservicio_a.models.AirQuality;
import com.example.microservicio_a.models.Location;
import com.example.microservicio_a.models.Temperature;
import com.example.microservicio_a.models.Wind;
import com.example.microservicio_a.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherClient weatherClient;
    private final WeatherService weatherService;
    @Autowired
    public WeatherController(WeatherClient weatherClient, WeatherService weatherService) {
        this.weatherClient = weatherClient;
        this.weatherService = weatherService;
    }

    @GetMapping("/locations/{id}")
    public Location getLocations(Long id) {
        return weatherClient.getLocations(id);
    }
    @GetMapping("/wind/{id}")
    public Wind getWind(Long id) {
        return weatherClient.getWinds(id);
    }
    @GetMapping("/air-quality")
    public List<AirQuality> getAirQuality() {
        return weatherService.getAirQuality();
    }

    @GetMapping("/temperature/{id}")
    public List<Temperature> getTemperature(Long id) {
        return weatherService.getTemperature(id);
    }
    @GetMapping("/cloudiness/{id}")
    public List<com.example.microservicio_a.models.Cloudiness> getCloudiness(Long id) {
        return weatherService.getCloudiness(id);
    }
    @GetMapping("/weather/{id}")
    public ResponseEntity<WeatherResponse> getWeather(
            @PathVariable("id") Long id,
            @RequestParam("datetime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") LocalDateTime datetime) {
        WeatherResponse response = weatherService.getWeather(id, datetime);

        return ResponseEntity.ok(response);
    }

}
