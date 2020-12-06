package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private long productKey;

    ItemByUnit(final Product product) {
        this.product = product;
        this.productKey = product.hashCode();
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    public DiscountScheme discount() {
        return DiscountSchemeFactory.getInstance().getDiscountScheme(product);
    }

    public long getProductKey() { return product.hashCode(); }
}
