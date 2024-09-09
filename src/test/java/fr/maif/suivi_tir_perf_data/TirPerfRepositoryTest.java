package fr.maif.suivi_tir_perf_data;

import fr.maif.suivi_tir_perf_data.Models.TirPerf;
import fr.maif.suivi_tir_perf_data.repositories.TirPerfRepository;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class TirPerfRepositoryTest {

    @Autowired
    TirPerfRepository tirPerfRepository;

    @AfterEach
    public void cleanUp() {
        tirPerfRepository.deleteAll();
    }

     @Test
    public void testCreateTirPerf() {
        // Given
        TirPerf tirPerf = TirPerf.builder()
                .date(LocalDate.of(1997, 12, 11))
                .build();

        // When
       TirPerf savedTirPerf = tirPerfRepository.save(tirPerf);

        // Then
        assertEquals(savedTirPerf, tirPerf);
    }

    @Test
    public void testUpdateTirPerf() {
        // Given
        TirPerf tirPerf = TirPerf.builder()
                .date(LocalDate.of(2024, 9, 3))
                .build();
        TirPerf savedTirPerf = tirPerfRepository.save(tirPerf);

        // When
        savedTirPerf.setDate(LocalDate.of(1999, 9, 9));
        TirPerf updatedTirPerf = tirPerfRepository.save(savedTirPerf);

        // Then
        assertEquals(savedTirPerf, updatedTirPerf);
    }

    @Test
    public void testDeleteTirPerf() {
        // Given
        TirPerf tirPerf = TirPerf.builder()
                        .date(LocalDate.of(2024, 9, 3))
                                .build();

        tirPerfRepository.save(tirPerf);

        // When
        tirPerfRepository.delete(tirPerf);
        TirPerf deletedTirPerf = tirPerfRepository.getTirPerfById(tirPerf.getId());
        // Then
        assertNull(deletedTirPerf);
    }

    @Test
    public void testGetAllTirPerfs() {

        // Given
        TirPerf tirPerf1 = new TirPerf();
        tirPerf1.setDate(LocalDate.of(2024, 9, 3));
        TirPerf tirPerf2 = new TirPerf();
        tirPerf2.setDate(LocalDate.of(1999, 9, 9));

        tirPerfRepository.save(tirPerf1);
        tirPerfRepository.save(tirPerf2);

        List<TirPerf> tirperfsExpected = List.of(tirPerf1, tirPerf2);

        // When
        List<TirPerf> tirPerfsSaved = tirPerfRepository.findAll();

        // Then
        tirPerfsSaved.forEach(tirPerf -> assertTrue(tirperfsExpected.contains(tirPerf)));
    }
}
