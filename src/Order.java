import java.util.*;

public class Order {
    private Customer customer;
    private List<MenuItem> items = new ArrayList<>();
    boolean isOnline;
    private String address;
    private double totalPrice;

    public Order() {
    }

    public Order(Customer customer, boolean isOnline, String address) {
        this.customer = customer;
        this.isOnline = isOnline;
        this.address = address;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getAddress() {
        return address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("سفارش ").append(isOnline ? "آنلاین" : "حضوری").append("\n");
        if (customer != null) {
            sb.append("مشتری: ").append(customer.getName()).append("\n");
        }
        sb.append("آیتم‌ها:\n");
        for (MenuItem item : items) {
            sb.append("- ").append(item.getName()).append(" (").append(item.getPrice()).append(" تومان)\n");
        }
        sb.append("مجموع: ").append(totalPrice).append(" تومان");
        if (isOnline && address != null) {
            sb.append("\nآدرس: ").append(address);
        }
        return sb.toString();
    }
}

