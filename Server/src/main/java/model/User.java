package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class User implements Serializable, HasID<String>, Comparable<User> {
    @Id
    private String username;
    private String hash;
    @Enumerated(EnumType.STRING)
    private TipUser tip;

    public User() {
    }

    public User(String username, String hash) {
        this.username = username;
        this.hash = hash;
        this.tip = TipUser.MANAGER;
    }

    public User(String username, String hash, TipUser type) {
        this.username = username;
        this.hash = hash;
        this.tip = type;
    }

    @Override
    public String getID() {
        return username;
    }

    @Override
    public void setID(String s) {
        this.username = s;
    }


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public TipUser getTip() {
        return tip;
    }

    public void setTip(TipUser tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return username + " " + hash + " " + tip;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(getHash(), user.getHash()) &&
                getTip() == user.getTip();
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, getHash(), getTip());
    }

    @Override
    public int compareTo(User o) {
        if (this.getID().equals(o.getID()) && this.getTip().equals(o.getTip()) && this.getHash().equals(o.getHash()))
            return 0;
        else return 1;
    }
}
