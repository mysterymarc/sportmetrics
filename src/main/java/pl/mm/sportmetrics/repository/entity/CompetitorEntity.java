package pl.mm.sportmetrics.repository.entity;

import javax.persistence.*;

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

}
