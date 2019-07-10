package com.example.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Rezervare extends BaseEntity<Long> {

    private Integer pozitieX;
    private Integer pozitieY;
    private Integer nrScaun;

    @ManyToOne
    private Spectator spectatorMapat;

    @ManyToOne
    private SpectacolData spectacolDataMapat;
}
