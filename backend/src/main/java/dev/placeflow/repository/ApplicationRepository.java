package dev.placeflow.repository;
import dev.placeflow.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ApplicationRepository extends JpaRepository<JobApplication,Long>{
 boolean existsByStudentIdAndJobId(Long studentId,Long jobId);
 List<JobApplication> findByStudentIdOrderByAppliedAtDesc(Long studentId);
 List<JobApplication> findAllByOrderByAppliedAtDesc();
 long countByStatus(ApplicationStatus status);
}
