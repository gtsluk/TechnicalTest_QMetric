package kata.supermarket;

import java.math.BigDecimal;
import java.util.Optional;

public class DiscountBySubtotal implements DiscountScheme {

    Integer quantity;
    BigDecimal subTotal;

    DiscountBySubtotal(Integer quantity, BigDecimal subTotal) {
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    @Override
    public BigDecimal discount(Item item, Long purchasedQty) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("subtotal Promotion must be greater than 0");
        }
        long numberOfPromotion = purchasedQty/quantity;
        BigDecimal totalPrice = item.price().multiply(new BigDecimal(purchasedQty));
        return totalPrice.subtract(subTotal.multiply(new BigDecimal(numberOfPromotion)));
    }
}
