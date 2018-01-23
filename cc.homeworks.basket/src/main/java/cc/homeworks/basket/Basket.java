package cc.homeworks.basket;

import java.util.ArrayList;
import java.util.List;

/**
 * A Basket is used for storing shopping items (including add, remove or clear all)
 */
public class Basket {

	private final List<Item> items;

	public Basket() {
		this.items = new ArrayList<>();
	}

	/**
	 * @return the list of items currently added to the basket
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param item the item which needs to be added to the basket
	 * @return <code>true</code> if the addition was successful, <code>false</code> otherwise
	 */
	public boolean add(final Item item) {
		return items.add(item);
	}

	/**
	 * @param item the item which must be removed from the basket
	 * @return <code>true</code> if the removal was successful, <code>false</code> otherwise
	 */
	public boolean remove(final Item item) {
		return items.remove(item);
	}

	/**
	 * Clears all items currently in the basket
	 */
	public void clear() {
		items.clear();
	}

}
