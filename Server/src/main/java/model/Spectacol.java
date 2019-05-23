package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SPECTACOLE")
public class Spectacol implements Serializable, HasID<Integer>, Comparable<Spectacol>  {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;
    private String denumire;
    private String descriere;

    public Spectacol(String denumire, String descriere) {
        this.denumire = denumire;
        this.descriere = descriere;
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
