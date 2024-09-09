package fr.maif.suivi_tir_perf_data.repositories;

import fr.maif.suivi_tir_perf_data.Models.ContextExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContextExecutionRepository extends JpaRepository<ContextExecution, Integer> {
    ContextExecution getContextExecutionById(int id);
}
