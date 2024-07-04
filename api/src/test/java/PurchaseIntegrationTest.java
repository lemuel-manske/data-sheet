import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import application.Application;
import application.purchase.PurchaseDto;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import utils.PurchaseFactory;
import utils.RequestHelper;

@AutoConfigureMockMvc
@TestPropertySource(locations = { "classpath:application-integrationtest.properties" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
class PurchaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private PurchaseFactory factory;

    @BeforeEach
    void setUp() {
        factory = new PurchaseFactory();
    }

    @Test
    void whenPurchaseIsCreated_ThenGetIt() throws Exception {
        MockHttpServletResponse postResponse = doPostPurchase(factory.bananaPurchase())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        PurchaseDto actualCreatedPurchase =
                RequestHelper.readAs(PurchaseDto.class, postResponse.getContentAsString());

        MockHttpServletResponse getResponse = doGetPurchase(actualCreatedPurchase.getId())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        PurchaseDto gotPurchase =
                RequestHelper.readAs(PurchaseDto.class, getResponse.getContentAsString());

        assertThat(actualCreatedPurchase).isEqualTo(gotPurchase);
    }

    @Test
    void givenPurchase_WhenDeleted_ThenCannotGetIt() throws Exception {
        MockHttpServletResponse postResponse = doPostPurchase(factory.bananaPurchase())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        String actualCreatedPurchaseId = RequestHelper
                .readAs(PurchaseDto.class, postResponse.getContentAsString())
                .getId();

        doDeletePurchase(actualCreatedPurchaseId)
                .andExpect(status().isNoContent());

        doGetPurchase(actualCreatedPurchaseId)
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeletedInvalidPurchase_ThenCannotDelete() throws Exception {
        String invalidPurchaseId = "12321";

        doDeletePurchase(invalidPurchaseId)
                .andExpect(status().isNotFound());
    }

    private ResultActions doGetPurchase(String purchaseId) throws Exception {
        MockHttpServletRequestBuilder getRequest = get("/purchase/" + purchaseId);

        return mockMvc.perform(getRequest);
    }

    private ResultActions doPostPurchase(PurchaseDto aPurchase) throws Exception {
        String json = RequestHelper.writeAsString(aPurchase);

        MockHttpServletRequestBuilder postRequest = post("/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        return mockMvc.perform(postRequest);
    }

    private ResultActions doDeletePurchase(String purchaseId) throws Exception {
        MockHttpServletRequestBuilder deleteRequest = delete("/purchase/" + purchaseId);

        return mockMvc.perform(deleteRequest);
    }
}