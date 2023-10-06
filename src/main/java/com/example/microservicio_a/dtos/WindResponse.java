package com.example.microservicio_a.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class WindResponse {
    private String wind;

    public WindResponse(String windDescription) {
        this.wind = windDescription;
    }
}
