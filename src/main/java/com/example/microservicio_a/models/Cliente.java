package com.example.microservicio_a.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    private Long id;
    private String email;
    private String secret;
    private String tempFavorite;

}
