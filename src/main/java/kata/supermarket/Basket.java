package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            BigDecimal result = BigDecimal.ZERO;
            Map<Long, List<Item>> groupedItems =
                    items.stream().collect(Collectors.groupingBy(
                            Item::getProductKey));
            for (Map.Entry<Long, List<Item>> entry : groupedItems.entrySet()) {
                Item item = entry.getValue().get(0);
                long size = entry.getValue().size();
                DiscountScheme scheme = item.discount();
                if (scheme != null) {
                    result = result.add(scheme.discount(item, size));
                }
            }
            return result;
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
