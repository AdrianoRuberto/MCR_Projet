import entities.Monster;
import entities.Player;
import spells.CombinedSpell;
import spells.Element;
import spells.Spell;

import java.util.ArrayList;
import java.util.Arrays;
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
			ArrayList<Command> commands = Arrays.stream(input.split(" ")).map(Command::valueOf).collect(Collectors.toCollection(ArrayList<Command>::new));
			if (commands.contains(Command.menu)){

				switch (commands.get(0)){

					case menu:
						printMenu();
						break;

					case quit:
						stop();
						break;

					case help:
						System.out.println(commands.get(1).helpText);
						break;

					default:
						Spell spell = new Spell(commands.get(0).element);

						for (int i = 1; i < commands.size(); ++i){
							spell = new CombinedSpell(spell, commands.get(i).element);
						}
				}
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
		fire("Throw a fire spell", "Fire help", Element.FIRE),
		water("Throw an water spell", "Water help", Element.WATER),
		rock("Throw a rock spell", "Rock help", Element.ROCK),
		leaf("Throw an leaf spell", "leaf help", Element.LEAF),
		thunder("Throw a thunder spell", "Thunder help", Element.THUNDER),
		menu("Displays the menu ", ""),
		help("Display the help. ex: 'help fire'", ""),
		quit("Quit the Mā" ,"");
		String description;
		String helpText;
		Element element;
		Command(String description, String help) {
			this.description = description;
			this.helpText = help;
		}
		Command(String description, String help, Element element){
			this(description, help);
			this.element = element;
		}
	}

}
