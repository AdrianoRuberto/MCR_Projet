package spells;

public class Water extends Element {
	private static Water instance;

	private Water() { }

	public static Water getInstance() {
		if (instance == null) instance = new Water();
		return instance;
	}

	@Override
	public Element strongAgainst() {
		return Fire.getInstance();
	}

	@Override
	public Element weakAgainst() {
		return Thunder.getInstance();
	}

}
