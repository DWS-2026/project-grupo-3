package es.codeurjc.board.service;

import es.codeurjc.board.model.CartItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@SessionScope
public class CartService {

    private final List<CartItem> items = new ArrayList<>();


    public void addItem(long productId, String productName, double price, int quantity) {
        Optional<CartItem> existing = items.stream()
                .filter(i -> i.getProductId() == productId)
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + quantity);
        } else {
            items.add(new CartItem(productId, productName, price, quantity));
        }
    }


    public void removeItem(long productId) {
        items.removeIf(i -> i.getProductId() == productId);
    }

    public void clear() {
        items.clear();
    }


    public List<CartItem> getItems() {
        return items;
    }


    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public String getFormattedTotal() {
        return String.format(Locale.US, "%.2f", getTotal());
    }


    public boolean isEmpty() {
        return items.isEmpty();
    }
}