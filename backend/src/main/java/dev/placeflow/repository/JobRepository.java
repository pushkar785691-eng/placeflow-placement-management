package dev.placeflow.repository;
import dev.placeflow.model.Job;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
public interface JobRepository extends JpaRepository<Job,Long>{
 @Query("select j from Job j where (:activeOnly=false or j.active=true) and (lower(j.title) like lower(concat('%',:q,'%')) or lower(j.company) like lower(concat('%',:q,'%')) or lower(j.location) like lower(concat('%',:q,'%')))")
 Page<Job> search(@Param("q") String q,@Param("activeOnly") boolean activeOnly, Pageable pageable);
 long countByActiveTrue();
}
