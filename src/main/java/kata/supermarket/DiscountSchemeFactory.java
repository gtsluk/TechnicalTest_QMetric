package kata.supermarket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DiscountSchemeFactory {
    Map<Object, DiscountScheme> productDiscountMap = new HashMap<Object, DiscountScheme>();

    static DiscountSchemeFactory _instance = null;

    private DiscountSchemeFactory() {}

    public static DiscountSchemeFactory getInstance() {
        if (_instance == null) {
            _instance = new DiscountSchemeFactory();
        }
        return _instance;
    }

    //create DiscountScheme with percentage of price.
    public DiscountScheme createSchemeByPercentage(int quantity, float percentage) {
        return new DiscountByPercentage(quantity, percentage);
    }

    //create DiscountScheme with fixed amount discount
    public DiscountScheme createSchemeBySubtotal(int quantity, BigDecimal subTotal) {
        return new DiscountBySubtotal(quantity, subTotal);
    }

    //create DiscountScheme with free quantity
    public DiscountScheme createSchemeByQuantity(int quantity, int freeQuantity) {
        return new DiscountByQuantity(quantity, freeQuantity);
    }

    //Cached the Discount scheme of each product.
    public void registerProductDiscount(Product product, DiscountScheme discountScheme) {
        productDiscountMap.put(product, discountScheme);
    }

    //Cached the Discount scheme of each weighed product.
    public void registerWeighedProductDiscount(WeighedProduct product, DiscountScheme discountScheme) {
        productDiscountMap.put(product, discountScheme);
    }

    //get the discount scheme by product.
    public DiscountScheme getDiscountScheme(Product product) {
        return productDiscountMap.get(product);
    }

    //get the discount scheme by weighedProduct.
    public DiscountScheme getDiscountScheme(WeighedProduct product) {
        return productDiscountMap.get(product);
    }
}
