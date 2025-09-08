public class Customer {
    private String name;
    private String email;
    private boolean subscribed;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.subscribed = false;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void subscribe() {
        this.subscribed = true;
        System.out.println("اشتراک فعال شد.");
    }

    public void unsubscribe() {
        this.subscribed = false;
        System.out.println("اشتراک لغو شد.");
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - اشتراک: %s", name, email, (subscribed ? "فعال" : "غیرفعال"));
    }

    public String hasSubscription() {
        return "";
    }
}