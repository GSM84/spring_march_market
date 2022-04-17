package ru.geekbrains.march.market.utils;

import lombok.Data;
import ru.geekbrains.march.market.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Data
public class Cart {

    private BigDecimal totalPrice;
    private List<CartItem> items;

    public void add(Product p){
        Optional<CartItem> item = findItem(p.getId());
        if (item.isPresent()) {
            item.get().incrementQuantity();
            recalculate();
            return;
        }

        CartItem cartItem = new CartItem(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice());
        items.add(cartItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        items.forEach(i -> totalPrice = totalPrice.add(i.getPrice()));
    }

    private Optional<CartItem> findItem(Long productId){
        return items.stream().filter(i -> i.getProductId().equals(productId)).findFirst();
    }

    public void increaseItemCount(Long productId) {
        CartItem item = findItem(productId).get();
        item.incrementQuantity();
        recalculate();
    }

    public void decreaseItemCount(Long productId) {
        CartItem item = findItem(productId).get();
        item.decrementQuantity();
        if (item.getQuantity() == 0) {
            items.remove(item);
        }
        recalculate();
    }
}
