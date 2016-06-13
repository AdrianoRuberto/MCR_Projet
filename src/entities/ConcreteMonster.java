package entities;

import spells.Element;
import utils.TerminalUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * The class representing actual monsters. It has a static generator that gives a random monster.
 */
public class ConcreteMonster extends Monster {
	private int avgDamage, rngDamage;
	private Element element;

	public ConcreteMonster(String name, int level, int healthPoints, int avgDamage, int rngDamage, Element elem) {
		super(name, level, healthPoints);
		this.avgDamage = avgDamage;
		this.rngDamage = rngDamage;
		this.element = elem;
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
	 * Gives a random monster that it at a level near the given level
	 * @param level a level near the monster's level. Typically the player's level.
	 * @return A random monster
	 */
	public static Monster generateConcreteMonster(int level) { // TODO: replace magic values by constants
		Random r = new Random();
		MonsterType type = MonsterType.values()[r.nextInt(MonsterType.values().length)];
		int monsterLevel = Math.max(1, level + (r.nextInt(2) - 1)); // player level ± 1
		int hp = (int) (type.baseHP + (level) % (double) type.baseHP);
		int baseDamage = type.baseDamage + r.nextInt(monsterLevel);
		int rngDamage = Math.max(2, r.nextInt(type.baseDamage));
		return new ConcreteMonster(type.name, monsterLevel, hp, baseDamage, rngDamage, type.elem) {
			@Override
			public void print() {
				TerminalUtils.printWithElement(System.out, type.sprite, type.elem);
				System.out.println(this.toString());
			}
		};
	}

	@Override
	public void print() {
		TerminalUtils.printWithElement(System.out, this.toString(), element);
	}

	/**
	 * The monsters data
	 */
	private enum MonsterType {
		anguille_geante("Giant eel", Element.THUNDER, "anguille.ascii"),
		araignee_rouge("Red spider", Element.FIRE, "araignee.ascii"),
		araignee_geante("Giant spider", Element.NORMAL, "araignee.ascii"),
		auroch("Auroch", Element.ROCK, "auroch.ascii"),
		basilic("Basilic", Element.LEAF, "basilic.ascii"),
		calmar("Calmar", Element.WATER, "calmar.ascii"),
		chauve_souris("Giant bat", Element.ROCK, "chauve_souris.ascii"),
		crocodile("Crocodile", Element.WATER, "crocodile.ascii"),
		cyclope("Cyclops", Element.ROCK, "cyclope.ascii"),
		demon("Demon", Element.NORMAL, "demon.ascii"),
		diablotin("Imp", Element.FIRE, "diablotin.ascii"),
		dragon_noir("Black dragon", Element.FIRE, "dragon.ascii"),
		dragon_glace("Ice dragon", Element.WATER, "dragon.ascii"),
		efrit("Efrit", Element.FIRE, "efrit.ascii"),
		elementaire_eau("Water elemental", Element.WATER, "elementaire_eau.ascii"),
		elementaire_feu("Fire elemental", Element.FIRE, "elementaire_feu.ascii"),
		elementaire_terre("Rock elemental", Element.ROCK, "elementaire_roche.ascii"),
		elementaire_vegetal("Leaf elemental", Element.LEAF, "elementaire_plante.ascii"),
		elementaire_foudre("Thunder elemental", Element.THUNDER, "elementaire_foudre.ascii"),
		fantome("Ghost", Element.NORMAL, "fantome.ascii"),
		fourmi_geante("Giant ant", Element.LEAF, "fourmi.ascii"),
		geant_pierre("Giant of mountain", Element.ROCK, "geant.ascii"),
		geant_tempete("Giant of storm", Element.THUNDER, "geant.ascii"),
		gobelin("Gobelin", Element.NORMAL, "gobelin.ascii"),
		gobelours("Bugbear", Element.LEAF, "ours.ascii"),
		gorille("Gorilla", Element.LEAF, "gorille.ascii"),
		grenouille_venimeuse("Poison frog", Element.LEAF, "grenouille.ascii"),
		griffon("Griffin", Element.THUNDER, "griffon.ascii"),
		grizzly("Grizzly", Element.NORMAL, "ours.ascii"),
		harpie("Harpy", Element.NORMAL, "harpie.ascii"),
		hydre("Hydra", Element.WATER, "hydre.ascii"),
		kraken("Kraken", Element.WATER, "kraken.ascii"),
		licorne("Unicorn", Element.NORMAL, "licorne.ascii"),
		loup_garou("Werewolf", Element.NORMAL, "loup_garou.ascii"),
		minotaure("Minotaur", Element.ROCK, "minotaure.ascii"),
		molosse_infernal("Hellhound", Element.FIRE, "molosse.ascii"),
		ogre("Ogre", Element.ROCK, "ogre.ascii"),
		ours_sanguinaire("Bloody bear", Element.NORMAL, "ours.ascii"),
		phoenix("Phoenix", Element.FIRE, "phoenix.ascii"),
		pixie("Pixie", Element.THUNDER, "pixie.ascii"),
		rat_garou("Skaven", Element.NORMAL, "rat_garou.ascii"),
		requin_sanguinaire("Angry shark", Element.WATER, "requin.ascii"),
		squelette("Skeletton", Element.NORMAL, "squelette.ascii"),
		succube("Succubus", Element.FIRE, "succube.ascii"),
		tertre_errant("Wandering Ent", Element.LEAF, "elementaire_plante.ascii"),
		vampire("Vampire", Element.NORMAL, "vampire.ascii"),
		zombie("Zombie", Element.NORMAL, "zombie.ascii");

		private final int baseHP;
		private final String name;
		private final Element elem;
		private final int baseDamage;
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
		MonsterType(String name, int baseHP, int baseDamage, Element elem, String spritePath) {
			this.name = name;
			this.elem = elem;
			this.baseHP = baseHP;
			this.baseDamage = baseDamage;

			try {
				File spriteFile = new File(spritesFolder + "/" + spritePath);
				BufferedReader fileReader = new BufferedReader(new FileReader(spriteFile));
				sprite = fileReader.lines().collect(Collectors.joining("\n"));
			} catch (FileNotFoundException e) {
				System.out.println("Could not find the sprite ascii image while searching in this path:");
				System.out.println(spritesFolder + "/" + spritePath);
			}
		}

		/**
		 * MonsterType constructor
		 * @param name the name of the monster
		 * @param elem the element corresponding to the monster
		 * @param spritePath the ascii sprite
		 */
		MonsterType(String name, Element elem, String spritePath) {
			this(name, 10, 5, elem, spritePath);
		}

	}


}
