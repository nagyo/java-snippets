package cc.homeworks.basket.discounts;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cc.homeworks.basket.Basket;
import cc.homeworks.basket.Item;

/**
 * Test class for {@link BogofDiscount}
 */
public class BogofDiscountTest {

	private BogofDiscount discount;
	private Basket basket;
	private Item firstItem;
	private Item secondItem;

	@Before
	public void before() {
		discount = new BogofDiscount();
		basket = new Basket();
		firstItem = new Item("1", "name", 1.0);
		secondItem = new Item("2", "name", 2.0);
	}

	@Test
	public void testBogofWithEmptyBasket() {
		assertEquals(0.0, discount.apply(basket, 0.0), 0.0);
	}

	@Test
	public void testBogofWithSingleItem() {
		basket.add(firstItem);
		assertEquals(1.0, discount.apply(basket, 0.0), 0.0);
	}

	@Test
	public void testBogofWithTwoIdenticalItems() {
		basket.add(firstItem);
		basket.add(firstItem);
		assertEquals(1.0, discount.apply(basket, 0.0), 0.0);
	}

	@Test
	public void testBogofWithThreeIdenticalItems() {
		basket.add(firstItem);
		basket.add(firstItem);
		basket.add(firstItem);
		assertEquals(2.0, discount.apply(basket, 0.0), 0.0);
	}

	@Test
	public void testBogofWithFourIdenticalItems() {
		basket.add(firstItem);
		basket.add(firstItem);
		basket.add(firstItem);
		basket.add(firstItem);
		assertEquals(2.0, discount.apply(basket, 0.0), 0.0);
	}

	@Test
	public void testBogofWithTwoDifferentItems() {
		basket.add(firstItem);
		basket.add(secondItem);
		assertEquals(3.0, discount.apply(basket, 0.0), 0.0);
	}

	@Test
	public void testBogofWithOneDifferentAndTwoSameItems() {
		basket.add(firstItem);
		basket.add(firstItem);
		basket.add(secondItem);
		assertEquals(3.0, discount.apply(basket, 0.0), 0.0);
	}

	@Test
	public void testBogofWithTwoTwoItems() {
		basket.add(firstItem);
		basket.add(firstItem);
		basket.add(secondItem);
		basket.add(secondItem);
		assertEquals(3.0, discount.apply(basket, 0.0), 0.0);
	}

}

