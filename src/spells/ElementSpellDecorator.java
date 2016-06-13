package spells;

import entities.Character;

import java.util.LinkedList;
import java.util.List;

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
		if (!isValid(spell))
			throw new IllegalArgumentException("Can't add the decorator");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getManaCost() {
		return 10 + super.getManaCost();
	}

	/**
	 * A spell can only have 1 ElementSpellDecorator with the same element.
	 * It's impossible to have 2 same element in the spell.
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(Spell spell) {
		Spell tmp = spell;
		List<Element> elements = new LinkedList<>();
		while (true) {
			if (tmp instanceof ElementSpellDecorator) {
				Element e = ((ElementSpellDecorator) tmp).element;
				if (elements.contains(e))
					return false;
				elements.add(e);
			}

			if (tmp instanceof SpellDecorator) {
				tmp = ((SpellDecorator) tmp).spell;
			} else {
				return true;
			}
		}
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
