package entities;

import spells.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
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
		anguille_geante("Anguille géante", Element.THUNDER, "anguille.ascii"),
		araignee_rouge("Araignée rouge", Element.FIRE, "araignee.ascii"),
		araignee_geante("Araignée géante", Element.NORMAL, "araignee.ascii"),
		auroch("Auroch", Element.ROCK, "auroch.ascii"),
		basilic("Basilic", Element.LEAF, "basilic.ascii"),
		calmar("Calmar", Element.WATER, "calmar.ascii"),
		chauve_souris("Chauve-souris géante", Element.ROCK, "chauve_souris.ascii"),
		crocodile("Crocodile", Element.WATER, "crocodile.ascii"),
		cyclope("Cyclope", Element.ROCK, "cyclope.ascii"),
		demon("Démon", Element.NORMAL, "demon.ascii"),
		diablotin("Diablotin", Element.FIRE, "diablotin.ascii"),
		dragon_noir("Dragon noir", Element.FIRE, "dragon.ascii"),
		dragon_glace("Dragon de glace", Element.WATER, "dragon.ascii"),
		efrit("Efrit", Element.FIRE, "efrit.ascii"),
		elementaire_eau("Élementaire d'eau", Element.WATER, "elementaire_eau.ascii"),
		elementaire_feu("Élementaire de feu", Element.FIRE, "elementaire_feu.ascii"),
		elementaire_terre("Élementaire de terre", Element.ROCK, "elementaire_roche.ascii"),
		elementaire_vegetal("Élementaire végétal", Element.LEAF, "elementaire_plante.ascii"),
		elementaire_foudre("Élementaire de foudre", Element.THUNDER, "elementaire_foudre.ascii"),
		fantome("Fantôme", Element.NORMAL, "fantome.ascii"),
		fourmi_geante("Fourmi géante", Element.LEAF, "fourmi.ascii"),
		geant_pierre("Géant de pierre", Element.ROCK, "geant.ascii"),
		geant_tempete("Géant des tempêtes", Element.THUNDER, "geant.ascii"),
		gobelin("Gobelin", Element.NORMAL, "gobelin.ascii"),
		gobelours("Gobelours", Element.LEAF, "ours.ascii"),
		gorille("Gorille", Element.LEAF, "gorille.ascii"),
		grenouille_venimeuse("Grenouille venimeuse", Element.LEAF, "grenouille.ascii"),
		griffon("Griffon", Element.THUNDER, "griffon.ascii"),
		grizzly("Grizzly", Element.NORMAL, "ours.ascii"),
		harpie("Harpie", Element.NORMAL, "harpie.ascii"),
		hydre("Hydre", Element.WATER, "hydre.ascii"),
		kraken("Kraken", Element.WATER, "kraken.ascii"),
		licorne("Licorne", Element.NORMAL, "licorne.ascii"),
		loup_garou("Loup-garou", Element.NORMAL, "loup_garou.ascii"),
		minotaure("Minotaure", Element.ROCK, "minotaure.ascii"),
		molosse_infernal("Molosse infernal", Element.FIRE, "molosse.ascii"),
		ogre("Ogre", Element.ROCK, "ogre.ascii"),
		ours_sanguinaire("Ours sanguinaire", Element.NORMAL, "ours.ascii"),
		phoenix("Phoenix", Element.FIRE, "phoenix.ascii"),
		pixie("Pixie", Element.THUNDER, "pixie.ascii"),
		rat_garou("Rat-garou", Element.NORMAL, "rat_garou.ascii"),
		requin_sanguinaire("Requin sanguinaire", Element.WATER, "requin.ascii"),
		squelette("Squelette", Element.NORMAL, "squelette.ascii"),
		succube("Succube", Element.FIRE, "succube.ascii"),
		tertre_errant("Tertre errant", Element.LEAF, "elementaire_plante.ascii"),
		vampire("Vampire", Element.NORMAL, "vampire.ascii"),
		zombie("Zombie", Element.NORMAL, "zombie.ascii")
		;

		private final int baseHP;
		private final String name;
		private final Element elem;
		private final int baseDamage;
		private String sprite;

		public static final String spritesPath = "sprites";



		MonsterType(String name, Element elem, String spritePath) {
			this(name, elem, 10, 5);

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

	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int i = 0;
		while (!Objects.equals(s.next(), "stop")){
			MonsterType m = MonsterType.values()[i++%MonsterType.values().length];
			System.out.println(m.sprite);
			System.out.println(m.name);

		}
	}

}
