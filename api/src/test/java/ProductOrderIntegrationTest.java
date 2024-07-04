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
import utils.ProductOrderControllerDouble;
import utils.ProductOrderFactory;
import utils.PurchaseControllerDouble;
import utils.PurchaseFactory;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@TestPropertySource(locations = { "classpath:application-integrationtest.properties" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
class ProductOrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ProductOrderFactory productOrderFactory;
    private PurchaseFactory purchaseFactory;
    private ProductOrderControllerDouble productOrderControllerDouble;
    private PurchaseControllerDouble purchaseOrderControllerDouble;

    @BeforeEach
    void setUp() {
        productOrderFactory = new ProductOrderFactory();
        purchaseFactory = new PurchaseFactory();
        productOrderControllerDouble = new ProductOrderControllerDouble(mockMvc);
        purchaseOrderControllerDouble = new PurchaseControllerDouble(mockMvc);
    }
    
    @Test
    void givenEmptyPurchase_WhenProductOrderIsAdded_ThenUpdatePurchaseProductOrdersList() throws Exception {
        PurchaseDto purchase = purchaseOrderControllerDouble.createPurchase(purchaseFactory.emptyPurchase());

        ProductOrderDto createdProductOrder = productOrderControllerDouble
                .createProductOrder(purchase.getId(), productOrderFactory.bananaOrder());

        PurchaseDto foundPurchaseResponse = purchaseOrderControllerDouble.getPurchaseById(purchase.getId());

        assertThat(foundPurchaseResponse.getOrders().get(0)).isEqualTo(createdProductOrder);
    }
}
