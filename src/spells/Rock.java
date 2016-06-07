package spells;

public class Rock extends Element {
	private static Rock instance;

	private Rock() {
	}

	public static Rock getInstance() {
		if (instance == null)
			instance = new Rock();
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
		return Thunder.getInstance();
	}
}
