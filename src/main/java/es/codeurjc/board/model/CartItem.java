package es.codeurjc.board.model;

import java.io.Serializable;
import java.util.Locale;

public class CartItem implements Serializable {

    private long productId;
    private String productName;
    private double price;
    private int quantity;

    public CartItem() {}

    public CartItem(long productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    public long getProductId() { return productId; }
    public void setProductId(long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getFormattedPrice() {
        return String.format(Locale.US, "%.2f", price);
    }

    public String getFormattedSubtotal() {
        return String.format(Locale.US, "%.2f", getSubtotal());
    }
}