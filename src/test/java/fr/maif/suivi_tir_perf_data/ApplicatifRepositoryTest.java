package fr.maif.suivi_tir_perf_data;

import fr.maif.suivi_tir_perf_data.Models.Applicatif;
import fr.maif.suivi_tir_perf_data.Models.Scenario;
import fr.maif.suivi_tir_perf_data.Models.TirPerf;
import fr.maif.suivi_tir_perf_data.repositories.ApplicatifRepository;
import static org.junit.jupiter.api.Assertions.*;

import fr.maif.suivi_tir_perf_data.repositories.ScenarioRepository;
import fr.maif.suivi_tir_perf_data.repositories.TirPerfRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ApplicatifRepositoryTest {

    @Autowired
    private ApplicatifRepository applicatifRepository;

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Autowired
    private TirPerfRepository tirPerfRepository;

    @AfterEach void cleanUp () {
        applicatifRepository.deleteAll();
        scenarioRepository.deleteAll();
        tirPerfRepository.deleteAll();
    }

    @Test
    public void testCreateApplicatif() {
        // Given
        Applicatif applicatif = Applicatif.builder()
                .name("Test Applicatif")
                .build();

        // When
        Applicatif savedApplicatif =applicatifRepository.save(applicatif);

        // Then
        assertNotNull(savedApplicatif.getId());
    }

    @Test
    public void testUpdateApplicatif() {
        // Given
        Applicatif applicatif = Applicatif.builder()
                .name("Test Applicatif")
                .build();

        Applicatif savedApplicatif =applicatifRepository.save(applicatif);

        // When
        applicatif.setName("Updated Applicatif");
        Applicatif updatedApplicatif = applicatifRepository.save(applicatif);


    }

    @Test
    public void testGetAllApplicatif() {
        // Given

        Applicatif applicatif1 = Applicatif.builder().build();
        Applicatif applicatif2 = Applicatif.builder().build();

        Applicatif savedApplicatif1 =applicatifRepository.save(applicatif1);
        Applicatif savedApplicatif2 =applicatifRepository.save(applicatif2);

        List<Applicatif> localApplicatifs = List.of(savedApplicatif1, savedApplicatif2);


        // When
        List<Applicatif> dataBaseApplicatifs = applicatifRepository.findAll();

        // Then

        dataBaseApplicatifs.forEach(applicatif -> {
            assertTrue(localApplicatifs.contains(applicatif));
        });


    }

    @Test
    public void testFindApplicatifById() {
        // Given
        Applicatif applicatif = Applicatif.builder().build();
        Applicatif savedApplicatif =applicatifRepository.save(applicatif);

        // When
        Applicatif foundApplicatif =applicatifRepository.findById(savedApplicatif.getId()).orElse(null);

        // Then
        assertNotNull(foundApplicatif);
    }

    @Test
    public void testDeleteApplicatif() {
        // Given
        Applicatif applicatif = Applicatif.builder().build();
        Applicatif savedApplicatif =applicatifRepository.save(applicatif);

        // When
       applicatifRepository.delete(savedApplicatif);

       // Then
        assertFalse(applicatifRepository.existsById(savedApplicatif.getId()));
    }


   @Test
    public void testGetAllAssociatedTirPerfs() {
        // Given

        Applicatif applicatif1 = Applicatif.builder()
                .name("Test Applicatif1")
                .build();

         Applicatif applicatif2 = Applicatif.builder()
                .name("Test Applicatif2")
                .build();


        Applicatif savedApplicatif1 = applicatifRepository.save(applicatif1);
        Applicatif savedApplicatif2 = applicatifRepository.save(applicatif2);


        Scenario scenario1 = new Scenario();
        scenario1.setApplicatif(savedApplicatif1);
        Scenario scenario2 = new Scenario();
        scenario2.setApplicatif(savedApplicatif2);
        Scenario savedScenario1 = scenarioRepository.save(scenario1);
        Scenario savedScenario2 = scenarioRepository.save(scenario2);

        TirPerf tirPerf1 = new TirPerf();
        TirPerf tirPerf2 = new TirPerf();
        tirPerf1.setScenario(savedScenario1);
        tirPerf2.setScenario(savedScenario2);
        TirPerf savedTirPerf1 = tirPerfRepository.save(tirPerf1);
        TirPerf savedTirPerf2 = tirPerfRepository.save(tirPerf2);

        List<TirPerf> localTirPerf = List.of(tirPerf1, tirPerf2);


        // When
        List<TirPerf> tirPerfsSaved = applicatifRepository.getAllTirPerf(applicatif1.getId());


        // Then

        assertTrue(tirPerfsSaved.size() == 1);
        assertEquals(tirPerfsSaved.get(0).getScenario().getApplicatif(), applicatif1);
        assertNotEquals(tirPerfsSaved.get(0).getScenario().getApplicatif(), applicatif2);


        // case 2: where an applicatif has more than 1 tirPerf, same applicatif but different scenarios

        // Given

        Applicatif applicatif3 = Applicatif.builder()
                .name("Test applicatif3")
                .build();
        Applicatif savedApplicatif3 = applicatifRepository.save(applicatif3);

        Scenario scenario3 = new Scenario();
        scenario3.setApplicatif(savedApplicatif3);
        Scenario savedScenario3 = scenarioRepository.save(scenario3);

        Scenario scenario4 = new Scenario();
        scenario4.setApplicatif(savedApplicatif3);
        Scenario savedScenario4 = scenarioRepository.save(scenario4);

        TirPerf tirPerf3 = new TirPerf();
        tirPerf3.setScenario(savedScenario3);
        TirPerf savedTirPerf3 = tirPerfRepository.save(tirPerf3);

        TirPerf tirPerf4 = new TirPerf();
        tirPerf4.setScenario(savedScenario4);
        TirPerf savedTirPerf4 = tirPerfRepository.save(tirPerf4);

        List<TirPerf> ListOfTirPerfs = List.of(savedTirPerf3, savedTirPerf4);

        // When

        List<TirPerf> ListOfDBTirPerfs = applicatifRepository.getAllTirPerf(applicatif3.getId());

        // Then

        assertEquals(ListOfDBTirPerfs.size(), ListOfTirPerfs.size());
        ListOfDBTirPerfs.forEach(tirPerf -> {
            assertTrue(ListOfDBTirPerfs.contains(tirPerf));
        });

    }
}
