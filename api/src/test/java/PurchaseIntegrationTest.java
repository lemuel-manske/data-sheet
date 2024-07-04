import application.Application;
import application.purchase.PurchaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import utils.PurchaseControllerDouble;
import utils.PurchaseFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource(locations = { "classpath:application-integrationtest.properties" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
class PurchaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private PurchaseControllerDouble purchaseController;
    private PurchaseFactory factory;

    @BeforeEach
    void setUp() {
        purchaseController = new PurchaseControllerDouble(mockMvc);
        factory = new PurchaseFactory();
    }

    @Test
    void whenPurchaseIsCreated_ThenGetIt() throws Exception {
        PurchaseDto actualCreatedPurchase =
                purchaseController.createPurchase(factory.bananaPurchase());

        PurchaseDto foundCreatedPurchase =
                purchaseController.getPurchaseById(actualCreatedPurchase.getId());

        assertThat(actualCreatedPurchase).isEqualTo(foundCreatedPurchase);
    }

    @Test
    void givenPurchase_WhenDeleted_ThenCannotGetIt() throws Exception {
        PurchaseDto createdPurchase =
                purchaseController.createPurchase(factory.bananaPurchase());

        String createdPurchaseId = createdPurchase.getId();

        purchaseController.deletePurchase(createdPurchaseId)
                .andExpect(status().isNoContent());

        purchaseController.getById(createdPurchaseId)
                .andExpect(status().isNotFound());
    }

}