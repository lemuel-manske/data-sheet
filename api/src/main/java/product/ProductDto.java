package product;

import princing.PriceDto;

public class ProductDto {

    private String id;
    private String name;
    private PriceDto price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceDto getPrice() {
        return price;
    }

    public void setPrice(PriceDto price) {
        this.price = price;
    }
}
