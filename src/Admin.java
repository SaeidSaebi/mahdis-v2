# salam mahdis
import enums.MenuItemType;

import java.util.Scanner;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public void manageMenu(MenuManager menuManager, Scanner scanner) {
        System.out.println("مدیریت منو:");
        menuManager.displayMenu();
        System.out.print("نام غذای جدید: ");
        String name = scanner.nextLine();
        System.out.print("قیمت غذای جدید: ");
        int price = scanner.nextInt();
        scanner.nextLine(); // پاک کردن خط جدید

        System.out.print("دسته‌بندی غذا (مثلاً غذای اصلی، نوشیدنی، دسر): ");
        String category = scanner.nextLine();
        System.out.print("رتبه غذا (عدد): ");
        int rank = scanner.nextInt();
        scanner.nextLine();
        System.out.print("آیا غذا موجود است؟ (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        menuManager.addItem(name, price, category, rank, available);
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
    }
}
