package application.product;

import application.princing.PriceDto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        ProductDto that = (ProductDto) o;
        return id.equals(that.id)
                && name.equals(that.name)
                && price.equals(that.price);
    }
}
