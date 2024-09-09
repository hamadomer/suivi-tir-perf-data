package fr.maif.suivi_tir_perf_data.repositories;


import fr.maif.suivi_tir_perf_data.Models.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Integer> {
    Fonction getFonctionById(int id);
    Fonction getFonctionByName(String name);
}
