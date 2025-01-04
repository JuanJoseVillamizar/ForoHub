package JuanJose.ForoHub.service.Statistics;

import JuanJose.ForoHub.dto.statistics.CountDTO;
import JuanJose.ForoHub.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository){
        this.statisticsRepository=statisticsRepository;
    }

    public CountDTO coutEntities() {
        Long categoryCount = statisticsRepository.countCategories();
        Long subCategoryCount = statisticsRepository.countSubCategories();
        Long courseCount = statisticsRepository.countCourses();
        Long topicCount = statisticsRepository.countTopics();
        Long responseCount = statisticsRepository.countResponses();
        return  new CountDTO(categoryCount,subCategoryCount,courseCount,topicCount,responseCount);
    }
}
