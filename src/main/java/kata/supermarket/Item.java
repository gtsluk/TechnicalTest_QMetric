package kata.supermarket;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();
    DiscountScheme discount();
    long getProductKey();
}
