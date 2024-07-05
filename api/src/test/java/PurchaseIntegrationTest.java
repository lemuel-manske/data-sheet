import application.Application;
import application.purchase.ProductOrderDto;
import application.purchase.PurchaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import utils.ProductOrderFactory;
import utils.PurchaseControllerDouble;
import utils.PurchaseFactory;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource(locations = { "classpath:application-integrationtest.properties" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
class PurchaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ProductOrderFactory productOrderFactory;
    private PurchaseFactory purchaseFactory;
    private PurchaseControllerDouble purchaseController;

    @BeforeEach
    void setUp() {
        productOrderFactory = new ProductOrderFactory();
        purchaseFactory = new PurchaseFactory();
        purchaseController = new PurchaseControllerDouble(mockMvc);
    }

    @Test
    void whenPurchaseIsCreated_ThenGetIt() throws Exception {
        PurchaseDto actualCreatedPurchase =
                purchaseController.createPurchase(purchaseFactory.bananaPurchase());

        PurchaseDto foundCreatedPurchase =
                purchaseController.getPurchaseById(actualCreatedPurchase.getId());

        assertThat(actualCreatedPurchase).isEqualTo(foundCreatedPurchase);
    }

    @Test
    void givenPurchase_WhenDeleted_ThenCannotGetIt() throws Exception {
        PurchaseDto createdPurchase =
                purchaseController.createPurchase(purchaseFactory.bananaPurchase());

        String createdPurchaseId = createdPurchase.getId();

        purchaseController.deletePurchase(createdPurchaseId)
                .andExpect(status().isNoContent());

        purchaseController.tryToGetPurchaseById(createdPurchaseId)
                .andExpect(status().isNotFound());
    }

    @Test
    void givenEmptyPurchase_WhenProductOrderIsAdded_ThenUpdatePurchaseProductOrdersList() throws Exception {
        PurchaseDto purchase = purchaseController.createPurchase(purchaseFactory.emptyPurchase());

        ProductOrderDto createdProductOrder = purchaseController
                .createProductOrder(purchase.getId(), productOrderFactory.bananaOrder());

        PurchaseDto foundPurchaseResponse = purchaseController.getPurchaseById(purchase.getId());
        assertThat(foundPurchaseResponse.getOrders().get(0)).isEqualTo(createdProductOrder);
    }

    @Test
    void givenPurchaseWithProductOrder_WhenProductOrderIsDeleted_ThenNoProductOrderLeftAtPurchase() throws Exception {
        PurchaseDto purchase = purchaseController.createPurchase(purchaseFactory.bananaPurchase());

        String bananaOrderId = purchase.getOrders().get(0).getId();

        purchaseController.deleteProductOrder(bananaOrderId)
                .andExpect(status().isNoContent());

        PurchaseDto purchaseAfterDeletion = purchaseController.getPurchaseById(purchase.getId());
        assertThat(purchaseAfterDeletion.getOrders()).isEmpty();
    }
}