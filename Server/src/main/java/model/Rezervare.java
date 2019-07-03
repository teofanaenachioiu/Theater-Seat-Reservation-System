package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "REZERVARE")
public class Rezervare implements Serializable, HasID<Integer>, Comparable<Rezervare> {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Integer id;
    private Integer pozitieX;
    private Integer pozitieY;
    private Integer numar;
    private float pret;
    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Spectacol spectacol;

    public Rezervare(Integer pozitieX, Integer pozitieY, Integer numar, float pret, Spectacol spectacol) {
        this.pozitieX = pozitieX;
        this.pozitieY = pozitieY;
        this.numar = numar;
        this.pret = pret;
        this.spectacol = spectacol;
    }

    public Spectacol getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(Spectacol spectacol) {
        this.spectacol = spectacol;
    }

    @Override
    public int compareTo(Rezervare o) {
        if (this.equals(o))
            return 0;
        else return 1;
    }

    @Override
    public Integer getID() {
        return null;
    }

    @Override
    public void setID(Integer integer) {
        this.id=integer;
    }

    public Integer getPozitieX() {
        return pozitieX;
    }

    public void setPozitieX(Integer pozitieX) {
        this.pozitieX = pozitieX;
    }

    public Integer getPozitieY() {
        return pozitieY;
    }

    public void setPozitieY(Integer pozitieY) {
        this.pozitieY = pozitieY;
    }

    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rezervare)) return false;
        Rezervare rezervare = (Rezervare) o;
        return Float.compare(rezervare.getPret(), getPret()) == 0 &&
                Objects.equals(id, rezervare.id) &&
                Objects.equals(getPozitieX(), rezervare.getPozitieX()) &&
                Objects.equals(getPozitieY(), rezervare.getPozitieY()) &&
                Objects.equals(getNumar(), rezervare.getNumar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getPozitieX(), getPozitieY(), getNumar(), getPret());
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "id=" + id +
                ", pozitieX=" + pozitieX +
                ", pozitieY=" + pozitieY +
                ", numar=" + numar +
                ", pret=" + pret +
                '}';
    }
}
