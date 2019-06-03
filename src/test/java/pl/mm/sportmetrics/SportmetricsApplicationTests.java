package pl.mm.sportmetrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mm.sportmetrics.logic.Calculation;
import pl.mm.sportmetrics.model.businesslayer.Result;
import pl.mm.sportmetrics.model.businesslayer.ResultsForRunner;
import pl.mm.sportmetrics.model.businesslayer.ResultsForRunnersGroup;
import pl.mm.sportmetrics.model.businesslayer.SegmentsStatistic;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SportmetricsApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void checkAvgCalculation(){
        //given
        ResultsForRunnersGroup group = new ResultsForRunnersGroup();
        ResultsForRunner runner1 = new ResultsForRunner();
        Result result1 = new Result();
        Result result2 = new Result();
        result1.setTime(Time.valueOf("00:00:20"));
        result2.setTime(Time.valueOf("00:01:00"));
        runner1.addSegmentResult(result1);
        runner1.addSegmentResult(result2);
        ResultsForRunner runner2 = new ResultsForRunner();
        Result result3 = new Result();
        Result result4 = new Result();
        result3.setTime(Time.valueOf("00:00:40"));
        result4.setTime(Time.valueOf("00:03:00"));
        runner2.addSegmentResult(result3);
        runner2.addSegmentResult(result4);
        group.add(runner1);
        group.add(runner2);
        //when
        SegmentsStatistic statistic = new Calculation().getAvgFromResults(group);
        //then
        assertThat(statistic.getStatistic(0).getValue()).isEqualTo(Time.valueOf("00:00:30"));
        assertThat(statistic.getStatistic(1).getValue()).isEqualTo(Time.valueOf("00:02:00"));
    }
}
