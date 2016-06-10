package entities;


import java.util.Random;

interface Item {
	Random random = new Random();


	String getName();

	default int modifyMinHit(int baseMin) {
		return baseMin;
	}

	default int modifyMaxHit(int baseMax) {
		return baseMax;
	}

	default int modifyHealthPoints(int baseHP) {
		return baseHP;
	}

	default boolean isWeapon() {
		return false;
	}

	default boolean isArmor() {
		return false;
	}

	default int handsNeeded() {
		return 0;
	}

	enum Weapon implements Item {
		gourdin("Gourdin"),
		epee("Épée"),
		dague("Dague"),
		hache("Hache"),
		arc("Arc") {
		};

		private String name;

		Weapon(String name) {
			this.name = name;
		}

		public static Item getRandomItem() {
			return values()[random.nextInt(values().length)];
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public boolean isWeapon() {
			return true;
		}

	}
}
