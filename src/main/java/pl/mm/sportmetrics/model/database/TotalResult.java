package pl.mm.sportmetrics.model.database;

import javax.persistence.*;
import java.sql.Time;
import java.time.Duration;

@Entity
@Table(name = "total_result")
public class TotalResult {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    public Competition competition;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "competitor_id", referencedColumnName = "id")
    public Competitor competitor;

    public int position;

    @Column(name="total_time")
    public Time totalTime;

    @Column(name="delay_time")
    public Time delayTime;

    public Long getId() {
        return id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Competitor getCompetitor() {
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

    public TotalResult(){

    }

    public TotalResult(TotalResult result){
        this.id = result.id;
        this.position = result.position;
        this.totalTime = (Time) result.totalTime.clone();
        this.delayTime = (Time) result.delayTime.clone();
        this.competitor = new Competitor(result.competitor);
    }
}
