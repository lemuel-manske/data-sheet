import application.Application;
import application.purchase.ProductOrder;
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

import java.math.BigDecimal;

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

    @Test
    void givenPurchaseWithProductOrder_WhenProductOrderIsDeleted_ThenNoProductOrderLeftAtPurchase() throws Exception {
        PurchaseDto purchase = purchaseOrderControllerDouble.createPurchase(purchaseFactory.bananaPurchase());

        String bananaOrderId = purchase.getOrders().get(0).getId();
        productOrderControllerDouble.deleteProductOrder(bananaOrderId);

        PurchaseDto purchaseAfterDeletion = purchaseOrderControllerDouble.getPurchaseById(purchase.getId());
        assertThat(purchaseAfterDeletion.getOrders()).isEmpty();
    }

    @Test
    void whenProductOrderIsUpdated_ThenMergeIt() throws Exception {
        PurchaseDto purchase = purchaseOrderControllerDouble.createPurchase(purchaseFactory.bananaPurchase());

        ProductOrderDto bananaOrder = purchase.getOrders().get(0);

        bananaOrder.getProduct().getPrice().setValue(new BigDecimal("1.99"));
        bananaOrder.getAmount().setValue(new BigDecimal("2.00"));

        productOrderControllerDouble.updateProductOrder(bananaOrder.getId(), bananaOrder);

        ProductOrderDto bananaOrderAfterUpdate = purchaseOrderControllerDouble
                .getPurchaseById(purchase.getId())
                .getOrders().get(0);

        bananaOrder.setTotal(new BigDecimal("3.98"));

        assertThat(bananaOrder).isEqualTo(bananaOrderAfterUpdate);
    }
}
