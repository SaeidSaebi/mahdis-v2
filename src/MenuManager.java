import enums.MenuItemType;
import java.io.*;
import java.util.ArrayList;

public class MenuManager {
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private final String MENU_FILE = "menu.json";

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
            // For now, we'll just create an empty menu
            menu = new ArrayList<>();
        } catch (IOException e) {
            menu = new ArrayList<>();
        }
    }

    private void saveMenuToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            // For now, we'll just save a simple format
            for (MenuItem item : menu) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("خطا در ذخیره منو: " + e.getMessage());
        }
    }
}
