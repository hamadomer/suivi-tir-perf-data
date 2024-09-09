package fr.maif.suivi_tir_perf_data.repositories;

import fr.maif.suivi_tir_perf_data.Models.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {
    Scenario getScenarioById(int id);
}
