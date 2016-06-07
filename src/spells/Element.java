package spells;

public abstract class Element {
	/**
	 * Gets the element which is strong against.
	 *
	 * @return the element which is strong against
	 */
	public abstract Element strongAgainst();

	/**
	 * Gets the element which is weak against.
	 *
	 * @return the element which is weak against
	 */
	public abstract Element weakAgainst();
}


