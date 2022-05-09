package ru.geekbrains.march.market.core.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartServiceIntegration orderServiceIntegration;

    @Test
    public void createNewOrderTest() throws Exception {
        CartItemDto cartItemDto1 = new CartItemDto();
        cartItemDto1.setProductId(4L);
        cartItemDto1.setProductTitle("Cheese");
        cartItemDto1.setQuantity(2);
        cartItemDto1.setPricePerProduct(BigDecimal.valueOf(500));
        cartItemDto1.setPrice(BigDecimal.valueOf(1000));

        CartItemDto cartItemDto2 = new CartItemDto();
        cartItemDto2.setProductId(2L);
        cartItemDto2.setProductTitle("Milk");
        cartItemDto2.setQuantity(3);
        cartItemDto2.setPricePerProduct(BigDecimal.valueOf(120.2));
        cartItemDto2.setPrice(BigDecimal.valueOf(360.6));

        ArrayList<CartItemDto> list = new ArrayList<>();
        list.add(cartItemDto1);
        list.add(cartItemDto2);

        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(BigDecimal.valueOf(1360.6));
        cartDto.setItems(Collections.unmodifiableList(list));

        Mockito.doReturn(cartDto)
                        .when(orderServiceIntegration)
                .getUserCart("bob");

        mockMvc
                .perform(
                        post("/api/v1/orders")
                                .header("username", "bob")
                                .content(new ObjectMapper().writeValueAsString(cartDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.value").value("1"));

    }
}
