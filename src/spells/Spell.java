package spells;

import entities.Character;

public class Spell {
	private Element element;

	public Spell(Element element) {
		this.element = element;
	}

	public int hit(Character from, Character to) {
		element.applyEffect(from, to);
		return element.manaCost();
	}
}
