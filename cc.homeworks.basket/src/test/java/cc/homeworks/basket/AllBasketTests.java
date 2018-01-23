package cc.homeworks.basket;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cc.homeworks.basket.discounts.BogofDiscountTest;
import cc.homeworks.basket.discounts.LoyaltyDiscountTest;
import cc.homeworks.basket.discounts.PercentageDiscountTest;

@RunWith(Suite.class)
@SuiteClasses({
	BasketTest.class,
	CustomerTest.class,
	ItemTest.class,
	BogofDiscountTest.class,
	LoyaltyDiscountTest.class,
	PercentageDiscountTest.class
})
public class AllBasketTests {
}
