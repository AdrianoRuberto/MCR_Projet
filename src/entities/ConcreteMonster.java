package entities;

import utils.Element;
import utils.AsciiImages;
import utils.TerminalUtils;

import java.io.FileNotFoundException;
import java.util.Random;


/**
 * The class representing actual monsters. It has a static generator that gives a random monster.
 */
public class ConcreteMonster extends Monster {
	private int avgDamage, rngDamage;

	/**
	 * Concrete monster constructor
	 * @param name the monster's name
	 * @param level the monster's level
	 * @param healthPoints the monster's hp
	 * @param avgDamage the monster's average damage
	 * @param rngDamage the monster's damage range (effective damage will be from avgDamage-(rngDamage/2) to avgDamage+(rngDamage/2)
	 * @param elem the monster's element type
	 */
	public ConcreteMonster(String name, int level, int healthPoints, int avgDamage, int rngDamage, Element elem) {
		super(name, level, healthPoints, elem);
		this.avgDamage = avgDamage;
		this.rngDamage = rngDamage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int minHit() {
		return (int) (avgDamage - rngDamage / 2.0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int maxHit() {
		return (int) (avgDamage + rngDamage / 2.0);
	}

	/**
	 * Gives a random monster that is at a level near the given level
	 * @param level a level near the monster's level. Typically the player's level.
	 * @return A random monster
	 */
	public static Monster generateConcreteMonster(int level) {
		Random r = new Random();
		MonsterType type = MonsterType.values()[r.nextInt(MonsterType.values().length)];
		int monsterLevel = Math.max(1, level + (r.nextInt(2) - 1)); // player getLevel ± 0,1
		int hp = (int) (type.baseHP + (level) % (double) type.baseHP);
		int baseDamage = type.baseDamage + r.nextInt(monsterLevel);
		int rngDamage = Math.max(2, r.nextInt(type.baseDamage));
		return new ConcreteMonster(type.name, monsterLevel, hp, baseDamage, rngDamage, type.elem) {
			@Override
			public String toString() {
				return TerminalUtils.colorize(type.sprite, type.elem) + "\n" + super.toString();
			}
		};
	}

	/**
	 * The monsters data
	 */
	private enum MonsterType {
		anguille_geante("Giant eel", Element.THUNDER, "anguille.ascii", 0),
		araignee_rouge("Red spider", Element.FIRE, "araignee.ascii", 4),
		araignee_geante("Giant spider", Element.NORMAL, "araignee.ascii", 4),
		auroch("Auroch", Element.ROCK, "auroch.ascii", 0),
		basilic("Basilic", Element.LEAF, "basilic.ascii", 0),
		calmar("Calmar", Element.WATER, "calmar.ascii", 8),
		chauve_souris("Giant bat", Element.ROCK, "chauve_souris.ascii", 0),
		crocodile("Crocodile", Element.WATER, "crocodile.ascii", 0),
		cyclope("Cyclops", Element.ROCK, "cyclope.ascii", 2),
		demon("Demon", Element.NORMAL, "demon.ascii", 2),
		diablotin("Imp", Element.FIRE, "diablotin.ascii", 1),
		dragon_noir("Black dragon", Element.FIRE, "dragon.ascii", 0),
		dragon_glace("Ice dragon", Element.WATER, "dragon.ascii", 0),
		efrit("Efrit", Element.FIRE, "efrit.ascii", 0),
		elementaire_eau("Water elemental", Element.WATER, "elementaire_eau.ascii", 0),
		elementaire_feu("Fire elemental", Element.FIRE, "elementaire_feu.ascii", 0),
		elementaire_terre("Rock elemental", Element.ROCK, "elementaire_roche.ascii", 0),
		elementaire_vegetal("Leaf elemental", Element.LEAF, "elementaire_plante.ascii", 0),
		elementaire_foudre("Thunder elemental", Element.THUNDER, "elementaire_foudre.ascii", 0),
		fantome("Ghost", Element.NORMAL, "fantome.ascii", 0),
		fourmi_geante("Giant ant", Element.LEAF, "fourmi.ascii", 0),
		geant_pierre("Giant of mountain", Element.ROCK, "geant.ascii", 2),
		geant_tempete("Giant of storm", Element.THUNDER, "geant.ascii", 2),
		gobelin("Gobelin", Element.NORMAL, "gobelin.ascii", 2),
		gobelours("Bugbear", Element.LEAF, "ours.ascii", 2),
		gorille("Gorilla", Element.LEAF, "gorille.ascii", 0),
		grenouille_venimeuse("Poison frog", Element.LEAF, "grenouille.ascii", 0),
		griffon("Griffin", Element.THUNDER, "griffon.ascii", 0),
		grizzly("Grizzly", Element.NORMAL, "ours.ascii", 0),
		harpie("Harpy", Element.NORMAL, "harpie.ascii", 1),
		hydre("Hydra", Element.WATER, "hydre.ascii", 0),
		kraken("Kraken", Element.WATER, "kraken.ascii", 0),
		licorne("Unicorn", Element.NORMAL, "licorne.ascii", 0),
		loup_garou("Werewolf", Element.NORMAL, "loup_garou.ascii", 2),
		minotaure("Minotaur", Element.ROCK, "minotaure.ascii", 2),
		molosse_infernal("Hellhound", Element.FIRE, "molosse.ascii", 0),
		ogre("Ogre", Element.ROCK, "ogre.ascii", 2),
		ours_sanguinaire("Bloody bear", Element.NORMAL, "ours.ascii", 0),
		phoenix("Phoenix", Element.FIRE, "phoenix.ascii", 0),
		pixie("Pixie", Element.THUNDER, "pixie.ascii", 1),
		rat_garou("Skaven", Element.NORMAL, "rat_garou.ascii", 2),
		requin_sanguinaire("Angry shark", Element.WATER, "requin.ascii", 0),
		squelette("Skeletton", Element.NORMAL, "squelette.ascii", 2),
		succube("Succubus", Element.FIRE, "succube.ascii", 1),
		tertre_errant("Wandering Ent", Element.LEAF, "elementaire_plante.ascii", 2),
		vampire("Vampire", Element.NORMAL, "vampire.ascii", 2),
		zombie("Zombie", Element.NORMAL, "zombie.ascii", 2);

		private final int baseHP;
		private final String name;
		private final Element elem;
		private final int baseDamage;
		private final int nbHands;
		private String sprite;

		public static final String spritesFolder = "sprites";

		/**
		 * MonsterType constructor
		 * @param name the name of the monster
		 * @param baseHP the healthpoints of the monster
		 * @param baseDamage the base damage points of the monster
		 * @param elem the element corresponding to the monster
		 * @param spritePath the ascii sprite
		 */
		MonsterType(String name, int baseHP, int baseDamage, Element elem, String spritePath, int nbHands) {
			this.name = name;
			this.elem = elem;
			this.baseHP = baseHP;
			this.baseDamage = baseDamage;
			this.nbHands = nbHands;
			try {
				this.sprite = AsciiImages.parseAscii(spritesFolder+"/"+spritePath);
			} catch (FileNotFoundException e) {
				System.out.println("Could not find the ascii file");
			}
		}

		/**
		 * MonsterType constructor
		 * @param name the name of the monster
		 * @param elem the element corresponding to the monster
		 * @param spritePath the ascii sprite
		 */
		MonsterType(String name, Element elem, String spritePath, int nbHands) {
			this(name, 40, 5, elem, spritePath, nbHands);
		}

	}


}
