package entities;

import spells.Element;

public abstract class MonsterDecorator extends Monster {
	protected Monster m;

	public MonsterDecorator(String name, int level, int healthPoints, Monster m, Element elem) {
		super(name, level, healthPoints, elem);
		this.m = m;
	}
}
