package dev.placeflow.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;

@Entity @Table(name="jobs")
public class Job {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false) private String title;
  @Column(nullable=false) private String company;
  @Column(nullable=false) private String location;
  @Column(nullable=false, length=5000) private String description;
  @Column(nullable=false) private String employmentType;
  @Column(precision=12, scale=2) private BigDecimal salary;
  private Double minimumCgpa;
  private LocalDate deadline;
  @Column(nullable=false) private boolean active=true;
  @Column(nullable=false, updatable=false) private Instant createdAt=Instant.now();
  public Long getId(){return id;} public void setId(Long v){id=v;}
  public String getTitle(){return title;} public void setTitle(String v){title=v;}
  public String getCompany(){return company;} public void setCompany(String v){company=v;}
  public String getLocation(){return location;} public void setLocation(String v){location=v;}
  public String getDescription(){return description;} public void setDescription(String v){description=v;}
  public String getEmploymentType(){return employmentType;} public void setEmploymentType(String v){employmentType=v;}
  public BigDecimal getSalary(){return salary;} public void setSalary(BigDecimal v){salary=v;}
  public Double getMinimumCgpa(){return minimumCgpa;} public void setMinimumCgpa(Double v){minimumCgpa=v;}
  public LocalDate getDeadline(){return deadline;} public void setDeadline(LocalDate v){deadline=v;}
  public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
  public Instant getCreatedAt(){return createdAt;}
}
