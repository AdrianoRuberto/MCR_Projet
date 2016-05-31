import entities.Monster;
import entities.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ma {
	private Player player;
	private boolean running;
	private Scanner scanner;

	public Ma() {
		scanner = new Scanner(System.in);
		System.out.println("Welcome to Mā ! You will fight against evil monsters using your powerful magics !");
		System.out.print("Enter your magician name: ");
		String name = scanner.nextLine();
		player = new Player(name);
		running = true;
	}

	public void start() {
		Monster monster = generateMonster();
		System.out.printf("A wild %s appears ! What are you going to do ?\n", monster.getName());
		printMenu();
		readCommand();
	}

	public void stop() {
		running = false;
	}

	private Monster generateMonster() {
		return new Monster("Goblin", 1, 10, 2, 1);
	}

	private void readCommand() {
		String input = scanner.nextLine();
		try {
			List<Command> commands = Arrays.stream(input.split("-")).map(Command::valueOf).collect(Collectors.toList());
			if (commands.contains(Command.menu)){
				printMenu();
				return;
			}
		} catch (IllegalArgumentException e){
			System.out.println("Command not recognized");
		}

	}

	public void printState() {
		System.out.println(player);
	}

	public void printMenu() {
		System.out.println("Commands:");
		for (Command c: Command.values()) {
			System.out.printf("%s: %s\n", c.name(), c.description);
		}
	}

	public static void main(String[] args) {
		Ma game = new Ma();
		game.start();
	}


	private enum Command {
		fire("Throw a fire spell"),
		ice("Throw an ice spell"),
		earth("Throw a telluric spell"),
		wind("Throw an air spell"),
		menu("Displays the menu ");

		String description;
		Command(String description) {
			this.description = description;
		}
	}
}
