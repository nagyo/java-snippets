package cc.homeworks.basket;

/**
 * General interface for discounts. A discount implementation must be responsible for calculating the total price of a
 * basket based on a current total value (it is possible that other discounts were applied before). To make sure every
 * discount is applied in the preferred order, they must have a priority defined.
 */
public interface Discount {

	/**
	 * The priority of the discount, used for determining order
	 *
	 * @return the priority as an integer
	 */
	int getPriority();

	/**
	 * Applies the given discount to the specified {@link Basket} by using the currentTotalValue as a base
	 *
	 * @param basket the basket which holds the {@link Item}s
	 * @param currentTotalValue the current total value of the basket
	 * @return the price calculated by applying the given discount
	 */
	double apply(Basket basket, double currentTotalValue);

}
