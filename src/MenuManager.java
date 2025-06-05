import enums.MenuItemType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    public void addItem(String name, int price, String category, int rank, boolean available) {
        menu.add(new MenuItem(name, price, category, rank, available));
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
            Gson gson = new Gson();
            menu = gson.fromJson(reader, new TypeToken<ArrayList<MenuItem>>(){}.getType());
            if (menu == null) menu = new ArrayList<>();
        } catch (IOException e) {
            menu = new ArrayList<>();
        }
    }

    private void saveMenuToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            Gson gson = new Gson();
            writer.write(gson.toJson(menu));
        } catch (IOException e) {
            System.out.println("خطا در ذخیره منو: " + e.getMessage());
        }
    }
}
