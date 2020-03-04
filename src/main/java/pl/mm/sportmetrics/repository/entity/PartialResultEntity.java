package pl.mm.sportmetrics.repository.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "partial_result")
public class PartialResultEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "total_result_id", referencedColumnName = "id")
    public TotalResultEntity totalResult;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "segment_id", referencedColumnName = "id")
    public SegmentEntity segment;


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

    public TotalResultEntity getTotalResult() {
        return totalResult;
    }

    public SegmentEntity getSegment() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartialResultEntity that = (PartialResultEntity) o;
        return segmentPosition == that.segmentPosition &&
                cumulativePosition == that.cumulativePosition &&
                Objects.equals(id, that.id) &&
                Objects.equals(totalResult, that.totalResult) &&
                Objects.equals(segment, that.segment) &&
                Objects.equals(segmentTime, that.segmentTime) &&
                Objects.equals(cumulativeTime, that.cumulativeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalResult, segment, segmentPosition, segmentTime, cumulativePosition, cumulativeTime);
    }
}
