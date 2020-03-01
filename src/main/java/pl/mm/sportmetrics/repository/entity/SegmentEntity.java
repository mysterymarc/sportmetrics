package pl.mm.sportmetrics.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "segment")
public class SegmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    public CompetitionEntity competition;

    @Column(name = "order_number")
    public int orderNumber;

    public String name;

    public Long getId() {
        return id;
    }

    public CompetitionEntity getCompetition() {
        return competition;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getName() {
        return name;
    }
}

