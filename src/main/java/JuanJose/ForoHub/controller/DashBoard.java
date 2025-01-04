package JuanJose.ForoHub.controller;

import JuanJose.ForoHub.dto.statistics.CountDTO;
import JuanJose.ForoHub.service.Statistics.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashBoard {

    private final StatisticsService statisticsService;

    public DashBoard (StatisticsService statisticsService){
        this.statisticsService=statisticsService;
    }

    @GetMapping("/overview")
    public ResponseEntity<CountDTO> countStatistics (){
        CountDTO statistics = statisticsService.coutEntities();
        return ResponseEntity.ok().body(statistics);
    }
}
