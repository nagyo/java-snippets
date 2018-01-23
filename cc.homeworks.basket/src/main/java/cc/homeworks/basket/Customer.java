package cc.homeworks.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cc.homeworks.basket.discounts.LoyaltyDiscount;

/**
 * A Customer is responsible for collecting {@link Item}s in her {@link Basket}. She could have different discounts and
 * she either could be loyal or not, but in any point in time it is a requirement to be able to calculate the current
 * total value of the basket (with and without discounts applied).
 */
public class Customer {

	/**
	 * Default percentage given for loyal customers
	 */
	public static final double DEFAULT_LOYALTY_DISCOUNT = 2.0;

	private String name;
	private boolean isLoyal;
	private final Basket basket;
	private final List<Discount> discounts;

	public Customer(final String name) {
		this.name = name;
		this.basket = new Basket();
		this.discounts = new ArrayList<>();
	}

	/**
	 * @return the {@link Basket} of the {@link Customer}
	 */
	public Basket getBasket() {
		return basket;
	}

	/**
	 * @return the name of the {@link Customer}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the desired name for the {@link Customer}
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return {@code true} if the {@link Customer} is loyal {@code false} otherwise
	 */
	public boolean isLoyal() {
		return isLoyal;
	}

	/**
	 * <p>
	 * If set to {@code true} the {@link Customer} will be considered loyal. That automatically means a loyalty
	 * discount.
	 * </p>
	 *
	 * <p>
	 * If set to {@code false} the {@link Customer} will loose any previously acquired {@link LoyaltyDiscount}s.
	 * </p>
	 *
	 * <p>
	 * Default loyalty discount is {@value #DEFAULT_LOYALTY_DISCOUNT}%
	 * </p>
	 *
	 * @param isLoyal
	 *            {@code true} means loyal, {@code false} means either new or "disloyal"
	 */
	public void setLoyal(final boolean isLoyal) {
		this.isLoyal = isLoyal;

		if (isLoyal) {

			if (discounts.stream().noneMatch(d -> d instanceof LoyaltyDiscount)) {
				discounts.add(new LoyaltyDiscount(DEFAULT_LOYALTY_DISCOUNT));
			}

		} else {

			final Optional<Discount> loyaltyDiscount = discounts.stream()
					.filter(d -> d instanceof LoyaltyDiscount)
					.findAny(); // must be only one LoyaltyDiscount

			if (loyaltyDiscount.isPresent()) {
				discounts.remove(loyaltyDiscount.get());
			}

		}
	}

	/**
	 * @return the list of {@link Discount} currently available for the given {@link Customer}
	 */
	public List<Discount> getDiscounts() {
		return discounts;
	}

	/**
	 * @param discount
	 *            the discount which needs to be added to the {@link Customer}'s set of discounts.
	 *            {@link LoyaltyDiscount} cannot be used here, it is a consequence of being loyal or not
	 */
	public void setDiscount(final Discount discount) {

		if (discount instanceof LoyaltyDiscount) {
			throw new IllegalArgumentException("Loyalty discount is a consequence of being loyal");
		}

		discounts.add(discount);
	}

	/**
	 * @return the total value of the {@link Basket} <b>without</b> discounts
	 */
	public double getTotalBasketValue() {
		return basket.getItems().stream().mapToDouble(Item::getPrice).sum();
	}

	/**
	 * Calculates the total value of the {@link Basket} by applying all available discounts in the preferred order (see:
	 * {@link Discount#getPriority()}
	 *
	 * @return the total value of the {@link Basket} after discounts were applied
	 */
	public double getTotalBasketValueWithDiscounts() {

		double currentTotalValue = getTotalBasketValue();

		final List<Discount> sortedDiscounts = discounts.stream()
				.sorted((d1, d2) -> Integer.compare(d1.getPriority(), d2.getPriority()))
				.collect(Collectors.toList());

		for (final Discount discount : sortedDiscounts) {
			currentTotalValue = discount.apply(basket, currentTotalValue);
		}

		return currentTotalValue;
	}

}
