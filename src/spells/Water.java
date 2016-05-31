package spells;

import entities.Character;
import entities.Monster;

public class Water extends Element {
	private static Water instance;

	private Water() {
	}

	public static Water getInstance() {
		if (instance == null)
			instance = new Water();
		return instance;
	}

	@Override
	public void applyEffect(Character from, Character to) {
		to.setState(to.getState() == State.BURNED ? State.NORMAL : State.WET);
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
		return Fire.getInstance();
	}

}
