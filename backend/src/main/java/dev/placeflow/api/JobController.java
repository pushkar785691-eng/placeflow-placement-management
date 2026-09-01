package dev.placeflow.api;

import static dev.placeflow.api.ApiDtos.*;
import dev.placeflow.model.Job;
import dev.placeflow.repository.JobRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController @RequestMapping("/api/jobs")
public class JobController {
 private final JobRepository jobs;public JobController(JobRepository j){jobs=j;}
 @GetMapping Page<Job> list(@RequestParam(defaultValue="") String search,@RequestParam(defaultValue="true") boolean activeOnly,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="12") int size){return jobs.search(search,activeOnly,PageRequest.of(page,Math.min(Math.max(size,1),50),Sort.by(Sort.Direction.DESC,"createdAt")));}
 @GetMapping("/{id}") Job one(@PathVariable Long id){return find(id);}
 @PostMapping @PreAuthorize("hasRole('ADMIN')") @ResponseStatus(HttpStatus.CREATED) Job create(@Valid @RequestBody JobRequest r){return jobs.save(apply(new Job(),r));}
 @PutMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") Job update(@PathVariable Long id,@Valid @RequestBody JobRequest r){return jobs.save(apply(find(id),r));}
 @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") @ResponseStatus(HttpStatus.NO_CONTENT) void delete(@PathVariable Long id){jobs.delete(find(id));}
 private Job find(Long id){return jobs.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job not found"));}
 private Job apply(Job j,JobRequest r){j.setTitle(r.title());j.setCompany(r.company());j.setLocation(r.location());j.setDescription(r.description());j.setEmploymentType(r.employmentType());j.setSalary(r.salary());j.setMinimumCgpa(r.minimumCgpa());j.setDeadline(r.deadline());if(r.active()!=null)j.setActive(r.active());return j;}
}
