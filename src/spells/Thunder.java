package spells;

public class Thunder extends Element {
	private static Thunder instance;

	private Thunder() { }

	public static Thunder getInstance() {
		if (instance == null) instance = new Thunder();
		return instance;
	}

	@Override
	public Element strongAgainst() {
		return Water.getInstance();
	}

	@Override
	public Element weakAgainst() {
		return null;
	}
}
