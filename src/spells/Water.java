package spells;

import entities.Character;

public class Water extends Element {
	private static Water instance;

	private Water() { }

	public static Water getInstance() {
		if (instance == null)
			instance = new Water();
		return instance;
	}

	@Override
	public void applyEffect(Character from, Character to) {
		to.setAura(to.getAura() == Aura.BURNED ? Aura.NORMAL : Aura.WET);
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
