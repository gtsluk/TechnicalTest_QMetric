package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                multipleItemsPricedPerUnitBuy1Free1(),
                multipleItemsPricedPerUnitBuy3Free1(),
                multipleItemsPricedPerUnitBuy2For1Dollar(),
                aSingleItemPricedByWeightForHalfPrice()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedPerUnitBuy1Free1() {
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeByQuantity(2,1);
        Product product1 = new Product(new BigDecimal("1.55"));
        factory.registerProductDiscount(product1, scheme);
        return Arguments.of("multiple items priced per unit & Digestives 1 free 1", "2.04",
                Arrays.asList(product1.oneOf(), product1.oneOf(), aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedPerUnitBuy3Free1() {
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeByQuantity(3,1);
        Product product1 = new Product(new BigDecimal("1.55"));
        factory.registerProductDiscount(product1, scheme);
        return Arguments.of("multiple items priced per unit & Digestives 3 for 2", "3.59",
                Arrays.asList(product1.oneOf(), product1.oneOf(), product1.oneOf(), aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedPerUnitBuy2For1Dollar() {
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeBySubtotal(2,BigDecimal.ONE);
        Product product1 = new Product(new BigDecimal("1.55"));
        factory.registerProductDiscount(product1, scheme);
        return Arguments.of("multiple items priced per unit & Digestives 2 with 1 dollar", "1.49",
                Arrays.asList(product1.oneOf(), product1.oneOf(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedByWeightForHalfPrice() {
        DiscountSchemeFactory factory = DiscountSchemeFactory.getInstance();
        DiscountScheme scheme = factory.createSchemeByPercentage(1,0.50f);
        WeighedProduct product1 = new WeighedProduct(new BigDecimal("0.4"));
        factory.registerWeighedProductDiscount(product1, scheme);
        return Arguments.of("a single weighed item with half-Price", "0.200",
                Collections.singleton(product1.weighing(BigDecimal.ONE)));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}