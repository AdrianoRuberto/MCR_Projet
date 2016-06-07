package spells;

public class Thunder extends Element {
	private static Thunder instance;

	private Thunder() {

	}

	public static Thunder getInstance() {
		if (instance == null)
			instance = new Thunder();
		return instance;
	}

	@Override
	public int manaCost() {
		return 0;
	}

	@Override
	public int damage() {
		return 0;
	}

	@Override
	public Element effectiveAgainst() {
		return Water.getInstance();
	}
}
