package pl.mm.sportmetrics.domain.model;

import org.junit.Test;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleStatisticTest {

    @Test
    public void givenStatisticWithTimeLessThanOtherStatisticWhenEvaluationThenDescriptionSetToWin(){
        //given
        SingleStatistic examinedStat = new SingleStatistic(Time.valueOf("00:00:04"));
        SingleStatistic otherStat = new SingleStatistic(Time.valueOf("00:00:05"));
        //when
        examinedStat.evaluateDescriptionWithWinLossByComparison(otherStat);
        //then
        assertThat(examinedStat.getDescription()).isEqualTo("win");
    }

    @Test
    public void givenStatisticWithTimeLongerThanOtherStatisticWhenEvaluationThenDescriptionSetToLoss(){
        //given
        SingleStatistic examinedStat = new SingleStatistic(Time.valueOf("00:01:00"));
        SingleStatistic otherStat = new SingleStatistic(Time.valueOf("00:00:59"));
        //when
        examinedStat.evaluateDescriptionWithWinLossByComparison(otherStat);
        //then
        assertThat(examinedStat.getDescription()).isEqualTo("loss");
    }

    @Test
    public void givenStatisticWithTimeEqualToOtherStatisticWhenEvaluationThenDescriptionSetToDraw(){
        //given
        SingleStatistic examinedStat = new SingleStatistic(Time.valueOf("01:00:00"));
        SingleStatistic otherStat = new SingleStatistic(Time.valueOf("01:00:00"));
        //when
        examinedStat.evaluateDescriptionWithWinLossByComparison(otherStat);
        //then
        assertThat(examinedStat.getDescription()).isEqualTo("draw");
    }

    @Test
    public void givenStatisticWithTimeSetToZeroAndOtherNonZeroStatisticWhenEvaluationThenDescriptionSetToLoss(){
        //given
        SingleStatistic examinedStat = new SingleStatistic(Time.valueOf("00:00:00"));
        SingleStatistic otherStat = new SingleStatistic(Time.valueOf("00:00:01"));
        //when
        examinedStat.evaluateDescriptionWithWinLossByComparison(otherStat);
        //then
        assertThat(examinedStat.getDescription()).isEqualTo("loss");
    }

    @Test
    public void givenStatisticWithTimeSetToNonZeroAndOtherZeroStatisticWhenEvaluationThenDescriptionSetToWin(){
        //given
        SingleStatistic examinedStat = new SingleStatistic(Time.valueOf("00:00:01"));
        SingleStatistic otherStat = new SingleStatistic(Time.valueOf("00:00:00"));
        //when
        examinedStat.evaluateDescriptionWithWinLossByComparison(otherStat);
        //then
        assertThat(examinedStat.getDescription()).isEqualTo("win");
    }


    @Test
    public void givenStatisticWithTimeSetToZeroAndOtherZeroStatisticWhenEvaluationThenDescriptionSetToDraw(){
        //given
        SingleStatistic examinedStat = new SingleStatistic(Time.valueOf("00:00:00"));
        SingleStatistic otherStat = new SingleStatistic(Time.valueOf("00:00:00"));
        //when
        examinedStat.evaluateDescriptionWithWinLossByComparison(otherStat);
        //then
        assertThat(examinedStat.getDescription()).isEqualTo("draw");
    }
}
