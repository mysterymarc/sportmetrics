package pl.mm.sportmetrics.repository.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "competitor")
public class CompetitorEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public String name;

    public String city;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public CompetitorEntity(CompetitorEntity comp){
        this.id = comp.id;
        this.city = comp.city;
        this.name = comp.name;
    }

    public CompetitorEntity(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitorEntity that = (CompetitorEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }
}
