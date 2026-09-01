package dev.placeflow.api;

import dev.placeflow.model.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.Map;

public final class ApiDtos {
 private ApiDtos(){}
 public record RegisterRequest(@NotBlank String name,@Email @NotBlank String email,@Size(min=8) String password,String course,Integer graduationYear,String resumeUrl){}
 public record LoginRequest(@Email @NotBlank String email,@NotBlank String password){}
 public record AuthResponse(String token,UserView user){}
 public record UserView(Long id,String name,String email,Role role,String course,Integer graduationYear,String resumeUrl){
  public static UserView of(User u){return new UserView(u.getId(),u.getName(),u.getEmail(),u.getRole(),u.getCourse(),u.getGraduationYear(),u.getResumeUrl());}
 }
 public record JobRequest(@NotBlank String title,@NotBlank String company,@NotBlank String location,@NotBlank @Size(max=5000) String description,@NotBlank String employmentType,@PositiveOrZero BigDecimal salary,@DecimalMin("0.0") @DecimalMax("10.0") Double minimumCgpa,LocalDate deadline,Boolean active){}
 public record ApplyRequest(@Size(max=2000) String coverLetter){}
 public record StatusRequest(@NotNull ApplicationStatus status){}
 public record ApplicationView(Long id,Long jobId,String jobTitle,String company,Long studentId,String studentName,String studentEmail,ApplicationStatus status,String coverLetter,Instant appliedAt,Instant updatedAt){
  public static ApplicationView of(JobApplication a){return new ApplicationView(a.getId(),a.getJob().getId(),a.getJob().getTitle(),a.getJob().getCompany(),a.getStudent().getId(),a.getStudent().getName(),a.getStudent().getEmail(),a.getStatus(),a.getCoverLetter(),a.getAppliedAt(),a.getUpdatedAt());}
 }
 public record Dashboard(long totalJobs,long activeJobs,long totalApplications,Map<ApplicationStatus,Long> byStatus){}
}
