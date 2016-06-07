package spells;

public class Thunder extends Element {
	private static Thunder instance;

	private Thunder() {
	}

	public static Thunder getInstance() {
		if (instance == null) instance = new Thunder();
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element strongAgainst() {
		return Water.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element weakAgainst() {
		return Rock.getInstance();
	}
}
