import java.util.List;
import java.util.Scanner;

public class Mā {
	private Player player;
	private boolean running;

	public Mā() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Mā ! You will fight against evil monsters using your powerful magics !");
		System.out.print("Enter your magician name: ");
		String name = scanner.nextLine();
		player = new Player(name);
		running = true;
	}

	public void start() {
		Monster monster = generateMonster();
		System.out.printf("A wild %s appears ! What are you going to do ?", monster.getName());
	}

	public void stop() {
		running = false;
	}

	public void printState() {
		System.out.println(player);
	}

	private Monster generateMonster() {
		return new Monster("Goblin", 1, 10, 2, 1);
	}

	public static void main(String[] args) {
		Mā game = new Mā();
		game.start();
	}
}
