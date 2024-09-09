package fr.maif.suivi_tir_perf_data.repositories;

import fr.maif.suivi_tir_perf_data.Models.PanSI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanSIRepository extends JpaRepository<PanSI, Integer> {
}
