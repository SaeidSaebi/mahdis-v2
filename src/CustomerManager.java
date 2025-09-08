import java.io.*;
import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customers = new ArrayList<>();
    private final String CUSTOMER_FILE = "customers.txt";

    public CustomerManager() {
        loadCustomersFromFile(); // مشتری‌ها در شروع برنامه بارگذاری میشن
    }

    public void addCustomer(String name, String email) {
        customers.add(new Customer(name, email));
        saveCustomersToFile(); // ذخیره در فایل
        System.out.println("مشتری جدید اضافه شد.");
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("هیچ مشتری ثبت نشده است.");
            return;
        }
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    public Customer findCustomer(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    private void loadCustomersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String email = parts[1];
                    boolean subscribed = Boolean.parseBoolean(parts[2]);
                    Customer customer = new Customer(name, email);
                    if (subscribed) {
                        customer.subscribe();
                    }
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("خطا در بارگذاری مشتریان: " + e.getMessage());
        }
    }

    private void saveCustomersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer c : customers) {
                writer.write(c.getName() + "," + c.getEmail() + "," + c.hasSubscription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("خطا در ذخیره مشتریان: " + e.getMessage());
        }
    }

    public void updateCustomer(Customer customer) {
        // Find and update the customer in the list
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equalsIgnoreCase(customer.getEmail())) {
                customers.set(i, customer);
                saveCustomersToFile();
                break;
            }
        }
    }
}
