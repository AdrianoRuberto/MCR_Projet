public class Player {
	private String name;
	private int level;
	private int experience;


	public Player(String name, int level, int experience) {
		this.name = name;
		this.level = level;
		this.experience = experience;
	}

	public String toString(){
		return String.format("%s: Level %d, %dxp", name, level, experience);
	}
}
