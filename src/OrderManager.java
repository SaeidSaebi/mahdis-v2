import java.util.Scanner;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OrderManager {
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

        saveOrder(order);
        System.out.println(order);
    }

    private void saveOrder(Order order) {
        try {
            Gson gson = new Gson();
            java.io.File file = new java.io.File("orders.json");
            java.util.List<Order> orders;
            if (file.exists()) {
                java.io.FileReader reader = new java.io.FileReader(file);
                orders = gson.fromJson(reader, new TypeToken<java.util.List<Order>>(){}.getType());
                reader.close();
                if (orders == null) orders = new java.util.ArrayList<>();
            } else {
                orders = new java.util.ArrayList<>();
            }
            orders.add(order);
            FileWriter writer = new FileWriter(file);
            gson.toJson(orders, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("خطا در ذخیره سفارش: " + e.getMessage());
        }
    }
}

