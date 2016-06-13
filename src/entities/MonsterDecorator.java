package entities;

public abstract class MonsterDecorator extends Monster {
	protected Monster m;

	public MonsterDecorator(String name, int level, int healthPoints, Monster m) {
		super(name, level, healthPoints);
		this.m = m;
	}
}
