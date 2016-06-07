package spells;

import entities.Character;

public class Fire extends Element {
	private static Fire instance;

	private Fire() { }

	public static Fire getInstance() {
		if (instance == null)
			instance = new Fire();
		return instance;
	}

	@Override
	public Aura auraEffect(Aura a) {
		if (a == Aura.WATER) return Aura.NORMAL;
		else if (a == Aura.LEAF) return Aura.FIRE;
		else return a;
	}

	@Override
	public int manaCost() {
		return 0;
	}

	@Override
	public Element effectiveAgainst() {
		return Leaf.getInstance();
	}

	@Override
	public Element weakAgainst() {
		return null;
	}

}
