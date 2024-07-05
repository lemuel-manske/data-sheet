package utils;

import application.purchase.ProductOrderDto;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductOrderControllerDouble {

    private final MockMvc mockMvc;

    public ProductOrderControllerDouble(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions getById(String productOrderId) throws Exception {
        MockHttpServletRequestBuilder getPurchaseRequest = get("/purchase/product-order/" + productOrderId);

        return mockMvc.perform(getPurchaseRequest);
    }

    public List<ProductOrderDto> getProductOrderById(String productOrderId) throws Exception {
        MockHttpServletResponse getPurchaseResponse = getById(productOrderId)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        return RequestHelper.readAs(List.class, getPurchaseResponse.getContentAsString());
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
