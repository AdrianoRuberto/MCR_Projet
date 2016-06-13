package spells;

import entities.Character;

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
		Element elem = to.getElementType();

		double dmg = 10;
		if (element.getStrong() == elem)
			dmg *= 1.5;
		else if (element.getWeak() == elem)
			dmg *= 0.5;

		to.receiveDamage((int) dmg);
	}

	@Override
	public String toString() {
		return super.toString() + " with " + element;
	}

	/**
	 * Gets the elements.
	 *
	 * @return the elements
	 */
	public Element getElement() {
		return element;
	}
}
