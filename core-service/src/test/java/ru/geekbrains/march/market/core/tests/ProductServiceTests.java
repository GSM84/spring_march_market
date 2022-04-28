package ru.geekbrains.march.market.core.tests;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.entities.Category;
import ru.geekbrains.march.market.core.repositories.ProductRepository;
import ru.geekbrains.march.market.core.services.CategoryService;
import ru.geekbrains.march.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest(classes = ProductService.class) // указание на тестируемый бин
public class ProductServiceTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void createNewProductTest() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Еда");

        Mockito.doReturn(Optional.of(category))
                .when(categoryService)
                .findCategoryByTitle("Еда");// настройка поведения мокнутого объекта

        ProductDto productDto = new ProductDto(null, "Апельсин", BigDecimal.valueOf(10.33), "Еда");
        productService.createNewProduct(productDto);

        Mockito.verify(productRepository, Mockito.times(1)).save(ArgumentMatchers.any()); // проверка количества вызовов метода

    }
}
