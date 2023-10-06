package com.example.microservicio_a.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private Long id;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
