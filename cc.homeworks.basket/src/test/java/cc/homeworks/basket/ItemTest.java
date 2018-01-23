package cc.homeworks.basket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link Item}s
 */
public class ItemTest {

	private Item item;

	@Before
	public void before() {
		item = new Item("1", "name", 1.0);
	}

	@Test
	public void testItemIdIsNotNull() {
		assertNotNull(item.getId());
	}

	@Test
	public void testItemId() {
		assertEquals("1", item.getId());
	}

	@Test
	public void testItemName() {
		assertEquals("name", item.getName());
	}

	@Test
	public void testItemPrice() {
		assertEquals(1.0, item.getPrice(), 0.0);
	}

	@Test
	public void testItemHashCodeAndEquals() {

		final List<Item> list = new ArrayList<>();

		assertTrue(list.add(item));
		assertTrue(list.add(item));
		assertEquals(2, list.size());

		final Set<Item> set = new HashSet<>();

		assertTrue(set.add(item));
		assertFalse(set.add(item));
		assertEquals(1, set.size());
	}
}
