package spells;

import entities.Character;

import java.util.Optional;

/**
 * Projet : MCR_Projet
 * Créé le 11.06.2016.
 *
 * @author Adriano Ruberto
 */
public class ElementSpellDecorator extends SpellDecorator {

	private final Element element;

	public ElementSpellDecorator(Spell spell, Element element) {
		super(spell);
		this.element = element;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hit(Character from, Character to) {
		super.hit(from, to);
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

	@Override
	public String toString() {
		return super.toString() + " with " + element;
	}
}
