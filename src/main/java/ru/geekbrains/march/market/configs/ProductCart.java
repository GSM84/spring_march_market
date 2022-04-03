package ru.geekbrains.march.market.configs;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import ru.geekbrains.march.market.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
//@Scope("prototype")
public class ProductCart {

    private final List<Product> productList = new ArrayList<>();

    public void addToCart(Product _product){
        productList.add(_product);
    }
}
