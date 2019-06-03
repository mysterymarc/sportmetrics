package pl.mm.sportmetrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mm.sportmetrics.logic.Calculation;
import pl.mm.sportmetrics.model.businesslayer.*;
import pl.mm.sportmetrics.model.viewlayer.*;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SportmetricsApplicationTests {

    @Test
    public void contextLoads() {//TODO: czy to potrzebne?
    }

    @Test
    public void givenResultsForRunnersGroupWhenGroupAvgCalculatedThenReturnedCorrectResult(){
        //given
        //TODO: Czy given moze byc az tak rozbudowany?
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

    @Test
    public void givenStatisticsWhenWinLossEvaluatedThenReturnedCorrectResult(){
        //given
        SegmentsStatistic stat1 = new SegmentsStatistic();
        SegmentsStatistic stat2 = new SegmentsStatistic();
//TODO: teoretycznie mozna by to sprawdzic na nizszym poziomie (bezposrednio tworzac Single statistic),
// wtedy mamy 4 razy ten sam test na roznych danych. Tutaj teoretycznie sprawdzamy wiecej za jednym zamachem, ale unit test to unit test
        stat1.addStatistic(Time.valueOf("00:00:04"));
        stat2.addStatistic(Time.valueOf("00:00:05"));

        stat1.addStatistic(Time.valueOf("00:01:00"));
        stat2.addStatistic(Time.valueOf("00:00:59"));

        stat1.addStatistic(Time.valueOf("01:00:00"));
        stat2.addStatistic(Time.valueOf("01:00:00"));

        stat1.addStatistic(Time.valueOf("00:00:00"));
        stat2.addStatistic(Time.valueOf("00:01:00"));

        //when
        stat1.evaluateStatisticsWithWinLossDescriptions(stat2);

        //then
        //TODO: Czy nikt sie nie bedzie czepial ze jest kilka asercji w jednym tescie?
        assertThat(stat1.getStatistic(0).getDescription()).isEqualTo("win");
        assertThat(stat1.getStatistic(1).getDescription()).isEqualTo("loss");
        assertThat(stat1.getStatistic(2).getDescription()).isEqualTo("draw");
        assertThat(stat1.getStatistic(3).getDescription()).isEqualTo("loss");
    }

    @Test
    public void givenResultsBusinessModelWhenMappingToViewThenCorrectViewModelReturned(){
        //given
        ResultsForRunnersGroup group = new ResultsForRunnersGroup();
        ResultsForRunner runner1 = new ResultsForRunner();
        runner1.setCompetitorId(25L);
        runner1.setCompetitorName("Janusz Nosacz");
        runner1.setCompetitorCity("Wrocław");
        runner1.setPosition(2);
        runner1.setTotalResultId(34L);
        runner1.setTotalTime(Time.valueOf("00:01:20"));
        runner1.setDelayTime(Time.valueOf("00:00:40"));
        Result result1 = new Result();
        Result result2 = new Result();
        result1.setTime(Time.valueOf("00:00:20"));
        result1.setPosition(1);
        result2.setTime(Time.valueOf("00:01:00"));
        result2.setPosition(2);
        runner1.addSegmentResult(result1);
        runner1.addSegmentResult(result2);
        Result result3 = new Result();
        Result result4 = new Result();
        result3.setTime(Time.valueOf("00:00:20"));
        result3.setPosition(2);
        result4.setTime(Time.valueOf("00:01:20"));
        result4.setPosition(2);
        runner1.addCumulativeResult(result3);
        runner1.addCumulativeResult(result4);
        group.add(runner1);

        ResultsForRunner runner2 = new ResultsForRunner();
        runner2.setCompetitorId(28L);
        runner2.setCompetitorName("Grażyna Sundajska");
        runner2.setCompetitorCity("Mongolia");
        runner2.setPosition(-1);
        runner2.setTotalResultId(36L);
        runner2.setTotalTime(Time.valueOf("00:00:00"));
        runner2.setDelayTime(Time.valueOf("00:00:00"));
        Result result5 = new Result();
        Result result6 = new Result();
        result5.setTime(Time.valueOf("00:00:40"));
        result5.setPosition(3);
        result6.setTime(Time.valueOf("00:00:00"));
        result6.setPosition(-1);
        runner2.addSegmentResult(result5);
        runner2.addSegmentResult(result6);
        Result result7 = new Result();
        Result result8 = new Result();
        result7.setTime(Time.valueOf("00:00:40"));
        result7.setPosition(3);
        result8.setTime(Time.valueOf("00:00:00"));
        result8.setPosition(-1);
        runner2.addCumulativeResult(result7);
        runner2.addCumulativeResult(result8);
        group.add(runner2);
        //when
        RowResultsGroupView result = new ResultsMatrixFromBusinessToViewMapper().doMapping(group);
        //then
        RowResultView firstRunnerRow = result.getRowsView().get(0);
        RowResultView secondRunnerRow = result.getRowsView().get(1);
        assertThat(firstRunnerRow.getCompetitorName()).isEqualTo("Janusz Nosacz");
        assertThat(firstRunnerRow.getCompetitorCity()).isEqualTo("Wrocław");
        assertThat(firstRunnerRow.getCompetitorId()).isEqualTo(25L);
        assertThat(firstRunnerRow.getTotalTime()).isEqualTo("01:20");
        assertThat(firstRunnerRow.getDelayTime()).isEqualTo("+00:40");
        assertThat(firstRunnerRow.getPosition()).isEqualTo("2");
        assertThat(firstRunnerRow.getTotalResultId()).isEqualTo(34L);
        assertThat(firstRunnerRow.getSegmentResults().get(0).time).isEqualTo("00:20");
        assertThat(firstRunnerRow.getSegmentResults().get(0).position).isEqualTo("1");
        assertThat(firstRunnerRow.getSegmentResults().get(1).time).isEqualTo("01:00");
        assertThat(firstRunnerRow.getSegmentResults().get(1).position).isEqualTo("2");
        assertThat(firstRunnerRow.getCumulativeResults().get(0).time).isEqualTo("00:20");
        assertThat(firstRunnerRow.getCumulativeResults().get(0).position).isEqualTo("2");
        assertThat(firstRunnerRow.getCumulativeResults().get(1).time).isEqualTo("01:20");
        assertThat(firstRunnerRow.getCumulativeResults().get(1).position).isEqualTo("2");

        assertThat(secondRunnerRow.getCompetitorName()).isEqualTo("Grażyna Sundajska");
        assertThat(secondRunnerRow.getCompetitorCity()).isEqualTo("Mongolia");
        assertThat(secondRunnerRow.getCompetitorId()).isEqualTo(28L);
        assertThat(secondRunnerRow.getTotalTime()).isEqualTo("");
        assertThat(secondRunnerRow.getDelayTime()).isEqualTo("");
        assertThat(secondRunnerRow.getPosition()).isEqualTo("x");
        assertThat(secondRunnerRow.getTotalResultId()).isEqualTo(36L);
        assertThat(secondRunnerRow.getSegmentResults().get(0).time).isEqualTo("00:40");
        assertThat(secondRunnerRow.getSegmentResults().get(0).position).isEqualTo("3");
        assertThat(secondRunnerRow.getSegmentResults().get(1).time).isEqualTo("");
        assertThat(secondRunnerRow.getSegmentResults().get(1).position).isEqualTo("x");
        assertThat(secondRunnerRow.getCumulativeResults().get(0).time).isEqualTo("00:40");
        assertThat(secondRunnerRow.getCumulativeResults().get(0).position).isEqualTo("3");
        assertThat(secondRunnerRow.getCumulativeResults().get(1).time).isEqualTo("");
        assertThat(secondRunnerRow.getCumulativeResults().get(1).position).isEqualTo("x");
    }

    @Test
    public void givenStatisticsBusinessModelWhenMappingToViewThenCorrectViewModelReturned() {

        SegmentsStatistic row = new SegmentsStatistic();
        row.addStatistic(Time.valueOf("00:00:03"),"win");
        row.addStatistic(Time.valueOf("01:00:00"),"loss");
        row.addStatistic(Time.valueOf("00:00:00"),"draw");
        row.setTitle("Example title");
        SegmentsStatisticsGroup group = new SegmentsStatisticsGroup();
        group.add(row);

        AnalysisResultsGroupView analyseView = new StatisticsMatrixFromBusinessToViewMapper().doMapping(group);

        //TODO: to get(0).getSegments.get(0) chyba wyglada okropnie?
        assertThat(analyseView.getAnalyses().get(0).getSegmentResults().get(0).getValue()).isEqualTo("00:03");
        assertThat(analyseView.getAnalyses().get(0).getSegmentResults().get(0).getValueAttribute()).isEqualTo("win");
        assertThat(analyseView.getAnalyses().get(0).getSegmentResults().get(1).getValue()).isEqualTo("01:00:00");
        assertThat(analyseView.getAnalyses().get(0).getSegmentResults().get(1).getValueAttribute()).isEqualTo("loss");
        assertThat(analyseView.getAnalyses().get(0).getSegmentResults().get(2).getValue()).isEqualTo("");
        assertThat(analyseView.getAnalyses().get(0).getSegmentResults().get(2).getValueAttribute()).isEqualTo("draw");
        assertThat(analyseView.getAnalyses().get(0).getTitle()).isEqualTo("Example title");

    }
}
