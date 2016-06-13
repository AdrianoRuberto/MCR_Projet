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


	default boolean isArmor() {
		return false;
	}

	default int handsNeeded() {
		return 0;
	}

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

	}
}
