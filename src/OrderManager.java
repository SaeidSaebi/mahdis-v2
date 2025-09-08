import java.util.Scanner;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class    OrderManager {
    private List<Order> orders = new ArrayList<>();
    private final String ORDER_FILE = "orders.json";

    public OrderManager() {
        loadOrdersFromFile();
    }

    public void addOrder(Order order) {
        orders.add(order);
        saveOrdersToFile();
        System.out.println("سفارش ثبت شد.");
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("هیچ سفارشی ثبت نشده است.");
            return;
        }
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    public List<Order> getOrdersByCustomer(String email) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getEmail().equalsIgnoreCase(email)) {
                customerOrders.add(o);
            }
        }
        return customerOrders;
    }

    private void loadOrdersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // For now, we'll just create an empty list
                orders = new ArrayList<>();
            }
        } catch (IOException e) {
            orders = new ArrayList<>();
        }
    }

    private void saveOrdersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE))) {
            for (Order order : orders) {
                writer.write(order.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("خطا در ذخیره سفارشات: " + e.getMessage());
        }
    }

    public void handleOrder(MenuManager menuManager, boolean isOnline, Scanner scanner) {
        Order order = new Order();
        order.isOnline = isOnline;

        boolean ordering = true;
        while (ordering) {
            menuManager.displayMenu();
            System.out.print("شماره غذا را وارد کنید (0 برای پایان): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                ordering = false;
            } else if (choice > 0 && choice <= menuManager.getMenuSize()) {
                order.addItem(menuManager.getItem(choice - 1));
            } else {
                System.out.println("انتخاب نامعتبر است!");
            }
        }

        addOrder(order);
        System.out.println(order);
    }
}

