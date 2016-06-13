package spells;

import entities.Character;

import java.util.function.Predicate;

public abstract class SpellDecorator implements Spell {
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
	 * Adds a new decorator at the given position.
	 *
	 * @param pos    the position
	 * @param newOne the new SpellDecorator
	 * @return the spell with the added decorator
	 */
	public Spell addAt(int pos, SpellDecorator newOne) {
		if (pos < 0) throw new IndexOutOfBoundsException();

		if (pos == 0) {
			newOne.spell = spell;
			spell = newOne;
			return this;
		} else if (spell instanceof SpellDecorator) {
			spell = ((SpellDecorator) spell).addAt(pos - 1, newOne);
			return this;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Alters the spell by first add the new spell and then
	 * remove the altered spell.
	 *
	 * @param pos    the position
	 * @param newOne the new SpellDecorator
	 * @return the spell with the altered spell
	 */
	public Spell alter(int pos, SpellDecorator newOne) {
		addAt(pos, newOne);
		return removeAt(pos);
	}

	/**
	 * Gets the position in the spell of the decorator according of the
	 * predicate.
	 *
	 * @param spell     the spell
	 * @param predicate the predicate
	 * @return the position
	 * @throws IndexOutOfBoundsException if the spell don't match the
	 *                                   predicate
	 */
	public int getPos(Spell spell, Predicate<SpellDecorator> predicate) throws IndexOutOfBoundsException {

		int i = 0;
		for (Spell tmp = spell; tmp instanceof SpellDecorator; ++i, tmp = ((SpellDecorator) tmp).spell) {
			if (predicate.test((SpellDecorator) tmp)) {
				return i;
			}
		}
		throw new IndexOutOfBoundsException();
	}
}
