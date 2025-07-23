import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.example.dto.GetCollectionDTO;
import org.example.dto.GetTotalResultDTO;
import org.example.enums.DonorTypeEnum;
import org.example.enums.StatusEnum;
import org.example.models.Donation;
import org.example.models.DonationAssignment;
import org.example.services.Logica;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogicaTest {
    private Logica logica;
    private Session session;

    private Donation donation;
    private Donation donation1;
    private DonationAssignment donationAssignment;
    private DonationAssignment donationAssignment1;

    @BeforeAll
    void setUp(){
        logica = Logica.getInstance();
        session = HibernateUtil.getSession();
        session.beginTransaction();

        donation = new Donation("Tomás", DonorTypeEnum.INDIVIDUAL, BigDecimal.valueOf(20000), LocalDate.of(2025, 12, 20), "Pro", StatusEnum.RECEIVED);
        donation1 = new Donation("Tomás", DonorTypeEnum.COMPANY, BigDecimal.valueOf(20000), LocalDate.of(2025, 12, 20), "Simple", StatusEnum.RECEIVED);
        donationAssignment = new DonationAssignment(donation, "Hola", LocalDate.of(2025, 12, 25));
        donationAssignment1 = new DonationAssignment(donation1, "Hola", LocalDate.of(2025, 12, 25));


        session.persist(donation);
        session.persist(donation1);
        session.persist(donationAssignment);
        session.persist(donationAssignment1);

        session.getTransaction().commit();
    }

    @AfterAll
    void tearDown(){
        if(session != null && session.isOpen()){
            session.beginTransaction();
            session.createQuery("delete from DonationAssignment").executeUpdate();
            session.createQuery("delete from Donation").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    @Test
    void testGetTotal(){
        List<GetTotalResultDTO> resultado = logica.getTotal();
        assertFalse(resultado.isEmpty());
        assertNotNull(resultado);
        assertEquals(resultado.size(), 2);
        for (int i = 0; i < resultado.size() - 1; i++){
            assertTrue(resultado.get(i).getTotalAmount().compareTo(
                    resultado.get(i + 1).getTotalAmount()) >= 0);
        }

    }
    @Test
    void testGetColection(){
        List<GetCollectionDTO> resultado = logica.getCollection();
        assertFalse(resultado.isEmpty());
        Assertions.assertNotNull(resultado);
        assertEquals(resultado.size(), 2);
        for (int i = 0; i < resultado.size() - 1; i++) {
            assertTrue(resultado.get(0).getTotalCollected().compareTo(
                    resultado.get(1).getTotalCollected()) >= 0);
        }
    }
}
