package dev.placeflow.api;

import static dev.placeflow.api.ApiDtos.*;
import dev.placeflow.model.*;
import dev.placeflow.repository.*;
import jakarta.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController @RequestMapping("/api/applications")
public class ApplicationController {
 private final ApplicationRepository apps;private final UserRepository users;private final JobRepository jobs;
 public ApplicationController(ApplicationRepository a,UserRepository u,JobRepository j){apps=a;users=u;jobs=j;}
 @GetMapping @PreAuthorize("hasRole('STUDENT')") List<ApplicationView> mine(Principal p){return apps.findByStudentIdOrderByAppliedAtDesc(user(p).getId()).stream().map(ApplicationView::of).toList();}
 @PostMapping("/jobs/{jobId}") @PreAuthorize("hasRole('STUDENT')") @ResponseStatus(HttpStatus.CREATED) ApplicationView apply(@PathVariable Long jobId,@Valid @RequestBody ApplyRequest r,Principal p){var u=user(p);var j=jobs.findById(jobId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job not found"));if(!j.isActive()||(j.getDeadline()!=null&&j.getDeadline().isBefore(LocalDate.now())))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This job is closed");if(apps.existsByStudentIdAndJobId(u.getId(),jobId))throw new ResponseStatusException(HttpStatus.CONFLICT,"You already applied to this job");var a=new JobApplication();a.setStudent(u);a.setJob(j);a.setCoverLetter(r.coverLetter());return ApplicationView.of(apps.save(a));}
 @GetMapping("/admin") @PreAuthorize("hasRole('ADMIN')") List<ApplicationView> all(){return apps.findAllByOrderByAppliedAtDesc().stream().map(ApplicationView::of).toList();}
 @PatchMapping("/{id}/status") @PreAuthorize("hasRole('ADMIN')") ApplicationView status(@PathVariable Long id,@Valid @RequestBody StatusRequest r){var a=apps.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Application not found"));a.setStatus(r.status());return ApplicationView.of(apps.save(a));}
 private User user(Principal p){return users.findByEmailIgnoreCase(p.getName()).orElseThrow();}
}
