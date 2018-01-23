package cc.homeworks.basket.discounts;

import cc.homeworks.basket.Basket;
import cc.homeworks.basket.Discount;

/**
 * Total amount and percentage based discount. After a certain threshold the specified percentage will be given
 */
public class PercentageDiscount implements Discount {

	private final double thresholdValue;
	private final double percentage;

	public PercentageDiscount(final double thresholdValue, final double percentage) {

		if (thresholdValue < 0) {
			throw new IllegalArgumentException("Threshold must be greater than or equal to 0");
		}

		this.thresholdValue = thresholdValue;

		if (percentage > 100.0) {
			throw new IllegalArgumentException("Percentage must be less than or equal to 100%");
		} else if (percentage < 0.0) {
			throw new IllegalArgumentException("Percentage must be greater than or equal to 0%");
		}

		this.percentage = percentage;
	}

	@Override
	public int getPriority() {
		return 2;
	}

	@Override
	public double apply(final Basket basket, final double currentTotalValue) {
		if (currentTotalValue > thresholdValue) {
			return currentTotalValue * ((100 - percentage) / 100);
		}
		return currentTotalValue;
	}

}
