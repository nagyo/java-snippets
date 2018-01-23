package cc.homeworks.basket.discounts;

import java.util.Map;
import java.util.stream.Collectors;

import cc.homeworks.basket.Basket;
import cc.homeworks.basket.Discount;
import cc.homeworks.basket.Item;

/**
 * Buy-One-Get-One-Free discount. This discount must be applied first, before any value or percentage based discount is
 * applied.
 */
public class BogofDiscount implements Discount {

	@Override
	public int getPriority() {
		return 1;
	}

	@Override
	public double apply(final Basket basket, final double currentTotalValue) {

		final Map<String, Double> idToPriceMap = basket.getItems().stream()
				.collect(Collectors.toMap(Item::getId, Item::getPrice, (price1, price2) -> price1));

		final Map<String, Long> idToOccurenceMap = basket.getItems().stream()
				.collect(Collectors.groupingBy(Item::getId, Collectors.counting()));

		double totalValue = 0.0;

		totalValue += idToOccurenceMap.entrySet().stream().filter(e -> (e.getValue() % 2) == 0)
				.mapToDouble(e -> (e.getValue() / 2) * idToPriceMap.get(e.getKey())).sum();

		totalValue += idToOccurenceMap.entrySet().stream().filter(e -> (e.getValue() % 2) != 0)
				.mapToDouble(e -> (((e.getValue() - 1) / 2) + 1) * idToPriceMap.get(e.getKey())).sum();

		return totalValue;
	}

}
