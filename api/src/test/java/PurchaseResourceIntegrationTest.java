import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import application.Application;
import application.purchase.PurchaseDto;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@AutoConfigureMockMvc
@TestPropertySource(locations = { "classpath:application-integrationtest.properties" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
class PurchaseResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private PurchaseFactory factory;

    @BeforeEach
    void setUp() {
        factory = new PurchaseFactory();
    }

    @Test
    void whenPurchaseIsCreated_ThenGetIt() throws Exception {
        MockHttpServletResponse postResponse = doPostValidPurchase(factory.validBananaPurchase())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        PurchaseDto actualCreatedPurchase =
                IntegrationTestHelper.readAs(PurchaseDto.class, postResponse.getContentAsString());

        MockHttpServletResponse getResponse = doGetPurchase(actualCreatedPurchase.getId())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        PurchaseDto gotPurchase =
                IntegrationTestHelper.readAs(PurchaseDto.class, getResponse.getContentAsString());

        assertThat(actualCreatedPurchase).isEqualTo(gotPurchase);
    }

    private ResultActions doGetPurchase(String purchaseId) throws Exception {
        MockHttpServletRequestBuilder getRequest = get("/purchase/" + purchaseId);

        return mockMvc.perform(getRequest);
    }

    private ResultActions doPostValidPurchase(PurchaseDto aPurchase) throws Exception {
        String json = IntegrationTestHelper.writeAsString(aPurchase);

        MockHttpServletRequestBuilder postRequest = post("/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        return mockMvc.perform(postRequest);
    }
}