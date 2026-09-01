package dev.placeflow.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="applications", uniqueConstraints=@UniqueConstraint(columnNames={"student_id","job_id"}))
public class JobApplication {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false, fetch=FetchType.LAZY) @JoinColumn(name="student_id") private User student;
  @ManyToOne(optional=false, fetch=FetchType.LAZY) @JoinColumn(name="job_id") private Job job;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private ApplicationStatus status=ApplicationStatus.APPLIED;
  @Column(length=2000) private String coverLetter;
  @Column(nullable=false, updatable=false) private Instant appliedAt=Instant.now();
  private Instant updatedAt=Instant.now();
  @PreUpdate void touch(){updatedAt=Instant.now();}
  public Long getId(){return id;} public User getStudent(){return student;} public void setStudent(User v){student=v;}
  public Job getJob(){return job;} public void setJob(Job v){job=v;}
  public ApplicationStatus getStatus(){return status;} public void setStatus(ApplicationStatus v){status=v;}
  public String getCoverLetter(){return coverLetter;} public void setCoverLetter(String v){coverLetter=v;}
  public Instant getAppliedAt(){return appliedAt;} public Instant getUpdatedAt(){return updatedAt;}
}
