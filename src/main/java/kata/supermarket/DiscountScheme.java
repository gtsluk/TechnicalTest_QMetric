package kata.supermarket;

import java.math.BigDecimal;

public interface DiscountScheme {
    BigDecimal discount(Item item, Long purchasedQty);
}
