package entities;

public class Player extends Character {
	private int level;

	public Player(String name) {
		super(name, 1, 10);
		this.level = 1;
	}

	public String toString() {
		return String.format("%s: Level %d", name, level);
	}

	/**
	 * Gets the level of the player.
	 *
	 * @return the level of the player
	 */
	public int getLevel() {
		return level;
	}
}
