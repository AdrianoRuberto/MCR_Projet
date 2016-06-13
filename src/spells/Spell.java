package spells;

import entities.Character;

/**
 * The Spell interface with hit and getManaCost function.
 */
public interface Spell {

	/**
	 * Hits a character with this spell.
	 *
	 * @param from the character who cast the spell
	 * @param to   the character who get hit by the spell
	 */
	void hit(Character from, Character to);

	/**
	 * Gets the mana cost of the spell.
	 *
	 * @return the mana cost of the spell
	 */
	int getManaCost();
}
