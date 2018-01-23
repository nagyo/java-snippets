package cc.homeworks.basket.discounts;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cc.homeworks.basket.Basket;

/**
 * Test class for {@link LoyaltyDiscount}
 */
public class LoyaltyDiscountTest {

	private Basket basket;

	@Before
	public void before() {
		basket = new Basket();
	}

	@Test
	public void testLoyaltyDiscountWithZeroCurrentTotal() {
		assertEquals(0.0, new LoyaltyDiscount(10.0).apply(basket, 0.0), 0.0);
	}

	@Test
	public void testLoyaltyDiscountWithZeroPercent() {
		assertEquals(100.0, new LoyaltyDiscount(0.0).apply(basket, 100.0), 0.0);
	}

	@Test
	public void testLoyaltyDiscountWithOnePercent() {
		assertEquals(99.0, new LoyaltyDiscount(1.0).apply(basket, 100.0), 0.0);
	}

	@Test
	public void testLoyaltyDiscountWithTenPercent() {
		assertEquals(90.0, new LoyaltyDiscount(10.0).apply(basket, 100.0), 0.0);
	}

	@Test
	public void testLoyaltyDiscountWithHundredPercent() {
		assertEquals(0.0, new LoyaltyDiscount(100.0).apply(basket, 100.0), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLoyaltyDiscountWithHundredAndTenPercent() {
		new LoyaltyDiscount(110.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLoyaltyDiscountWithNegativePercent() {
		new LoyaltyDiscount(-10.0);
	}

}
