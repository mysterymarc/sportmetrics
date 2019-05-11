package pl.mm.sportmetrics.model.database;

import javax.persistence.*;

@Entity
@Table(name = "segment")
public class Segment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    public Competition competition;

    @Column(name="order_number")
    public int orderNumber;

    public Long getId() {
        return id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getName() {
        return name;
    }

    public String name;
}

