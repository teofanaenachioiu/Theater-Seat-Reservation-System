package com.example.demo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class SpectacolDto {

    private Long id;
    private String denumire;
    private String descriere;

}
