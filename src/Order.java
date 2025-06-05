import java.util.*;

public class Order {
    public List<MenuItem> items = new ArrayList<>();
    public boolean isOnline;

    public void addItem(MenuItem item) {
        items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("سفارش ").append(isOnline ? "آنلاین" : "حضوری").append(":\n");
        for (MenuItem item : items) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}

