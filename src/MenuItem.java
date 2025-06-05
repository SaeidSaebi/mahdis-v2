import enums.MenuItemType;

public class MenuItem {
        String name;
        int price;
        String category;
        int rank;
        boolean available;

        public MenuItem(String name, int price, String category, int rank, boolean available) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.rank = rank;
            this.available = available;
        }

        @Override
        public String toString() {
            return name + " - " + price + " تومان (" + category + ")" +
                    ", رتبه: " + rank + ", موجود: " + (available ? "بله" : "خیر");
        }
    }