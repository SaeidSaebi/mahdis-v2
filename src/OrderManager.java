import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class    OrderManager {
    private List<Order> orders = new ArrayList<>();
    private final String ORDER_FILE = "orders.txt";

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
            orders = new ArrayList<>();
            StringBuilder orderBuilder = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // Empty line indicates end of an order, process the accumulated order
                    if (orderBuilder.length() > 0) {
                        processOrderFromText(orderBuilder.toString());
                        orderBuilder = new StringBuilder();
                    }
                } else {
                    orderBuilder.append(line).append("\n");
                }
            }
            
            // Process the last order if there's no trailing empty line
            if (orderBuilder.length() > 0) {
                processOrderFromText(orderBuilder.toString());
            }
            
        } catch (IOException e) {
            orders = new ArrayList<>();
            System.out.println("خطا در بارگذاری سفارشات: " + e.getMessage());
        }
    }
    
    private void processOrderFromText(String orderText) {
        try {
            Order order = new Order();
            String[] lines = orderText.split("\n");
            
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("سفارش ")) {
                    order.isOnline = line.contains("آنلاین");
                } else if (line.startsWith("مجموع: ") && line.endsWith(" تومان")) {
                    // We can reconstruct the order, but for simplicity, we'll just create a basic order
                    // In a real application, you'd want to store more structured data
                }
            }
            
            orders.add(order);
        } catch (Exception e) {
            System.out.println("خطا در پردازش سفارش: " + e.getMessage());
        }
    }

    private void saveOrdersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE))) {
            for (int i = 0; i < orders.size(); i++) {
                writer.write(orders.get(i).toString());
                writer.newLine();
                
                // Add empty line between orders for better separation
                if (i < orders.size() - 1) {
                    writer.newLine();
                }
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

