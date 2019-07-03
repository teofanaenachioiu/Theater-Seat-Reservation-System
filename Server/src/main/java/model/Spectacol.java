package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "SPECTACOLE")
public class Spectacol implements Serializable, HasID<Integer>, Comparable<Spectacol>  {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "show_id")
    private Integer id;
    private String denumire;
    private String descriere;

    public Set<Rezervare> getRezarvari() {
        return rezarvari;
    }

    public void setRezarvari(Set<Rezervare> rezarvari) {
        this.rezarvari = rezarvari;
    }

    @OneToMany(mappedBy = "spectacol",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Set<Rezervare> rezarvari;

    public Spectacol(String denumire, String descriere) {
        this.denumire = denumire;
        this.descriere = descriere;
    }

    public void addRezervare(Rezervare rezervare){
        this.rezarvari.add(rezervare);
    }

    public void removeRezervare(Rezervare rezervare){
        this.rezarvari.remove(rezervare);
    }

    public Spectacol() {
    }

    @Override
    public int compareTo(Spectacol o) {
        if (this.equals(o))
            return 0;
        else return 1;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spectacol)) return false;
        Spectacol spectacol = (Spectacol) o;
        return Objects.equals(id, spectacol.id) &&
                Objects.equals(getDenumire(), spectacol.getDenumire()) &&
                Objects.equals(getDescriere(), spectacol.getDescriere());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getDenumire(), getDescriere());
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer integer) {
        this.id=integer;
    }
}
