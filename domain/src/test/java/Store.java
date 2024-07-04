import application.pricing.Bank;
import application.pricing.Price;
import application.product.Product;

public class Store {

    public static Product banana() {
        return new Product("Banana", Bank.brl("1.99"));
    }

    public static Product banana(Price price) {
        return new Product("Banana", price);
    }

    public static Product rice() {
        return new Product("Rice", Bank.brl("6.99"));
    }

    public static Product apple() {
        return new Product("Apple", Bank.brl("7.99"));
    }

    public static Product apple(Price price) {
        return new Product("Apple", price);
    }

    public static Product beans() {
        return new Product("Beans", Bank.brl("7.99"));
    }
}
