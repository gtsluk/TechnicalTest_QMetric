package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DiscountSchemeFactoryTest {

    @Test
    void getInstance() {
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        assertNotNull(factory);
    }

    @Test
    void createSchemeByPercentage() {
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeByPercentage(1, 50);
        assertNotNull(scheme);
    }

    @Test
    void createSchemeBySubtotal() {
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeBySubtotal(2, BigDecimal.ONE);
        assertNotNull(scheme);
    }

    @Test
    void createSchemeByQuantity() {
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeByQuantity(3, 1);
        assertNotNull(scheme);
    }

    @Test
    void registerProductDiscount() {
        Product product = new Product(new BigDecimal("1.55"));
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeByQuantity(3, 1);
        factory.registerProductDiscount(product, scheme);
        DiscountScheme verify = factory.getDiscountScheme(product);
        assertEquals(scheme, verify);
    }

    @Test
    void registerWeighedProductDiscount() {
        WeighedProduct product = new WeighedProduct(new BigDecimal("4.99"));
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeByQuantity(3, 1);
        factory.registerWeighedProductDiscount(product, scheme);
        DiscountScheme verify = factory.getDiscountScheme(product);
        assertEquals(scheme, verify);
    }

}
