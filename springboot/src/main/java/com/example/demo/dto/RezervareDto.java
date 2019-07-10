package com.example.demo.dto;

import com.example.demo.domain.SpectacolData;
import com.example.demo.domain.Spectator;
import lombok.*;

import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class RezervareDto {

    private Long id;
    private String denumire;
    private String descriere;
    private Long data;
    private Float pret;
    private int pozitieX;
    private int pozitieY;
    private int nrScaun;

}
