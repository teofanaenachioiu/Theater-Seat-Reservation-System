package com.example.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"rezervari"})
@ToString(exclude = {"rezervari"})
@Builder
public class Spectator {

    @Id
    private String nume;
    private String password;

    @OneToMany(mappedBy = "spectatorMapat", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Rezervare> rezervari;
}
