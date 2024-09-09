package fr.maif.suivi_tir_perf_data.repositories;

import fr.maif.suivi_tir_perf_data.Models.RapportExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RapportExecutionRepository extends JpaRepository<RapportExecution, Integer> {
    RapportExecution getRapportExecutionById(int id);
}
