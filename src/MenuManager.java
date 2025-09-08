import enums.MenuItemType;
import java.io.*;
import java.util.ArrayList;

public class MenuManager {
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private final String MENU_FILE = "menu.txt";

    public MenuManager() {
        loadMenuFromFile();
    }

    public void displayMenu() {
        System.out.println("منوی رستوران:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }

    public void addItem(String name, double price, MenuItemType type, String description) {
        menu.add(new MenuItem(name, price, type, description));
        saveMenuToFile();
        System.out.println("آیتم جدید به منو اضافه شد.");
    }

    public MenuItem getItem(int index) {
        if (index >= 0 && index < menu.size()) {
            return menu.get(index);
        }
        return null;
    }

    public int getMenuSize() {
        return menu.size();
    }

    private void loadMenuFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            menu = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                // Parse the saved menu item format: name|type|price|description
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    try {
                        String name = parts[0].trim();
                        String typeStr = parts[1].trim();
                        double price = Double.parseDouble(parts[2].trim());
                        String description = parts[3].trim();
                        
                        MenuItemType type = MenuItemType.valueOf(typeStr);
                        menu.add(new MenuItem(name, price, type, description));
                    } catch (Exception e) {
                        System.out.println("خطا در بارگذاری آیتم منو: " + line);
                    }
                }
            }
        } catch (IOException e) {
            menu = new ArrayList<>();
            System.out.println("خطا در بارگذاری منو: " + e.getMessage());
        }
    }

    private void saveMenuToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (MenuItem item : menu) {
                // Save in a parseable format: name|type|price|description
                writer.write(item.getName() + "|" + item.getType().name() + "|" + 
                           item.getPrice() + "|" + item.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("خطا در ذخیره منو: " + e.getMessage());
        }
    }
}
