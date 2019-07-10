package com.example.demo.domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"spectacolDatas"})
@ToString(callSuper = true, exclude = {"spectacolDatas"})
@Builder
public class Spectacol extends BaseEntity<Long> {

    private String denumire;
    private String descriere;

    @OneToMany(mappedBy = "spectacolMapat", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<SpectacolData> spectacolDatas;
}
