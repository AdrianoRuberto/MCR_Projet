package spells;

import entities.Monster;

public class CombinedSpell extends Spell {
	private Spell spell;

	public CombinedSpell(Spell spell, Element element){
		super(element);
		this.spell = spell;
	}

	public int hit(Monster m){
		int mana = super.hit(m);
		spell.hit(m);
		return mana + element.manaCost();
	}
}
