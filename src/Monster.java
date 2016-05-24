import java.util.Random;

public class Monster extends Character {
	private Random random;
	private int avgDamage;
	private int rngDamage;

	public Monster(String name, int level, int hitpoints, int avgDamage, int rngDamage) {
		super(name, level, hitpoints);
		random = new Random(); // TODO: determine the random seed
		this.avgDamage = avgDamage;
		this.rngDamage = rngDamage;
	}

	public int hit() {
		return random.nextInt(rngDamage)-rngDamage/2+avgDamage;
	}

	public void receiveDamage(int damage) {
		hitpoints -= damage;
	}
}
