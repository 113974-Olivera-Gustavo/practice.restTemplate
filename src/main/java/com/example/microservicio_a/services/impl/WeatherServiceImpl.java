package com.example.microservicio_a.services.impl;

import com.example.microservicio_a.clients.WeatherClient;
import com.example.microservicio_a.dtos.*;
import com.example.microservicio_a.models.*;
import com.example.microservicio_a.services.WeatherService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final ModelMapper modelMapper;
    private final WeatherClient weatherClient;
    @Autowired
    public WeatherServiceImpl(ModelMapper modelMapper, WeatherClient weatherClient) {
        this.modelMapper = modelMapper;
        this.weatherClient = weatherClient;
    }

    @CircuitBreaker(name = "circuitW", fallbackMethod = "handleFallback")
    @Override
    public Location getLocation(Long id) {
        return weatherClient.getLocations(id);
    }
    @Override
    public Wind getWind(Long id) {
        return weatherClient.getWinds(id);
    }
    @Override
    public List<AirQuality> getAirQuality() {
        return weatherClient.getAirQuality();
    }
    @Override
    public List<Temperature> getTemperature(Long id) {
        return weatherClient.getTemperatureByLocation(id);
    }
    @Override
    public List<Cloudiness> getCloudiness(Long id) {
        return weatherClient.getCloudinessByLocation(id);
    }


    @CircuitBreaker(name = "circuitW", fallbackMethod = "handleFallback")
    @Override
    public WeatherResponse getWeather(Long id, LocalDateTime datetime) {
        Location locationInfo = getLocation(id);
        Wind windForLocation = getWind(id);

        String windDirection = convertirDireccion(windForLocation.getDirectionInDegrees());
        String windDescription = windForLocation.getSpeed() + " Km/h from " + windDirection;

        List<Temperature> temperatures = weatherClient.getTemperatureByLocationAndDate(id, datetime);
        TemperatureResponse temperatureResponse = new TemperatureResponse();
        if (!temperatures.isEmpty()) {
            Temperature latestTemperature = temperatures.get(temperatures.size() - 1);
            temperatureResponse.setValue(latestTemperature.getTemperature());
            temperatureResponse.setUnit(latestTemperature.getUnit());
        }

        List<Cloudiness> cloudinessList = weatherClient.getCloudinessByLocationAndDate(id, datetime);
        CloudinessResponse cloudinessResponse = new CloudinessResponse();
        if (!cloudinessList.isEmpty()) {
            Cloudiness latestCloudiness = cloudinessList.get(cloudinessList.size() - 1);
            cloudinessResponse.setIndex(BigDecimal.valueOf(latestCloudiness.getCloudiness()));
            // Asigna la descripción de acuerdo al índice
            cloudinessResponse.setDescription(getCloudinessDescription(latestCloudiness.getCloudiness()));
        }

        WindResponse windResponse = new WindResponse(windDescription);

        return WeatherResponse.builder()
                .location(modelMapper.map(locationInfo, LocationResponse.class))
                .temperature(temperatureResponse)
                .wind(windResponse)
                .cloudiness(cloudinessResponse)
                .build();
    }


    @Override
    public List<Temperature> getTemperatureByDate(Long id, LocalDateTime date) {
       return weatherClient.getTemperatureByLocationAndDate(id, date);
    }

    @Override
    public List<Cloudiness> getCloudinessByDate(Long id, LocalDateTime date) {
        return weatherClient.getCloudinessByLocationAndDate(id, date);
    }

    private String getCloudinessDescription(Integer cloudiness) {
        if (cloudiness == 0) {
            return "Clear Sky";
        } else if (cloudiness >= 1 && cloudiness <= 3) {
            return "Few clouds";
        } else if (cloudiness >= 4 && cloudiness <= 6) {
            return "Sky half cludy";
        } else if (cloudiness >= 7 && cloudiness <= 8) {
            return "Sky completely cludy";
        } else {
            return "Unknown";
        }
    }


    private String convertirDireccion(int direccion){
        String[] direcciones = {"N", "NE", "E", "SE","S", "SW", "W", "NW"};
        int indice = (int) Math.round(((double)direccion % 360) / 45d);
        return direcciones[indice];
    }

}
