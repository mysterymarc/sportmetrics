package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.List;

public class CompetitionsCollection{

    private List<Competition> competitions = new ArrayList<Competition>();

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public boolean add(Competition competition) {
        return competitions.add(competition);
    }


}
