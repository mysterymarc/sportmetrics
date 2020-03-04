package pl.mm.sportmetrics.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompetitionsCollection {

    private List<Competition> competitions = new ArrayList<>();

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public boolean add(Competition competition) {
        return competitions.add(competition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionsCollection that = (CompetitionsCollection) o;
        return Objects.equals(competitions, that.competitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitions);
    }

    @Override
    public String toString() {
        return "CompetitionsCollection{" +
                "competitions=" + competitions +
                '}';
    }
}
