package game.spells;

import game.entities.Character;
import game.Element;

import java.util.LinkedList;
import java.util.List;

/**
 * The ElementSpellDecorator implement a decorator on the spell which add an
 * element on it.
 *
 * It's only possible to have 1 unique element by spell.
 */
public class ElementSpellDecorator extends SpellDecorator {

	private final Element element;
	private final int manaCost;

	public ElementSpellDecorator(Spell spell, Element element, int manaCost) {
		super(spell);
		if (!isValid(this))
			throw new IllegalArgumentException("[ERROR] The spell " + spell + " can't add " + element);
		this.element = element;
		this.manaCost = manaCost;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getManaCost() {
		return manaCost + super.getManaCost();
	}

	/**
	 * A spell can only have 1 ElementSpellDecorator with the same element.
	 * It's impossible to have 2 same element in one spell.
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
