package spells;

import entities.Character;

import java.util.Optional;

public class Spell {
	private Element element;

	public Spell(Element element) {
		this.element = element;
	}

	public int hit(Character from, Character to) {
		Optional<Element> last = to.getLastElement();

		double dmg = 10;
		if (last.isPresent()) {
			if (element.strongAgainst() == last.get())
				dmg *= 1.5;
			else if (element.weakAgainst() == last.get())
				dmg *= 0.5;
		}

		to.receiveDamage((int) dmg, element);

		return 10;
	}
}
