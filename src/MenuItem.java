import enums.MenuItemType;

public class MenuItem {
    private String name;
    private double price;
    private MenuItemType type;
    private String description;

    public MenuItem(String name, double price, MenuItemType type, String description) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public MenuItemType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s تومان\n%s", name, type, price, description);
    }
}