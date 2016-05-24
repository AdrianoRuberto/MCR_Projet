package spells;

import entities.Monster;

public class Spell {
	protected Element element;

	public Spell(Element element) {
		this.element = element;
	}

	public int hit(Monster m) {
		element.applyEffect(m);
		return element.manaCost();
	}
}
