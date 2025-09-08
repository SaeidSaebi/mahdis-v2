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
            boolean inDataSection = false;
            
            while ((line = reader.readLine()) != null) {
                // Look for the data section
                if (line.contains("<!-- DATA START -->")) {
                    inDataSection = true;
                    continue;
                }
                
                if (line.contains("<!-- DATA END -->")) {
                    break;
                }
                
                // Parse data only in the data section
                if (inDataSection && !line.trim().isEmpty()) {
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
            }
        } catch (IOException e) {
            menu = new ArrayList<>();
            System.out.println("خطا در بارگذاری منو: " + e.getMessage());
        }
    }

    private void saveMenuToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            // Create beautiful table header
            writer.write("═══════════════════════════════════════════════════════════════════════════════════════");
            writer.newLine();
            writer.write("                                    🍽️  منوی رستوران  🍽️");
            writer.newLine();
            writer.write("═══════════════════════════════════════════════════════════════════════════════════════");
            writer.newLine();
            writer.write("┌─────┬──────────────────────────┬────────────────┬──────────────┬────────────────────────┐");
            writer.newLine();
            writer.write("│ ردیف │           نام غذا          │    دسته‌بندی     │  قیمت (تومان)  │        توضیحات          │");
            writer.newLine();
            writer.write("├─────┼──────────────────────────┼────────────────┼──────────────┼────────────────────────┤");
            writer.newLine();
            
            // Add menu items in table format
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.get(i);
                String row = String.format("│ %3d │ %-24s │ %-14s │ %12.0f │ %-22s │",
                    (i + 1),
                    truncateString(item.getName(), 24),
                    truncateString(item.getType().toString(), 14),
                    item.getPrice(),
                    truncateString(item.getDescription(), 22)
                );
                writer.write(row);
                writer.newLine();
            }
            
            // Close table
            writer.write("└─────┴──────────────────────────┴────────────────┴──────────────┴────────────────────────┘");
            writer.newLine();
            writer.write("═══════════════════════════════════════════════════════════════════════════════════════");
            writer.newLine();
            
            // Add parseable data at the end for loading (hidden from view)
            writer.write("\n<!-- DATA START -->");
            writer.newLine();
            for (MenuItem item : menu) {
                writer.write(item.getName() + "|" + item.getType().name() + "|" + 
                           item.getPrice() + "|" + item.getDescription());
                writer.newLine();
            }
            writer.write("<!-- DATA END -->");
            writer.newLine();
            
        } catch (IOException e) {
            System.out.println("خطا در ذخیره منو: " + e.getMessage());
        }
    }
    
    private String truncateString(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
}
