package fr.maif.suivi_tir_perf_data;


import fr.maif.suivi_tir_perf_data.Models.RapportExecution;
import fr.maif.suivi_tir_perf_data.repositories.RapportExecutionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RapportExecutionTest {

    @Autowired
    RapportExecutionRepository rapportExecutionRepository;

    @AfterEach
    public void cleanUp () {
        rapportExecutionRepository.deleteAll();
    }

    @Test
    public void testCreateRapportExecution() {
        // Given
        RapportExecution rapportExecution = new RapportExecution();
        rapportExecution.setSuccessRate(100);

        // When
        RapportExecution savedRapportExecution = rapportExecutionRepository.save(rapportExecution);

        // Then
        assertEquals(savedRapportExecution, rapportExecution);
    }

    @Test
    public void testUpdateRapportExecution() {
        // Given
        RapportExecution rapportExecution = new RapportExecution();
        rapportExecution.setSuccessRate(100);
        RapportExecution savedRapportExecution = rapportExecutionRepository.save(rapportExecution);

        // When
        savedRapportExecution.setDuration(90);
        RapportExecution updatedRapportExecution = rapportExecutionRepository.save(savedRapportExecution);

        // Then
        assertEquals(savedRapportExecution, rapportExecution);
    }

    @Test
    public void testDeleteRapportExecution() {
        // Given
        RapportExecution rapportExecution = new RapportExecution();
        rapportExecutionRepository.save(rapportExecution);

        // When
        rapportExecutionRepository.delete(rapportExecution);
        RapportExecution deletedRapportExecution = rapportExecutionRepository.getRapportExecutionById(rapportExecution.getId());

        // Then
        assertNull(deletedRapportExecution);
    }

    @Test
    public void testGetAllRapportExecutions() {

        // Given
        RapportExecution rapportExecution1 = new RapportExecution();
        RapportExecution rapportExecution2 = new RapportExecution();

        // TODO understand why JPA update all instance of objects even those who are in some data structures
        List<RapportExecution> rapportExecutionsExpected = List.of(rapportExecution1,rapportExecution2);

        rapportExecutionRepository.save(rapportExecution1);
        rapportExecutionRepository.save(rapportExecution2);


        // When
        List<RapportExecution> rapportExecutionsSaved = rapportExecutionRepository.findAll();

        // Then
        rapportExecutionsSaved.forEach(rapportExecution -> {
            assertTrue(rapportExecutionsExpected.contains(rapportExecution));
        });

    }
}
