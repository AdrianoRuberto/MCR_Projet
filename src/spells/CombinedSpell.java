package spells;

import entities.Character;

public class CombinedSpell extends Spell {
	private Spell spell;

	public CombinedSpell(Spell spell, Element element) {
		super(element);
		this.spell = spell;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hit(Character from, Character to) {
		super.hit(from, to);
		spell.hit(from, to);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getManaCost() {
		return super.getManaCost() + spell.getManaCost();
	}
}
