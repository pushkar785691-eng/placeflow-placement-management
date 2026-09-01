package dev.placeflow.api;
import static dev.placeflow.api.ApiDtos.*;
import dev.placeflow.model.ApplicationStatus;
import dev.placeflow.repository.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/dashboard")
public class DashboardController{
 private final JobRepository jobs;private final ApplicationRepository apps;public DashboardController(JobRepository j,ApplicationRepository a){jobs=j;apps=a;}
 @GetMapping Dashboard get(){Map<ApplicationStatus,Long> map=new EnumMap<>(ApplicationStatus.class);for(var s:ApplicationStatus.values())map.put(s,apps.countByStatus(s));return new Dashboard(jobs.count(),jobs.countByActiveTrue(),apps.count(),map);}
}
