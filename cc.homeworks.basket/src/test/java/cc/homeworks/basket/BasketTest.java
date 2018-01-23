package cc.homeworks.basket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link Basket}
 */
public class BasketTest {

	private Basket basket;
	private Item item;

	@Before
	public void before() {
		basket = new Basket();
		item = new Item("1", "name", 1.0);
	}

	@Test
	public void testBasketItemsIsNotNull() {
		assertNotNull(basket.getItems());
	}

	@Test
	public void testAddItemToBasket() {
		assertTrue(basket.getItems().isEmpty());
		basket.add(item);
		assertEquals(1, basket.getItems().size());
		final Item firstItem = basket.getItems().get(0);
		assertEquals(item, firstItem);
	}

	@Test
	public void testRemoveItemFromBasket() {
		basket.add(item);
		assertEquals(1, basket.getItems().size());
		basket.remove(item);
		assertTrue(basket.getItems().isEmpty());
	}

	@Test
	public void testClearBasket() {
		basket.add(item);
		basket.add(item);
		assertEquals(2, basket.getItems().size());
		basket.clear();
		assertTrue(basket.getItems().isEmpty());
	}

}
