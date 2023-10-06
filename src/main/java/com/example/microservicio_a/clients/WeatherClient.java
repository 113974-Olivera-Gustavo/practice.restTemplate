package com.example.microservicio_a.clients;

import com.example.microservicio_a.dtos.AirQualityResponse;
import com.example.microservicio_a.dtos.CloudinessResponse;
import com.example.microservicio_a.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WeatherClient {
    private final RestTemplate restTemplate;

    private String location_url = "https://my-json-server.typicode.com/LCIV-2023/fake-weather/location";
    private String wind_url = "https://my-json-server.typicode.com/LCIV-2023/fake-weather/wind";
    private String air_quality ="https://my-json-server.typicode.com/LCIV-2023/fake-weather/air_quality";
    private String temperature= "https://my-json-server.typicode.com/LCIV-2023/fake-weather/temperature";
    private String cloudiness= "https://my-json-server.typicode.com/LCIV-2023/fake-weather/cloudiness";


    public Location getLocations(Long id) {
        String url = location_url + "/" + id;
        ResponseEntity<Location> response = restTemplate.exchange(url, HttpMethod.GET, null, Location.class);
        Location locations = response.getBody();
        return locations;
    }
    public List<Cloudiness> getCloudinessByLocation(Long locationId) {
        String url = cloudiness + "?location_id=" + locationId;
        ResponseEntity<Cloudiness[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Cloudiness[].class);
        Cloudiness[] cloudinessResponses = response.getBody();
        return Arrays.asList(cloudinessResponses);
    }




    public Wind getWinds(Long id){
        String url = wind_url + "/" + id;
        ResponseEntity<Wind> response = restTemplate.exchange(url, HttpMethod.GET, null, Wind.class);
        Wind winds = response.getBody();
        return winds;

    }

    public List<Temperature> getTemperatureByLocation(Long locationId) {
        String url = temperature + "?location_id=" + locationId;
        ResponseEntity<Temperature[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Temperature[].class);
        Temperature[] temperatures = response.getBody();
        return Arrays.asList(temperatures);
    }

    public List<AirQuality> getAirQuality() {

        ResponseEntity<AirQuality[]> response = restTemplate.exchange(air_quality, HttpMethod.GET, null, AirQuality[].class);
        AirQuality[] airQualities = response.getBody();
        return Arrays.asList(airQualities);
    }

    public List<Temperature> getTemperatureByLocationAndDate(Long locationId, LocalDateTime datetime) {
        // Formatea la fecha y hora en el formato esperado en la URL
        String formattedDatetime = DateTimeFormatter.ISO_DATE_TIME.format(datetime);
        // Construye la URL con los parámetros de ubicación y fecha
        String url = temperature + "?location_id=" + locationId + "&datetime=" + formattedDatetime;

        ResponseEntity<Temperature[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Temperature[].class);
        Temperature[] temperatures = response.getBody();
        return Arrays.asList(temperatures);
    }

    public List<Cloudiness> getCloudinessByLocationAndDate(Long locationId, LocalDateTime datetime) {
        // Formatea la fecha y hora en el formato esperado en la URL
        String formattedDatetime = DateTimeFormatter.ISO_DATE_TIME.format(datetime);

        // Construye la URL con los parámetros de ubicación y fecha
        String url = cloudiness + "?location_id=" + locationId + "&datetime=" + formattedDatetime;

        ResponseEntity<Cloudiness[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Cloudiness[].class);
        Cloudiness[] cloudinessResponses = response.getBody();
        return Arrays.asList(cloudinessResponses);
    }

}
