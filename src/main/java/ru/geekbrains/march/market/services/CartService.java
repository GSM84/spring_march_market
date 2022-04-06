package ru.geekbrains.march.market.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.configs.ProductCart;
import ru.geekbrains.march.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;

    private final ProductCart productCart;

    public void addProductToCart(Long id) {
        Optional<Product> product = productRepository.findById(id);

        product.ifPresent(productCart::addToCart);
    }

    public List<Product> getCartProducts(){
        System.out.println("going to return cart products " + productCart.getProductList().size());
        return productCart.getProductList();
    }

}
