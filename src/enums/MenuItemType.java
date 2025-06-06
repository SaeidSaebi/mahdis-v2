package enums;

public enum MenuItemType {
    APPETIZER("پیش غذا"),
    MAIN_COURSE("غذای اصلی"),
    DESSERT("دسر"),
    BEVERAGE("نوشیدنی");

    private final String persianName;

    MenuItemType(String persianName) {
        this.persianName = persianName;
    }

    public String getPersianName() {
        return persianName;
    }

    @Override
    public String toString() {
        return persianName;
    }
} 