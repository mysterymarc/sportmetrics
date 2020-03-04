package pl.mm.sportmetrics.repository.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "total_result")
public class TotalResultEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    public CompetitionEntity competition;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "competitor_id", referencedColumnName = "id")
    public CompetitorEntity competitor;

    public int position;

    @Column(name="total_time")
    public Time totalTime;

    @Column(name="delay_time")
    public Time delayTime;

    public Long getId() {
        return id;
    }

    public CompetitionEntity getCompetition() {
        return competition;
    }

    public CompetitorEntity getCompetitor() {
        return competitor;
    }

    public int getPosition() {
        return position;
    }

    public Time getTotalTime() {
        return totalTime;
    }

    public Time getDelayTime() {
        return delayTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalResultEntity that = (TotalResultEntity) o;
        return position == that.position &&
                Objects.equals(id, that.id) &&
                Objects.equals(competition, that.competition) &&
                Objects.equals(competitor, that.competitor) &&
                Objects.equals(totalTime, that.totalTime) &&
                Objects.equals(delayTime, that.delayTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, competition, competitor, position, totalTime, delayTime);
    }
}
