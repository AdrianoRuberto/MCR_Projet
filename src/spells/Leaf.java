package spells;

public class Leaf extends Element {
	private static Leaf instance;

	private Leaf() {
	}

	public static Leaf getInstance() {
		if (instance == null) instance = new Leaf();
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element strongAgainst() {
		return Rock.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element weakAgainst() {
		return Fire.getInstance();
	}
}
