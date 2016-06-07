package spells;

import entities.Character;

public class CombinedSpell extends Spell {
	private Spell spell;

	public CombinedSpell(Spell spell, Element element) {
		super(element);
		this.spell = spell;
	}

	public int hit(Character from, Character to) {
		return super.hit(from, to) + spell.hit(from, to);
	}
}
