import java.util.Scanner;

public class RestaurantApp {
    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();
        OrderManager orderManager = new OrderManager();
        Admin admin = new Admin("admin", "1234");
        CustomerManager customerManager = new CustomerManager();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. ورود مدیریت");
            System.out.println("2. سفارش حضوری");
            System.out.println("3. فروش اینترنتی");
            System.out.println("4. مدیریت مشتریان");
            System.out.println("5. خروج");

            int choice = scanner.nextInt();
            scanner.nextLine(); // پاک کردن خط جدید

            switch (choice) {
                case 1:
                    handleAdminLogin(admin, menuManager, scanner);
                    break;
                case 2:
                    orderManager.handleOrder(menuManager, false, scanner);
                    break;
                case 3:
                    orderManager.handleOrder(menuManager, true, scanner);
                    break;
                case 4:
                    handleCustomerManagement(customerManager, scanner);
                    break;
                case 5:
                    System.out.println("خداحافظ!");
                    scanner.close();
                    return;
                default:
                    System.out.println("گزینه نامعتبر است!");
            }
        }
    }

    public static void handleAdminLogin(Admin admin, MenuManager menuManager, Scanner scanner) {
        System.out.print("نام کاربری: ");
        String username = scanner.nextLine();
        System.out.print("رمز عبور: ");
        String password = scanner.nextLine();

        if (admin.login(username, password)) {
            System.out.println("ورود موفقیت‌آمیز");
            System.out.println("1. مدیریت منو");
            System.out.println("2. نمایش گزارشات فروش");
            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // پاک کردن خط جدید

            if (adminChoice == 1) {
                admin.manageMenu(menuManager, scanner);
            } else if (adminChoice == 2) {
                admin.viewReports();
            } else {
                System.out.println("گزینه نامعتبر است!");
            }
        } else {
            System.out.println("نام کاربری یا رمز عبور اشتباه است.");
        }
    }

    public static void handleCustomerManagement(CustomerManager customerManager, Scanner scanner) {
        System.out.println("مدیریت مشتریان:");
        System.out.println("1. اضافه کردن مشتری");
        System.out.println("2. نمایش مشتریان");
        System.out.println("3. مدیریت اشتراک مشتری");

        System.out.print("لطفاً یک گزینه انتخاب کنید: ");
        int customerChoice = scanner.nextInt();
        scanner.nextLine(); // پاک کردن خط جدید

        switch (customerChoice) {
            case 1:
                System.out.print("نام مشتری: ");
                String name = scanner.nextLine();
                System.out.print("ایمیل مشتری: ");
                String email = scanner.nextLine();
                customerManager.addCustomer(name, email);
                break;
            case 2:
                customerManager.displayCustomers();
                break;
            case 3:
                System.out.print("ایمیل مشتری برای مدیریت: ");
                String customerEmail = scanner.nextLine();
                Customer customer = customerManager.findCustomer(customerEmail);
                if (customer != null) {
                    System.out.println("1. فعال‌سازی اشتراک");
                    System.out.println("2. لغو اشتراک");
                    System.out.print("لطفاً یک گزینه انتخاب کنید: ");
                    int subscriptionChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (subscriptionChoice == 1) {
                        customer.subscribe();
                        customerManager.updateCustomer(customer);
                    } else if (subscriptionChoice == 2) {
                        customer.unsubscribe();
                        customerManager.updateCustomer(customer);
                    } else {
                        System.out.println("گزینه نامعتبر است!");
                    }
                } else {
                    System.out.println("مشتری یافت نشد.");
                }
                break;
            default:
                System.out.println("گزینه نامعتبر است!");
        }
    }
}
