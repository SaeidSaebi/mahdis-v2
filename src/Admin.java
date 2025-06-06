import enums.MenuItemType;
import java.util.Scanner;

public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void manageMenu(MenuManager menuManager, Scanner scanner) {
        System.out.println("مدیریت منو:");
        menuManager.displayMenu();
        System.out.print("نام غذای جدید: ");
        String name = scanner.nextLine();
        System.out.print("قیمت غذای جدید: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // پاک کردن خط جدید

        System.out.print("دسته‌بندی غذا (APPETIZER, MAIN_COURSE, DESSERT, BEVERAGE): ");
        String typeInput = scanner.nextLine().trim().toUpperCase();
        MenuItemType type = MenuItemType.valueOf(typeInput);

        System.out.print("توضیحات غذا: ");
        String description = scanner.nextLine();

        menuManager.addItem(name, price, type, description);
        System.out.println("غذا اضافه شد.");
    }

    public void viewReports() {
        // شبیه‌سازی گزارشات فروش
        System.out.println("نمایش گزارشات فروش (شبیه‌سازی):");
        System.out.println("فروش روزانه: 5,000,000 تومان");
        System.out.println("فروش هفتگی: 35,000,000 تومان");
        System.out.println("فروش ماهانه: 150,000,000 تومان");
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
