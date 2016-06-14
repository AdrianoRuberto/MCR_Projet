package entities;

import java.util.Random;

/**
 * Decorated Monster with items
 */
public class GearedMonster extends MonsterDecorator {
	private Monster monster;
	private Item item;

	/**
	 * Geared monster constructor
	 * @param m The monster to equip
	 * @param i The item for the monster to use
	 */
	public GearedMonster(Monster m, Item i) {
		super(m);

		if (m.equippedWeapons() + i.handsNeeded() > maxEquippedWeapons()) {
			throw new RuntimeException("The monster can't wield this weapon, it has already too many");
		}
		else if (i.isArmor() && m.hasArmor()) {
			throw new RuntimeException("A monster can only equip one armour");
		}
		this.monster = m;
		this.item = i;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return String.format("%s, with %s", name, item.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int minHit() {
		return item.modifyMinHit(monster.minHit());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int maxHit() {
		return item.modifyMaxHit(monster.maxHit());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxHealthPoints() {
		return item.modifyHealthPoints(monster.getMaxHealthPoints());
	}

	/**
	 * Gives a random monster that is at a level near the given level. Wears a random number of weapons depending of
	 * the monster capacities.
	 * @param level a level near the monster's level. Typically the player's level.
	 * @return A random geared monster
	 */
	public static Monster generateGearedMonster(int level) {
		Monster m = ConcreteMonster.generateConcreteMonster(level);
		if (m.maxEquippedWeapons() > 0) {
			Random random = new Random();
			int nbWeapons = random.nextInt(m.maxEquippedWeapons());
			for (int i = 0; i < nbWeapons; i++) {
				m = new GearedMonster(m, Item.Weapon.getRandomItem());
			}
		}
		return m;
	}

	@Override
	public int equippedWeapons() {
		return monster.equippedWeapons() + item.handsNeeded();
	}

	@Override
	public String toString() {
		return monster.toString() + ", wearing a " + item;
	}
}
