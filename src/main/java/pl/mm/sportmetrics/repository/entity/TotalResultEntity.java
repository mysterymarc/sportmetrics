package pl.mm.sportmetrics.repository.entity;

import javax.persistence.*;
import java.sql.Time;

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

    public TotalResultEntity(){

    }

    public TotalResultEntity(TotalResultEntity result){
        this.id = result.id;
        this.position = result.position;
        this.totalTime = (Time) result.totalTime.clone();
        this.delayTime = (Time) result.delayTime.clone();
        this.competitor = new CompetitorEntity(result.competitor);
    }
}
