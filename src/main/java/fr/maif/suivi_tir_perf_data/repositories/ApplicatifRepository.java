package fr.maif.suivi_tir_perf_data.repositories;

import fr.maif.suivi_tir_perf_data.Models.Applicatif;
import fr.maif.suivi_tir_perf_data.Models.TirPerf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApplicatifRepository extends JpaRepository<Applicatif, Integer> {

    @Query("SELECT t FROM TirPerf t JOIN t.scenario s JOIN s.applicatif a WHERE a.id = :id")
    List<TirPerf> getAllTirPerf(@Param("id") Integer id);
}