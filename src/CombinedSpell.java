public class CombinedSpell extends Spell {
	private Spell s;

	public CombinedSpell(Spell spell, Element element){
		super(element);
		this.s = spell;
	}

	public void effect(){
		super.effect();
		// TODO: effect depending on the second element
	}
}
