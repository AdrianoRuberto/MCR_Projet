package entities;

public class GearedMonster extends MonsterDecorator {
	private Monster monster;
	private Item item;

	public GearedMonster(Monster m, Item i) {
		super(
				m.name,
				m.level,
				i.modifyHealthPoints(m.healthPoints),
				m,
				m.getElementType()
		);

		if (m.equippedWeapons() + i.handsNeeded() > maxEquippedWeapons()) {
			throw new RuntimeException("The monster can't wield this weapon, it has already too many");
		}
		else if (i.isArmor() && m.hasArmor()) {
			throw new RuntimeException("A monster can only equip one armour");
		}
		this.monster = m;
		this.item = i;
	}

	@Override
	public String getName() {
		return String.format("%s, avec %s", name, item.getName());
	}

	@Override
	public int minHit() {
		return item.modifyMinHit(monster.minHit());
	}

	@Override
	public int maxHit() {
		return item.modifyMaxHit(monster.maxHit());
	}

	public static Monster generateGearedMonster(int level) {
		return new GearedMonster(ConcreteMonster.generateConcreteMonster(level), Item.Weapon.getRandomItem());
	}

	public int equippedWeapons() {
		return monster.equippedWeapons() + item.handsNeeded();
	}
}
