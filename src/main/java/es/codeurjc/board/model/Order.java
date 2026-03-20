package es.codeurjc.board.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;  //order ID

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private User user;

    private double price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> items = new ArrayList<>();


    @Transient
    private boolean isProcessing;

    @Transient
    private boolean isShipped;

    @Transient
    private boolean isDelivered;

    @Transient
    private boolean isCancelled;

    public Order() {}

    public Order(User user, List<OrderItems> items) {
        this.user = user;
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
        this.items = items;
        for (OrderItems item : items) {
            item.setOrder(this);
        }
        this.price = calculateTotalPrice();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public List<OrderItems> getItems() { return items; }
    public void setItems(List<OrderItems> items) { this.items = items; }

    public void addItem(OrderItems item) {
        items.add(item);
        item.setOrder(this);
        this.price = calculateTotalPrice();
    }

    public void removeItem(OrderItems item) {
        items.remove(item);
        item.setOrder(null);
    }

    public boolean isProcessing() { return isProcessing; }
    public void setProcessing(boolean processing) { isProcessing = processing; }

    public boolean isShipped() { return isShipped; }
    public void setShipped(boolean shipped) { isShipped = shipped; }

    public boolean isDelivered() { return isDelivered; }
    public void setDelivered(boolean delivered) { isDelivered = delivered; }

    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean cancelled) { isCancelled = cancelled; }

    public String getFormattedDate() {
        if (orderDate == null) return "";
        return orderDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getFormattedPrice() {
        return String.format(Locale.US, "%.2f", price);
    }

    public double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(OrderItems::getSubtotal)
                .sum();
    }
}
