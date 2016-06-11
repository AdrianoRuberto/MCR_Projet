package spells;

import entities.Character;

public abstract class SpellDecorator extends Spell {
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

	@Override
	public String toString() {
		return spell + " is decorated";
	}

	/**
	 * Removes the decorator at the given position.
	 *
	 * @param pos the position
	 */
	public Spell removeAt(int pos) {
		if (pos < 0)
			throw new IndexOutOfBoundsException();

		if (pos == 0)
			return spell;
		else if (spell instanceof SpellDecorator) {
			spell = ((SpellDecorator) spell).removeAt(pos - 1);
			return this;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Counts the number of decorators
	 *
	 * @return the number of decorators
	 */
	public int nbDecorators() {
		int nb = 1;
		for (Spell tmp = spell; tmp instanceof SpellDecorator; tmp = ((SpellDecorator) tmp).spell) {
			++nb;
		}
		return nb;
	}
}
