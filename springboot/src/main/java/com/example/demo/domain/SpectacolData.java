package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"rezervari"})
@ToString(callSuper = true, exclude = {"rezervari"})
@Builder
public class SpectacolData extends BaseEntity<Long>{

    private Float pret;
    private Long data;

    @ManyToOne
    private Spectacol spectacolMapat;

    @OneToMany(mappedBy = "spectacolDataMapat", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Rezervare> rezervari;
}
