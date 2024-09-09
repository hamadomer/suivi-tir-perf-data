package fr.maif.suivi_tir_perf_data.repositories;

import fr.maif.suivi_tir_perf_data.Models.TirPerf;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TirPerfRepository extends JpaRepository<TirPerf, Integer> {
    TirPerf getTirPerfById(int id);
}
