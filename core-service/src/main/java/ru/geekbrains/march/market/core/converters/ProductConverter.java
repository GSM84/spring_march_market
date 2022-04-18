package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.entities.Product;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }
}
