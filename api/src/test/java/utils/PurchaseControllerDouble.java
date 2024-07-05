package utils;

import application.purchase.ProductOrderDto;
import application.purchase.PurchaseDto;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PurchaseControllerDouble {

    private final MockMvc mockMvc;

    public PurchaseControllerDouble(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions tryToGetPurchaseById(String purchaseId) throws Exception {
        MockHttpServletRequestBuilder getPurchaseRequest = get("/purchase/" + purchaseId);

        return mockMvc.perform(getPurchaseRequest);
    }

    public PurchaseDto getPurchaseById(String purchaseId) throws Exception {
        MockHttpServletResponse getPurchaseResponse = tryToGetPurchaseById(purchaseId)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        return RequestHelper.readAs(PurchaseDto.class, getPurchaseResponse.getContentAsString());
    }

    public PurchaseDto createPurchase(PurchaseDto purchase) throws Exception {
        String json = RequestHelper.writeAsString(purchase);

        MockHttpServletRequestBuilder postRequest = post("/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        MockHttpServletResponse postResponse = mockMvc.perform(postRequest)
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        return RequestHelper.readAs(PurchaseDto.class, postResponse.getContentAsString());
    }

    public ResultActions deletePurchase(String purchaseId) throws Exception {
        MockHttpServletRequestBuilder deleteRequest = delete("/purchase/" + purchaseId);

        return mockMvc.perform(deleteRequest);
    }

    public ProductOrderDto createProductOrder(String purchaseId, ProductOrderDto productOrder) throws Exception {
        String json = RequestHelper.writeAsString(productOrder);

        MockHttpServletRequestBuilder postRequest = post("/purchase/" + purchaseId + "/product-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        MockHttpServletResponse postResponse = mockMvc.perform(postRequest)
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        return RequestHelper.readAs(ProductOrderDto.class, postResponse.getContentAsString());
    }

    public ResultActions deleteProductOrder(String productOrderId) throws Exception {
        MockHttpServletRequestBuilder deleteRequest = delete("/purchase/product-order/" + productOrderId);

        return mockMvc.perform(deleteRequest);
    }

    public ProductOrderDto updateProductOrder(String productOrderId, ProductOrderDto productOrder) throws Exception {
        String json = RequestHelper.writeAsString(productOrder);

        MockHttpServletRequestBuilder putRequest = put("/purchase/product-order/" + productOrderId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        MockHttpServletResponse putResponse = mockMvc.perform(putRequest)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        return RequestHelper.readAs(ProductOrderDto.class, putResponse.getContentAsString());
    }
}
