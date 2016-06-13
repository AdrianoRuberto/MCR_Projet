package spells;

import entities.Character;

public class ConcreteSpell extends Spell {

	private int damage;
	private int manaCost;

	public ConcreteSpell(int damage, int manaCost) {
		this.damage = damage;
		this.manaCost = manaCost;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hit(Character from, Character to) {
		to.receiveDamage(damage);
		from.removeMana(getManaCost());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getManaCost() {
		return manaCost;
	}
}