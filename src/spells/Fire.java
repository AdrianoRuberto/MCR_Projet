package spells;

import entities.Character;

public class Fire extends Element {
	private static Fire instance;

	private Fire() {

	}

	public static Fire getInstance() {
		if (instance == null)
			instance = new Fire();
		return instance;
	}

	@Override
	public void applyEffect(Character from, Character to) {
		to.setState(to.getState() == State.WET ? State.NORMAL : State.BURNED);
		super.applyEffect(from, to);
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
		return Leaf.getInstance();
	}

}
