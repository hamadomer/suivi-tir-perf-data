package fr.maif.suivi_tir_perf_data;


import fr.maif.suivi_tir_perf_data.Models.Scenario;
import fr.maif.suivi_tir_perf_data.repositories.ScenarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ScenarioRepositoryTest {

    @Autowired
    ScenarioRepository scenarioRepository;

    @AfterEach
    public void cleanUp () {
        scenarioRepository.deleteAll();
    }


     @Test
    public void testCreateScenario() {
        // Given
        Scenario scenario = new Scenario();
        scenario.setDescription("Description");

        // When
        Scenario savedScenario = scenarioRepository.save(scenario);

        // Then
        assertEquals(scenario, savedScenario);
    }

    @Test
    public void testUpdateScenario() {
        // Given
        Scenario scenario = new Scenario();
        scenario.setDescription("Description");
        Scenario savedScenario = scenarioRepository.save(scenario);

        // When
       savedScenario.setDescription("Description updated");
       Scenario updatedScenario = scenarioRepository.save(savedScenario);

       // Then
        assertEquals(savedScenario, updatedScenario);
    }

    @Test
    public void testDeleteScenario() {
        // Given
        Scenario scenario = new Scenario();
        scenario.setDescription("Description");
        scenarioRepository.save(scenario);

        // When
        scenarioRepository.delete(scenario);
        Scenario deletedScenario = scenarioRepository.getScenarioById(scenario.getId());

        // Then
        assertNull(deletedScenario);
    }

    @Test
    public void testGetAllScenarios() {

        // Given
        Scenario scenario1 = new Scenario();
        scenario1.setDescription("Description");
        Scenario scenario2 = new Scenario();
        scenario2.setDescription("Description2");

        scenarioRepository.save(scenario1);
        scenarioRepository.save(scenario2);

        List<Scenario> scenariosExpected = List.of(scenario1, scenario2);

        // When
        List<Scenario> scenariosSaved = scenarioRepository.findAll();

        // Then

        scenariosSaved.forEach(scenario -> {
            assertTrue(scenariosExpected.contains(scenario));
        });

    }
}
