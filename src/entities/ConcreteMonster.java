package entities;

import spells.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.stream.Collectors;

public class ConcreteMonster extends Monster {
	private int avgDamage, rngDamage;

	public ConcreteMonster(String name, int level, int healthPoints, int avgDamage, int rngDamage) {
		super(name, level, healthPoints);
		this.avgDamage = avgDamage;
		this.rngDamage = rngDamage;

	}

	@Override
	public int minHit() {
		return (int) (avgDamage - rngDamage/2.0);
	}

	@Override
	public int maxHit() {
		return (int) (avgDamage + rngDamage/2.0);
	}

	public static Monster generateConcreteMonster(int level) { // TODO: replace magic values by constants
		Random r = new Random();
		MonsterType type = MonsterType.values()[r.nextInt(MonsterType.values().length)];
		int monsterLevel = Math.max(1, level+(r.nextInt(2)-1)); // player level ± 1
		int hp = (int) (type.baseHP + (level)%(double)type.baseHP);
		int baseDamage = type.baseDamage + r.nextInt(monsterLevel);
		int rngDamage = Math.max(2, r.nextInt(type.baseDamage));
		return new ConcreteMonster(type.name, monsterLevel, hp, baseDamage, rngDamage);
	}

	private enum MonsterType {
		anguille_geante("Anguille géante", Element.THUNDER),
		araignee_rouge("Araignée rouge", Element.FIRE),
		araignee_geante("Araignée géante", Element.NORMAL),
		auroch("Auroch", Element.ROCK),
		basilic("Basilic", Element.LEAF),
		calmar("Calmar", Element.WATER),
		chauve_souris("Chauve-souris géante", Element.ROCK),
		chimere("Chimère", Element.THUNDER),
		cockatrice("Cockatrice", Element.THUNDER),
		crocodile("Crocodile", Element.WATER),
		cyclope("Cyclope", Element.ROCK),
		demon_ombres("Démon des ombres", Element.NORMAL),
		diablotin("Diablotin", Element.FIRE),
		dragon_noir("Dragon noir", Element.FIRE),
		dragon_dairain("Dragon d'airain", Element.FIRE),
		dryade("Dryade", Element.WATER),
		efrit("Efrit", Element.FIRE),
		elementaire_eau("Élementaire d'eau", Element.WATER),
		elementaire_feu("Élementaire de feu", Element.FIRE),
		elementaire_terre("Élementaire de terre", Element.ROCK),
		elementaire_vegetal("Élementaire végétal", Element.LEAF),
		elementaire_foudre("Élementaire de foudre", Element.THUNDER),
		fantome("Fantôme", Element.NORMAL),
		fourmi_geante("Fourmi géante", Element.LEAF),
		geant_pierre("Géant de pierre", Element.ROCK),
		geant_tempete("Géant des tempêtes", Element.THUNDER),
		gnoll("Gnoll", Element.NORMAL),
		gobelin("Gobelin", Element.NORMAL),
		gobelours("Gobelours", Element.LEAF),
		gorgone("Gorgone", Element.ROCK),
		gorille("Gorille", Element.LEAF),
		grenouille_venimeuse("Grenouille venimeuse", Element.LEAF),
		griffon("Griffon", Element.THUNDER),
		grizzly("Grizzly", Element.NORMAL),
		harpie("Harpie", Element.NORMAL),
		hydre("Hydre", Element.WATER),
		homme_poisson("Homme-poisson", Element.WATER),
		kobold("Kobold", Element.ROCK),
		kraken("Kraken", Element.WATER),
		licorne("Licorne", Element.NORMAL, "licorne.utf8"),
		loup_garou("Loup-garou", Element.NORMAL),
		meduse("Méduse", Element.WATER),
		minotaure("Minotaure", Element.ROCK),
		molosse_infernal("Molosse infernal", Element.FIRE),
		necrophage("Nécrophage", Element.NORMAL),
		ogre("Ogre", Element.ROCK),
		ours_sanguinaire("Ours sanguinaire", Element.NORMAL),
		phoenix("Phoenix", Element.FIRE),
		pixie("Pixie", Element.THUNDER),
		rat_garou("Rat-garou", Element.NORMAL),
		requin_sanguinaire("Requin sanguinaire", Element.WATER),
		squelette("Squelette", Element.NORMAL),
		succube("Succube", Element.FIRE),
		tertre_errant("Tertre errant", Element.LEAF),
		vampire("Vampire", Element.NORMAL),
		zombi("Zombi", Element.NORMAL)
		;

		private final int baseHP;
		private final String name;
		private final Element elem;
		private final int baseDamage;
		private String sprite;

		public static final String spritesPath = "sprites";


		MonsterType(String name, Element elem){
			this(name, elem, 10, 5);
		}

		MonsterType(String name, Element elem, String spritePath) {
			this(name, elem);

			try {
				File spriteFile = new File(spritesPath + "/" + spritePath);
				BufferedReader fileReader = new BufferedReader(new FileReader(spriteFile));
				sprite = fileReader.lines().collect(Collectors.joining("\n"));
			} catch (FileNotFoundException e) {
				System.out.println("Could not find the sprite ascii image while searching in this path:");
				System.out.println(spritesPath +"/"+spritePath);
			}
		}
		MonsterType(String name, Element elem, int baseHP, int baseDamage) {
			this.name = name;
			this.elem = elem;
			this.baseHP = baseHP;
			this.baseDamage = baseDamage;
		}

		public String sprite(){
			return sprite;
		}
	}

}
