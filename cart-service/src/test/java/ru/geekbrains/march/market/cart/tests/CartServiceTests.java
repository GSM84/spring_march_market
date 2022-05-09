package ru.geekbrains.march.market.cart.tests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.services.CartService;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@SpringBootTest(classes = CartService.class)
public class CartServiceTests {

    @Autowired
    private CartService cartService;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Test
    public void addProductToCartTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(4L);
        productDto.setPrice(BigDecimal.valueOf(500.00));
        productDto.setTitle("Cheese");
        productDto.setCategoryTitle("Food");

        Mockito.doReturn(productDto)
                .when(productServiceIntegration)
                .findById(4L);

        cartService.addToCart(4L);

        assertThat(cartService.getCurrentCart().getTotalPrice(), is(BigDecimal.valueOf(500.00)));

        cartService.clearCart();

    }

    @Test
    public void  clearCartTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(4L);
        productDto.setPrice(BigDecimal.valueOf(500.00));
        productDto.setTitle("Cheese");
        productDto.setCategoryTitle("Food");

        Mockito.doReturn(productDto)
                .when(productServiceIntegration)
                .findById(4L);

        cartService.addToCart(4L);

        assertThat(cartService.getCurrentCart().getTotalPrice(), is(BigDecimal.valueOf(500.00)));

        cartService.clearCart();

        assertThat(cartService.getCurrentCart().getTotalPrice(), is(BigDecimal.ZERO));
    }

    @Test
    public void  cartItemIncreaseTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(4L);
        productDto.setPrice(BigDecimal.valueOf(500.00));
        productDto.setTitle("Cheese");
        productDto.setCategoryTitle("Food");

        Mockito.doReturn(productDto)
                .when(productServiceIntegration)
                .findById(4L);

        cartService.addToCart(4L);

        cartService.getCurrentCart().increaseItemCount(4L);
        cartService.getCurrentCart().increaseItemCount(4L);
        cartService.getCurrentCart().increaseItemCount(4L);

        assertThat(cartService.getCurrentCart().getTotalPrice(), is(BigDecimal.valueOf(2000.00)));
        assertThat(cartService.getCurrentCart().getItems().get(0).getQuantity(), is(4));

        cartService.clearCart();

    }

    @Test
    public void  cartItemDecreaseTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(4L);
        productDto.setPrice(BigDecimal.valueOf(500.00));
        productDto.setTitle("Cheese");
        productDto.setCategoryTitle("Food");

        Mockito.doReturn(productDto)
                .when(productServiceIntegration)
                .findById(4L);

        cartService.addToCart(4L);

        cartService.getCurrentCart().increaseItemCount(4L);

        assertThat(cartService.getCurrentCart().getTotalPrice(), is(BigDecimal.valueOf(1000.00)));
        assertThat(cartService.getCurrentCart().getItems().get(0).getQuantity(), is(2));

        cartService.getCurrentCart().decreaseItemCount(4L);

        assertThat(cartService.getCurrentCart().getTotalPrice(), is(BigDecimal.valueOf(500.00)));
        assertThat(cartService.getCurrentCart().getItems().get(0).getQuantity(), is(1));

        cartService.getCurrentCart().decreaseItemCount(4L);

        assertThat(cartService.getCurrentCart().getTotalPrice(), is(BigDecimal.ZERO));

        cartService.clearCart();

    }
}
