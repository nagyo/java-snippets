package cc.homeworks.basket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import cc.homeworks.basket.discounts.BogofDiscount;
import cc.homeworks.basket.discounts.LoyaltyDiscount;
import cc.homeworks.basket.discounts.PercentageDiscount;

/**
 * Test class for {@link Customer}
 */
public class CustomerTest {

	private static final String DEFAULT_NAME = "John Doe";

	private Customer customer;
	private Item firstItem;
	private Item secondItem;

	@Before
	public void before() {
		customer = new Customer(DEFAULT_NAME);
		firstItem = new Item("1", "name", 100.0);
		secondItem = new Item("2", "name", 200.0);
	}

	@Test
	public void testCustomerBasketIsNotNull() {
		assertNotNull(customer.getBasket());
	}

	@Test
	public void testCustomerBasketIsEmptyByDefault() {
		assertTrue(customer.getBasket().getItems().isEmpty());
	}

	@Test
	public void testCustomerDiscountsNotNull() {
		assertNotNull(customer.getDiscounts());
	}

	@Test
	public void testCustomerDiscountsEmptyByDefault() {
		assertTrue(customer.getDiscounts().isEmpty());
	}

	@Test
	public void testCustomerName() {
		assertEquals(DEFAULT_NAME, customer.getName());
	}

	@Test
	public void testCustomerNameChange() {
		assertEquals(DEFAULT_NAME, customer.getName());
		customer.setName("Jane Doe");
		assertEquals("Jane Doe", customer.getName());
	}

	@Test
	public void testBasketTotalValueWhenEmpty() {
		assertEquals(0.0, customer.getTotalBasketValue(), 0.0);
	}

	@Test
	public void testBasketTotalValueWithDiscountsWhenEmpty() {
		assertEquals(0.0, customer.getTotalBasketValueWithDiscounts(), 0.0);
	}

	@Test
	public void testCustomerIsNotLoyalByDefault() {
		assertFalse(customer.isLoyal());
	}

	@Test
	public void testCustomerSetToLoyal() {

		assertFalse(customer.isLoyal());
		assertTrue(customer.getDiscounts().isEmpty());
		customer.getBasket().add(firstItem);

		assertEquals(100.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(100.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

		customer.setLoyal(true);

		assertTrue(customer.isLoyal());
		assertEquals(1, customer.getDiscounts().size());
		assertTrue(customer.getDiscounts().get(0) instanceof LoyaltyDiscount);

		assertEquals(100.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(98.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

	}

	@Test
	public void testCustomerSetToDisLoyal() {

		customer.setLoyal(true);

		assertTrue(customer.isLoyal());
		assertEquals(1, customer.getDiscounts().size());
		assertTrue(customer.getDiscounts().get(0) instanceof LoyaltyDiscount);

		customer.getBasket().add(firstItem);

		assertEquals(100.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(98.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

		customer.setLoyal(false);

		assertFalse(customer.isLoyal());
		assertTrue(customer.getDiscounts().isEmpty());

		assertEquals(100.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(100.0, customer.getTotalBasketValueWithDiscounts(), 0.0);
	}

	@Test
	public void testCustomerWithPercentageDiscount() {

		customer.getBasket().add(firstItem);

		assertEquals(100.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(100.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

		customer.setDiscount(new PercentageDiscount(10.0, 20.0));

		assertEquals(100.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(80.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

	}

	@Test
	public void testCustomerWithBogofDiscount() {

		customer.getBasket().add(firstItem);
		customer.getBasket().add(firstItem);
		customer.getBasket().add(firstItem);

		assertEquals(300.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(300.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

		customer.setDiscount(new BogofDiscount());

		assertEquals(300.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(200.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

	}

	@Test
	public void testCustomerWithBogofAndPercentageWithoutLoyalty() {

		customer.getBasket().add(firstItem);
		customer.getBasket().add(firstItem);
		customer.getBasket().add(firstItem);

		assertEquals(300.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(300.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

		customer.setDiscount(new BogofDiscount());
		customer.setDiscount(new PercentageDiscount(20.0, 10.0));

		assertEquals(300.0, customer.getTotalBasketValue(), 0.0);

		// Bogof		300.0 -> 200.0
		// Percentage	200.0 -> 180.0
		assertEquals(180.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

	}

	@Test
	public void testCustomerWithBogofAndPercentageWithLoyalty() {

		customer.setLoyal(true);

		customer.getBasket().add(firstItem);
		customer.getBasket().add(firstItem);
		customer.getBasket().add(firstItem);

		assertEquals(300.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(294.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

		customer.setDiscount(new BogofDiscount());
		customer.setDiscount(new PercentageDiscount(20.0, 10.0));

		assertEquals(300.0, customer.getTotalBasketValue(), 0.0);

		// Bogof		300.0 -> 200.0
		// Percentage	200.0 -> 180.0
		// Loyalty		180.0 -> 176.4
		assertEquals(176.4, customer.getTotalBasketValueWithDiscounts(), 0.0);

	}

	@Test
	public void testCustomerWithBogofAndPercentageWithLoyaltyMultipleItems() {

		customer.setLoyal(true);

		customer.getBasket().add(firstItem);
		customer.getBasket().add(firstItem);
		customer.getBasket().add(firstItem);
		customer.getBasket().add(secondItem);
		customer.getBasket().add(secondItem);

		assertEquals(700.0, customer.getTotalBasketValue(), 0.0);
		assertEquals(686.0, customer.getTotalBasketValueWithDiscounts(), 0.0);

		customer.setDiscount(new BogofDiscount());
		customer.setDiscount(new PercentageDiscount(20.0, 10.0));

		assertEquals(700.0, customer.getTotalBasketValue(), 0.0);

		// Bogof		700.0 -> 400.0
		// Percentage	400.0 -> 360.0
		// Loyalty		360.0 -> 352.8
		assertEquals(352.8, customer.getTotalBasketValueWithDiscounts(), 0.0);

	}

}
