package kata.supermarket;

import java.math.BigDecimal;

public class DiscountByPercentage implements DiscountScheme {

    final float discountPct;
    final int quantity;

    DiscountByPercentage(int quantity, float discountPct) {
        this.quantity = quantity;
        this.discountPct = discountPct;
    }

    @Override
    public BigDecimal discount(Item item, Long purchasedQty) {
        if (discountPct < 0 || discountPct > 100 ) {
            throw new IllegalArgumentException("percentPromotion must be in [0,100]");
        }
        if (purchasedQty < quantity) {
            return BigDecimal.ZERO;
        }
        return item.price().multiply(new BigDecimal(discountPct));
    }
}
