package spells;

import entities.Character;

import java.util.Optional;

public class Spell {
	private Element element;

	public Spell(Element element) {
		this.element = element;
	}

	/**
	 * Hits a character with this spell.
	 *
	 * @param from the character who cast the spell
	 * @param to the character who get it by the spell
	 */
	public void hit(Character from, Character to) {
		Optional<Element> last = to.getLastElement();

		double dmg = 10;
		if (last.isPresent()) {
			if (element.strongAgainst() == last.get())
				dmg *= 1.5;
			else if (element.weakAgainst() == last.get())
				dmg *= 0.5;
		}

		to.receiveDamage((int) dmg, element);
	}

	/**
	 * Gets the mana cost of the spell.
	 *
	 * @return the mana cost of the spell
	 */
	public int getManaCost() {
		return 10;
	}
}
