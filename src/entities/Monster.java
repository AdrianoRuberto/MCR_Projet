package entities;

import java.util.Random;

public abstract class Monster extends Character {
	private Random random;


	public Monster(String name, int level, int healthPoints) {
		super(name, level, healthPoints);
		random = new Random((name+level+healthPoints).hashCode());
	}

	public final int hit() {
		int min = minHit();
		int max = maxHit();
		return random.nextInt(max-min)+min;
	}

	public abstract int minHit();
	public abstract int maxHit();

	public int equippedWeapons() {
		return 0;
	}
	public int maxEquippedWeapons() {
		return 1;
	}

	public boolean hasArmor() {
		return false;
	}

	public String toString() {
		return String.format("%s, lvl:%d, hp:%d, damage:%d-%d", this.getName(), level, healthPoints, minHit(), maxHit());
	}

	public static abstract class MonsterGenerator {
		public abstract Monster generateMonster(int level);
	}

}
