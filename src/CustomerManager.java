import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers = new ArrayList<>();
    private final String CUSTOMER_FILE = "customers.json";

    public CustomerManager() {
        loadCustomersFromFile();
    }

    public void addCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        customers.add(customer);
        saveCustomersToFile();
        System.out.println("مشتری اضافه شد.");
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

    public void updateCustomer(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equalsIgnoreCase(customer.getEmail())) {
                customers.set(i, customer);
                saveCustomersToFile();
                return;
            }
        }
    }

    private void loadCustomersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // For now, we'll just create an empty list
                customers = new ArrayList<>();
            }
        } catch (IOException e) {
            customers = new ArrayList<>();
        }
    }

    private void saveCustomersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("خطا در ذخیره مشتریان: " + e.getMessage());
        }
    }
}
