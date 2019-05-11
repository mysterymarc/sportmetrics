package pl.mm.sportmetrics.model.database;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "competition")
public class Competition {

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
