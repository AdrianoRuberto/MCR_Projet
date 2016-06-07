package spells;

public class Fire extends Element {
	private static Fire instance;

	private Fire() { }

	public static Fire getInstance() {
		if (instance == null) instance = new Fire();
		return instance;
	}

	@Override
	public Element strongAgainst() {
		return Leaf.getInstance();
	}

	@Override
	public Element weakAgainst() {
		return null;
	}

}
