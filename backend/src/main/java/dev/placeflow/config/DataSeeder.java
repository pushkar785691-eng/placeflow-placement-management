package dev.placeflow.config;

import dev.placeflow.model.*;import dev.placeflow.repository.*;import java.math.BigDecimal;import java.time.LocalDate;import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.*;import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataSeeder{
 @Bean CommandLineRunner seed(UserRepository users,JobRepository jobs,PasswordEncoder encoder){return args->{
  if(!users.existsByEmailIgnoreCase("admin@placeflow.dev")){var u=new User();u.setName("Placement Admin");u.setEmail("admin@placeflow.dev");u.setPassword(encoder.encode("Admin@123"));u.setRole(Role.ADMIN);users.save(u);}
  if(!users.existsByEmailIgnoreCase("student@placeflow.dev")){var u=new User();u.setName("Demo Student");u.setEmail("student@placeflow.dev");u.setPassword(encoder.encode("Student@123"));u.setRole(Role.STUDENT);u.setCourse("Computer Science");u.setGraduationYear(2027);users.save(u);}
  if(jobs.count()==0){var j=new Job();j.setTitle("Graduate Software Engineer");j.setCompany("Nova Systems");j.setLocation("Bengaluru / Hybrid");j.setDescription("Build reliable customer-facing services with Java, React and PostgreSQL. Collaborate on design, testing and code reviews.");j.setEmploymentType("Full-time");j.setSalary(new BigDecimal("900000"));j.setMinimumCgpa(7.0);j.setDeadline(LocalDate.now().plusDays(30));jobs.save(j);}
 };}
}
