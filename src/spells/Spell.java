package spells;

import entities.Character;

/**
 * Projet : MCR_Projet
 * Créé le 11.06.2016.
 *
 * @author Adriano Ruberto
 */
public abstract class Spell {

	/**
	 * Hits a character with this spell.
	 *
	 * @param from the character who cast the spell
	 * @param to   the character who get hit by the spell
	 */
	public abstract void hit(Character from, Character to);

	/**
	 * Gets the mana cost of the spell.
	 *
	 * @return the mana cost of the spell
	 */
	public abstract int getManaCost();
}
