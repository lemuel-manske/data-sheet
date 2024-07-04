import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.net.URI;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import application.Application;
import application.purchase.PurchaseDto;

@TestPropertySource(locations = { "classpath:application-integrationtest.properties" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
class PurchaseResourceIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    private static final String PURCHASE_ENDPOINT = "/purchase";

    @Test
    void whenPurchaseIsCreated_ThenGetIt() {
        PurchaseDto actualCreatedPurchase = doPostValidPurchase();

        PurchaseDto foundPurchase = doGetPurchase(actualCreatedPurchase.getId());

        assertThat(actualCreatedPurchase).isEqualTo(foundPurchase);
    }

    private PurchaseDto doGetPurchase(String purchaseId) {
        String endpoint = PURCHASE_ENDPOINT + "/" + purchaseId;

        return template.getForObject(endpoint, PurchaseDto.class);
    }

    private PurchaseDto doPostValidPurchase() {
        PurchaseRequest purchase = new PurchaseRequest();

        purchase.addOrder(
                "Banana",
                "2.99", "BRL",
                "1.50", "KILOGRAM");

        return template.postForObject(server(), purchase.getRequest(), PurchaseDto.class);
    }

    private URI server() {
        return URI.create(String.format("http://localhost:%d%s", port, PURCHASE_ENDPOINT));
    }

}