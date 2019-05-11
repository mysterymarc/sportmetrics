package pl.mm.sportmetrics.model.database;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "partial_result")
public class PartialResult {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "total_result_id", referencedColumnName = "id")
    public TotalResult totalResult;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "segment_id", referencedColumnName = "id")
    public Segment segment;


    @Column(name="segment_position")
    public int segmentPosition;

    @Column(name="segment_time")
    public Time segmentTime;

    @Column(name="cumulative_position")
    public int cumulativePosition;

    @Column(name="cumulative_time")
    public Time cumulativeTime;

    public Long getId() {
        return id;
    }

    public TotalResult getTotalResult() {
        return totalResult;
    }

    public Segment getSegment() {
        return segment;
    }

    public int getSegmentPosition() {
        return segmentPosition;
    }

    public Time getSegmentTime() {
        return segmentTime;
    }

    public int getCumulativePosition() {
        return cumulativePosition;
    }

    public Time getCumulativeTime() {
        return cumulativeTime;
    }
}
