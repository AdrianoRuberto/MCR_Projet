package entities;

/**
 * Represents a monster decorator. Adds functionalities to them.
 */
public abstract class MonsterDecorator extends Monster {
	protected Monster m;

	/**
	 * Monster decorator constructor
	 * @param m the monster to decorate
	 */
	public MonsterDecorator(Monster m) {
		super(m.name, m.level, m.healthPoints, m.getElementType());
		this.m = m;
	}
}
