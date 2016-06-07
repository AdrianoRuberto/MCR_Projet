package spells;

public class Leaf extends Element {
	private static Leaf instance;

	private Leaf() { }

	public static Leaf getInstance() {
		if (instance == null)
			instance = new Leaf();
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
		return Rock.getInstance();
	}
}
