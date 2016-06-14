package spells;

import entities.Character;

import java.util.function.Predicate;

/**
 * The SpellDecorator is an abstraction who gives a lot of function to make
 * operations on decorators.
 */
public abstract class SpellDecorator implements Spell {
	protected Spell spell;

	public SpellDecorator(Spell spell) {
		this.spell = spell;
	}

	/**
	 * Gets if the spell is a valid spell for a specific decorator.
	 *
	 * @param spell the spell to test
	 * @return true if it's valid, false if it's not
	 */
	public abstract boolean isValid(Spell spell);

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
	 * Private function called by addAt, this function don't check if the spell
	 * is valid.
	 * {@link #addAt(int, SpellDecorator)}
	 */
	private Spell addAtRecursive(int pos, SpellDecorator newOne) throws IndexOutOfBoundsException {
		if (pos < 0) throw new IndexOutOfBoundsException();

		if (pos == 0) {
			newOne.spell = spell;
			spell = newOne;
			return this;
		} else if (spell instanceof SpellDecorator) {
			spell = ((SpellDecorator) spell).addAtRecursive(pos - 1, newOne);
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
	 * @throws IndexOutOfBoundsException if the position is out of bounds
	 * @throws IllegalArgumentException  if the decorator can't be added
	 */
	public Spell addAt(int pos, SpellDecorator newOne)
			throws IllegalArgumentException, IndexOutOfBoundsException {
		if (isValid(addAtRecursive(pos, newOne))) {
			return this;
		} else {
			removeAt(pos + 1);
			throw new IllegalArgumentException("Can't add the decorator!");
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
	 * @throws IndexOutOfBoundsException if the spell don't match the predicate
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
