package kata.supermarket;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final BigDecimal pricePerUnit;
    private long productKey;

    public Product(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        this.productKey = UUID.randomUUID().hashCode();
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }
    long productKey() {
        return productKey;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
