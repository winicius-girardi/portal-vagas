package br.com.portalvagas.repository;

import br.com.portalvagas.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("""
        SELECT j FROM Job j
        WHERE 
            (:searchField IS NULL OR 
             LOWER(j.title) LIKE LOWER(CONCAT('%', :searchField, '%')) OR
             LOWER(j.description) LIKE LOWER(CONCAT('%', :searchField, '%')) OR
             LOWER(j.company) LIKE LOWER(CONCAT('%', :searchField, '%')))
    """)
    Page<Job> searchJobs(@Param("searchField") String searchField, Pageable pageable);

}
