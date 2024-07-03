import application.pricing.Bank;
import application.pricing.Price;
import application.product.Product;

class Store {

    Product banana() {
        return new Product("Banana", Bank.brl("1.99"));
    }

    Product banana(Price price) {
        return new Product("Banana", price);
    }

    Product rice() {
        return new Product("Rice", Bank.brl("6.99"));
    }

    Product apple() {
        return new Product("Apple", Bank.brl("7.99"));
    }

    Product apple(Price price) {
        return new Product("Apple", price);
    }

    Product beans() {
        return new Product("Beans", Bank.brl("7.99"));
    }
}
