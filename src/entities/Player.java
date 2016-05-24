package entities;

import entities.Character;

public class Player extends Character {
	private int experience;


	public Player(String name) {
		super(name, 1, 10);
		this.experience = 0;
	}

	public String toString(){
		return String.format("%s: Level %d - %dXP", name, level, experience);
	}
}
