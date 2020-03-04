package pl.mm.sportmetrics.repository.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentEntity that = (SegmentEntity) o;
        return orderNumber == that.orderNumber &&
                Objects.equals(id, that.id) &&
                Objects.equals(competition, that.competition) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, competition, orderNumber, name);
    }
}

