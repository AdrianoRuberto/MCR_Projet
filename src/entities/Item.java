package entities;


import java.util.Random;

/**
 * Represent an item a monster can wear to boost its stats
 */
interface Item {
	/**
	 * Gets the name of the item
	 * @return a String of the name
	 */
	String getName();

	/**
	 * Change the minimal hit the monster will do with this item
	 * @param baseMin the base min hit
	 * @return the new min hit
	 */
	default int modifyMinHit(int baseMin) {
		return baseMin;
	}

	/**
	 * Change the maximal hit the monster will do with this item
	 * @param baseMax the base max hit
	 * @return the new max hit
	 */
	default int modifyMaxHit(int baseMax) {
		return baseMax;
	}

	/**
	 * Change the base health points the monster will have with this item
	 * @param baseHP the base health points number
	 * @return the new health points number
	 */
	default int modifyHealthPoints(int baseHP) {
		return baseHP;
	}

	/**
	 * Gives how many hands are needed to use the item
	 * @return hands neeeded for the weapon to be used
	 */
	default int handsNeeded() {
		return 0;
	}

	/**
	 * A weapon is a type of item used to boost the attack stats of monsters.
	 */
	enum Weapon implements Item {
		dagger("Dagger") {
			@Override
			public int modifyMaxHit(int baseMax) {
				return baseMax+2;
			}
		},
		sword("Sword") {
			@Override
			public int modifyMinHit(int baseMin) {
				return baseMin+1;
			}
			@Override
			public int modifyMaxHit(int baseMax) {
				return baseMax+1;
			}
		},
		shortbow("Shortbow") {
			@Override
			public int modifyMinHit(int baseMin) {
				return baseMin+1;
			}
			@Override
			public int modifyMaxHit(int baseMax) {
				return baseMax+1;
			}
		},
		axe("Axe"){
			@Override
			public int modifyMinHit(int baseMin) {
				return Math.min(1,baseMin-1);
			}
			@Override
			public int modifyMaxHit(int baseMax) {
				return baseMax+2;
			}
		},
		battleaxe("Battleaxe") {
			@Override
			public int modifyMinHit(int baseMin) {
				return Math.min(1,baseMin-2);
			}
			@Override
			public int modifyMaxHit(int baseMax) {
				return baseMax+2;
			}
		};

		private String name;

		/**
		 * Constructs the weapon
		 * @param name the weapon's name
		 */
		Weapon(String name) {
			this.name = name;
		}

		/**
		 * Random getter utility
		 * @return a random weapon
		 */
		public static Item getRandomItem() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getName() {
			return name;
		}

	}
}
