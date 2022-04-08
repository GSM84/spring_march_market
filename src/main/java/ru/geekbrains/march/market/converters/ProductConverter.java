package ru.geekbrains.march.market.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

    public List<ProductDto> entityListToDtoList(List<Product> productList){
        return productList.stream()
                .map(p -> new ProductDto(
                    p.getId(),
                    p.getTitle(),
                    p.getPrice(),
                    p.getCategory().getTitle()
            )).collect(Collectors.toList());
    }

}
