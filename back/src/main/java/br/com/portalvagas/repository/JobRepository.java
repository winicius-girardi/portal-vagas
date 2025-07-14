package br.com.portalvagas.repository;

import br.com.portalvagas.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    Long countByTypeOfJob(String typeOfJob);

    @Query(value = """
        SELECT TO_CHAR(publish_date, 'YYYY-MM-DD') AS dia, COUNT(*) 
        FROM sch_portal_vagas.job 
        WHERE publish_date >= CURRENT_DATE - INTERVAL '30 days' 
        GROUP BY dia 
        ORDER BY dia
        """, nativeQuery = true)
    List<Object[]> countVagasPorDiaUltimos30Dias();
}
