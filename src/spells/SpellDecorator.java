package spells;

import entities.Character;

public class SpellDecorator extends Spell {
	private Spell spell;

	public SpellDecorator(Spell spell) {
		this.spell = spell;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hit(Character from, Character to) {
		spell.hit(from, to);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getManaCost() {
		return spell.getManaCost();
	}
}
