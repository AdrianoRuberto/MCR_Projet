package spells;

import entities.Character;

import java.util.Optional;

public class ConcreteSpell extends Spell {
	public ConcreteSpell(Element element) {
		super(element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hit(Character from, Character to) {
		Optional<Element> last = to.getLastElement();

		double dmg = 10;
		if (last.isPresent()) {
			if (element.getStrong() == last.get())
				dmg *= 1.5;
			else if (element.getWeak() == last.get())
				dmg *= 0.5;
		}

		to.receiveDamage((int) dmg, element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getManaCost() {
		return 10;
	}
}
