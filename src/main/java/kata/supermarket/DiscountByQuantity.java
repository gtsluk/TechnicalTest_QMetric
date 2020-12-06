package kata.supermarket;

import java.math.BigDecimal;

public class DiscountByQuantity implements DiscountScheme {

    Integer quantity;
    Integer freeQuantity;

    DiscountByQuantity(Integer quantity, Integer freeQuantity) {
        this.quantity = quantity;
        this.freeQuantity = freeQuantity;
    }

    @Override
    public BigDecimal discount(Item item, Long purchasedQty) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantityPromotion must be greater than 0");
        }

        long numberOfFreeQuantity = ((purchasedQty / quantity) * freeQuantity);
        return item.price().multiply(new BigDecimal(numberOfFreeQuantity)) ;
    }
}
