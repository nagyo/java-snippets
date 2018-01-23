package cc.homeworks.basket.discounts;

import cc.homeworks.basket.Basket;
import cc.homeworks.basket.Discount;

/**
 * Loyalty discount, simple percentage based discount
 */
public class LoyaltyDiscount implements Discount {

	private final double percentage;

	public LoyaltyDiscount(final double percentage) {

		if (percentage > 100.0) {
			throw new IllegalArgumentException("Percentage must be less than or equal to 100%");
		} else if (percentage < 0.0) {
			throw new IllegalArgumentException("Percentage must be greater than or equal to 0%");
		}

		this.percentage = percentage;
	}

	@Override
	public int getPriority() {
		return 3;
	}

	@Override
	public double apply(final Basket basket, final double currentTotalValue) {
		return currentTotalValue * ((100 - percentage) / 100);
	}

}
