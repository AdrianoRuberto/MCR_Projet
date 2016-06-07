package spells;

public class Fire extends Element {
	private static Fire instance;

	private Fire() {
	}

	public static Fire getInstance() {
		if (instance == null) instance = new Fire();
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element strongAgainst() {
		return Leaf.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element weakAgainst() {
		return Water.getInstance();
	}
}
