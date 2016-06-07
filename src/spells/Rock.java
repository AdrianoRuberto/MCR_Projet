package spells;

public class Rock extends Element {
	private static Rock instance;

	private Rock() { }

	public static Rock getInstance() {
		if (instance == null) instance = new Rock();
		return instance;
	}

	@Override
	public Element strongAgainst() {
		return Thunder.getInstance();
	}

	@Override
	public Element weakAgainst() {
		return null;
	}
}
