package cc.homeworks.basket.discounts;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cc.homeworks.basket.Basket;

/**
 * Test class for {@link PercentageDiscount}
 */
public class PercentageDiscountTest {

	private Basket basket;

	@Before
	public void before() {
		basket = new Basket();
	}

	@Test
	public void testPercentageDiscountWithZeroCurrentTotal() {
		assertEquals(0.0, new PercentageDiscount(10.0, 10.0).apply(basket, 0.0), 0.0);
	}

	@Test
	public void testPercentageDiscountWithZeroThreshold() {
		assertEquals(9.0, new PercentageDiscount(0.0, 10.0).apply(basket, 10.0), 0.0);
	}

	@Test
	public void testPercentageDiscountWithEqualThresholdAndTotal() {
		assertEquals(10.0, new PercentageDiscount(10.0, 10.0).apply(basket, 10.0), 0.0);
	}

	@Test
	public void testPercentageDiscountBelowThreshold() {
		assertEquals(10.0, new PercentageDiscount(11.0, 10.0).apply(basket, 10.0), 0.0);
	}

	@Test
	public void testPercentageDiscountBelowThresholdWithFraction() {
		assertEquals(99.9, new PercentageDiscount(100.0, 10.0).apply(basket, 99.9), 0.0);
	}

	@Test
	public void testPercentageDiscountAboveThreshold() {
		assertEquals(90.0, new PercentageDiscount(99.0, 10.0).apply(basket, 100.0), 0.0);
	}

	@Test
	public void testPercentageDiscountAboveThresholdWithFraction() {
		assertEquals(90.0, new PercentageDiscount(99.9, 10.0).apply(basket, 100.0), 0.0);
	}

	@Test
	public void testPercentageDiscountWithZeroPercent() {
		assertEquals(100.0, new PercentageDiscount(10.0, 0.0).apply(basket, 100.0), 0.0);
	}

	@Test
	public void testPercentageDiscountWithOnePercent() {
		assertEquals(99.0, new PercentageDiscount(10.0, 1.0).apply(basket, 100.0), 0.0);
	}

	@Test
	public void testPercentageDiscountWithTenPercent() {
		assertEquals(90.0, new PercentageDiscount(10.0, 10.0).apply(basket, 100.0), 0.0);
	}

	@Test
	public void testPercentageDiscountWithHundredPercent() {
		assertEquals(0.0, new PercentageDiscount(10.0, 100.0).apply(basket, 100.0), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPercentageDiscountWithHundredAndTenPercent() {
		new PercentageDiscount(10.0, 110.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPercentageDiscountWithNegativePercent() {
		new PercentageDiscount(10.0, -10.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPercentageDiscountWithNegativeThresHold() {
		new PercentageDiscount(-10.0, 10.0);
	}
}
