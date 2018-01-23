package cc.homeworks.basket;

/**
 * Arbitrary item in a shopping basket. An item must have a unique identifier, name and a price tag
 */
public class Item {

	private final String id;
	private final String name;
	private final double price;

	public Item(final String id, final String name, final double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	/**
	 * @return the id of the item
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price of the item
	 */
	public double getPrice() {
		return price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Item other = (Item) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
