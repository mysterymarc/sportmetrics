package pl.mm.sportmetrics.repository;

import org.springframework.stereotype.Service;
import pl.mm.sportmetrics.domain.model.Competition;
import pl.mm.sportmetrics.domain.model.CompetitionsCollection;
import pl.mm.sportmetrics.repository.dao.CompetitionDAO;
import pl.mm.sportmetrics.repository.entity.CompetitionEntity;
import pl.mm.sportmetrics.repository.entity.EventEntitiesSet;

@Service
public class CompetitionRepository {

    private final CompetitionDAO competitionDao;

    public CompetitionRepository(CompetitionDAO competitionDao) {
        this.competitionDao = competitionDao;
    }

    public Competition findCompetition(Long id){
        return mapToModel(competitionDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Repository doesn't return CompetitionEntity for competition_id=" + id)));
    }

    public CompetitionsCollection findAllCompetitions(){
        CompetitionsCollection competitions = new CompetitionsCollection();
        competitionDao.findAll().forEach(entity -> competitions.add(mapToModel(entity)));
        return competitions;
    }

    public void saveCompetition(EventEntitiesSet event){
        competitionDao.save(event.competition);
    }

    private Competition mapToModel(CompetitionEntity entity){
        return new Competition(entity.id,entity.name);
    }
}
