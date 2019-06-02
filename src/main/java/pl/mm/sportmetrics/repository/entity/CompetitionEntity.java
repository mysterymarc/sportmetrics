package pl.mm.sportmetrics.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "competition")
public class CompetitionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
