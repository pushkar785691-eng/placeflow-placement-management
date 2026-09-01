package dev.placeflow.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String email;
  @Column(nullable=false) private String password;
  @Column(nullable=false) private String name;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role = Role.STUDENT;
  private String course;
  private Integer graduationYear;
  private String resumeUrl;
  @Column(nullable=false, updatable=false) private Instant createdAt = Instant.now();
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getEmail(){return email;} public void setEmail(String v){email=v;}
  public String getPassword(){return password;} public void setPassword(String v){password=v;}
  public String getName(){return name;} public void setName(String v){name=v;}
  public Role getRole(){return role;} public void setRole(Role v){role=v;}
  public String getCourse(){return course;} public void setCourse(String v){course=v;}
  public Integer getGraduationYear(){return graduationYear;} public void setGraduationYear(Integer v){graduationYear=v;}
  public String getResumeUrl(){return resumeUrl;} public void setResumeUrl(String v){resumeUrl=v;}
  public Instant getCreatedAt(){return createdAt;}
}
