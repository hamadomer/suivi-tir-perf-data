package fr.maif.suivi_tir_perf_data;


import fr.maif.suivi_tir_perf_data.Models.ContextExecution;
import fr.maif.suivi_tir_perf_data.repositories.ContextExecutionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class ContextExecutionRepositoryTest {

    @Autowired
    ContextExecutionRepository contextExecutionRepository;

    @AfterEach
    public void cleanUp () {
        contextExecutionRepository.deleteAll();
    }

     @Test
    public  void testCreateContextExecution() {
        // Given
      ContextExecution contextExecution = ContextExecution.builder()
                .environment("env")
                .complementaryInformation("info")
                .build();

        // When
        ContextExecution created = contextExecutionRepository.save(contextExecution);

        // Then
        assertEquals(created, contextExecution);

    }

    @Test
    public void testDeleteContextExecution() {
        // Given

        ContextExecution contextExecution = ContextExecution.builder()
                .environment("env")
                .complementaryInformation("info")
                .build();

        ContextExecution savedContextExecution = contextExecutionRepository.save(contextExecution);

        // When
        contextExecutionRepository.delete(contextExecution);
        ContextExecution deletedContext = contextExecutionRepository.getContextExecutionById(savedContextExecution.getId());


        // Then
        assertNull(deletedContext);
    }

    @Test
    public void testUpdateContextExecution() {
        // Given
        ContextExecution contextExecution = ContextExecution.builder()
                .environment("env")
                .complementaryInformation("info")
                .build();

        contextExecutionRepository.save(contextExecution);

        // When
        contextExecution.setComplementaryInformation("Updated info");
        ContextExecution updatedVersion = contextExecutionRepository.save(contextExecution);

        // Then
        assertEquals(updatedVersion, contextExecution);
    }

    @Test
    public void testGetAllContextExecutions() {
        // Given
        ContextExecution contextExecution1 = ContextExecution.builder()
                .environment("env")
                .complementaryInformation("info")
                .build();
        ContextExecution contextExecution2 = ContextExecution.builder()
                .environment("env")
                .complementaryInformation("info")
                .build();

        contextExecutionRepository.save(contextExecution1);
        contextExecutionRepository.save(contextExecution2);

        List<ContextExecution> ContextExecutionsExpected = List.of(contextExecution1, contextExecution2);
        // When
        List<ContextExecution> ContextExecutionsDB = contextExecutionRepository.findAll();

        // Then
        ContextExecutionsDB.forEach(contextExecution -> {
            assertTrue(ContextExecutionsExpected.contains(contextExecution));
        });
    }
}
