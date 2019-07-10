package com.example.demo.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class SpectacolDataDto {

    private Long id;
    private Float pret;
    private Long data;
    private Long spectacolId;

}
