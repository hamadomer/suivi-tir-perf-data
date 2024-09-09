package fr.maif.suivi_tir_perf_data;


import fr.maif.suivi_tir_perf_data.Models.Fonction;
import fr.maif.suivi_tir_perf_data.repositories.FonctionRepository;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class FonctionRepositoryTest {

    @Autowired
    FonctionRepository fonctionRepository;


    @AfterEach
    public void cleanUp() {
        fonctionRepository.deleteAll();
    }


    @Test
    public void testCreateFonction() {
        // Given
        Fonction fonction = Fonction.builder()
                .name("Test Fonction")
                .build();

        // When
        Fonction fonctionDB = fonctionRepository.save(fonction);

        //
        assertEquals(fonctionDB, fonction);
    }

    @Test
    public void testUpdateFonction() {
        // Given
        Fonction fonction = Fonction.builder()
                .name("Test fonction")
                .build();
        fonction = fonctionRepository.save(fonction);

        // When
        fonction.setName("Updated Fonction");
        Fonction fonctionDB = fonctionRepository.save(fonction);

        // Then
        assertEquals(fonctionDB, fonction);
    }

    @Test
    public void testDeleteFonction() {
        // Given
        Fonction fonction = Fonction.builder()
                .name("Delete")
                .build();
        fonction = fonctionRepository.save(fonction);

        // When
        fonctionRepository.delete(fonction);
        Fonction deletedFonction = fonctionRepository.getFonctionById(fonction.getId());

        //
        assertNull(deletedFonction);
    }

    @Test
    public void testGetAllFonctions() {
        // Given
        Fonction fonction1 = Fonction.builder()
                .name("Test 1")
                .build();

        Fonction fonction2 = Fonction.builder()
                        .name("Test 2")
                                .build();


        fonctionRepository.save(fonction1);
        fonctionRepository.save(fonction2);

        List<Fonction> fonctionsExpected = List.of(fonction1, fonction2);

        // When
        List<Fonction> fonctionsDB = fonctionRepository.findAll();

        // Then

        fonctionsDB.forEach(fonction -> {
            assertTrue(fonctionsExpected.contains(fonction));
        });
    }

    @Test
    public void testGetFonctionByName () {

        // Given
        Fonction fonction = Fonction.builder()
                        .name("Test Fonction")
                                .build();

        fonctionRepository.save(fonction);

        // When
        Fonction savedFonction = fonctionRepository.getFonctionByName(fonction.getName());

        // Then
        assertNotNull(savedFonction);
        assertEquals(savedFonction.getName(), fonction.getName());
    }
}
