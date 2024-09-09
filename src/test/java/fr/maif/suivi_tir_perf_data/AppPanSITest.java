package fr.maif.suivi_tir_perf_data;

import fr.maif.suivi_tir_perf_data.Models.AppPanSI;
import fr.maif.suivi_tir_perf_data.Models.Applicatif;
import fr.maif.suivi_tir_perf_data.Models.PanSI;
import fr.maif.suivi_tir_perf_data.repositories.AppPanSIRepoistory;
import fr.maif.suivi_tir_perf_data.repositories.ApplicatifRepository;
import fr.maif.suivi_tir_perf_data.repositories.PanSIRepository;
import jakarta.persistence.RollbackException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AppPanSITest {

    @Autowired
    private AppPanSIRepoistory appPanSIRepoistory;



    @Autowired
    private PanSIRepository panSIRepository;

    @Autowired
    private ApplicatifRepository applicatifRepository;


//    @AfterEach
//    public void cleanUp() {
//        appPanSIRepoistory.deleteAll();
//    }


    @Test
    public void testCreateAppPanSIShouldPass() {
        // Given

        AppPanSI appPanSI = new AppPanSI();
        Applicatif applicatif = new Applicatif();
        Applicatif savedApplicatif = applicatifRepository.save(applicatif);

        PanSI panSI = new PanSI();
        PanSI savedPanSI = panSIRepository.save(panSI);
        String version = "test version";
        appPanSI.setApplicatif(savedApplicatif);
        appPanSI.setPanId(savedPanSI);
        appPanSI.setVersion(version);

        // When

        AppPanSI savedAppPanSI = appPanSIRepoistory.save(appPanSI);

        // Then

        assertNotNull(savedAppPanSI);
        assertEquals(savedAppPanSI.getApplicatif().getId(), savedApplicatif.getId());
    }

    @Test
    public void testCreateAppPanSIShouldFail() {

        // Given

        AppPanSI appPanSI = new AppPanSI();

        Applicatif applicatif = new Applicatif();
        applicatif.setName("test applicatif");
        Applicatif savedApplicatif = applicatifRepository.save(applicatif);

        PanSI panSI = new PanSI();
        PanSI savedPanSI = panSIRepository.save(panSI);

        String version = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.";


        appPanSI.setApplicatif(savedApplicatif);
        appPanSI.setPanId(savedPanSI);
        appPanSI.setVersion(version);


        // TODO : learn more about flush >> https://www.baeldung.com/spring-jpa-flush

        assertThrows( DataIntegrityViolationException.class, () -> appPanSIRepoistory.save(appPanSI) );





    }

}
