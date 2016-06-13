import entities.GearedMonster;
import entities.Monster;
import entities.Player;
import spells.ConcreteSpell;
import spells.Element;
import spells.ElementSpellDecorator;
import spells.Spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ma {
	private Player player;
	private boolean running;
	private Scanner scanner;
	private Spell spell;

	public Ma() {
		scanner = new Scanner(System.in);
		System.out.println("Welcome to Mā ! You will fight against evil monsters using your powerful magics !");
		System.out.print("Enter your magician name: ");
		String name = scanner.nextLine();
		player = new Player(name);
		running = true;
	}

	public static void main(String[] args) {
		Ma game = new Ma();
		game.start();
	}

	public void start() {
		printMenu();
		while (running) {
			Monster monster = GearedMonster.generateGearedMonster(player.level());
			System.out.printf("A wild %s appears ! What are you going to do ?\n", monster.getName());
			while (monster.isAlive()) {
				do {
					System.out.print("> ");
				} while (!firstPhase());

				if (!running)
					break;

				do {
					System.out.print("> ");
				} while (!secondPhase(monster));
				// Monster play
				if (monster.isAlive())
					player.receiveDamage(monster.hit());
			}
			System.out.println("You killed " + monster.getName());
		}
	}

	/**
	 * First phase of the fight.
	 * The player can use commands :
	 * - help
	 * - quit
	 * - menu
	 * - prepare
	 *
	 * @return true if the first phase is finished
	 */
	public boolean firstPhase() {
		List<Command> commands = parse(scanner.nextLine());

		switch (commands.get(0)) {
			case help:
				System.out.println(commands.get(1).helpText);
				return false;
			case menu:
				printMenu();
				return false;
			case quit:
				stop();
				return true;
			case prepare:
				if (commands.size() > 1) {
					commands.remove(0);
					spell = new ConcreteSpell(10, 10);
					for (Command command : commands) {
						spell = new ElementSpellDecorator(spell, command.element);
					}
					System.out.println("You have successfully prepare a spell");
					return true;
				} else {
					System.out.println("Prepare spell command not valid, use help ");
					return false;
				}
			default:
				System.out.println("Invalid command");
				return false;
		}
	}

	/**
	 * The second phase of the fight.
	 *
	 * @return true if the second phase is finished
	 */
	private boolean secondPhase(Monster monster) {
		List<Command> commands = parse(scanner.nextLine());
		switch (commands.get(0)) {
			case cast:
				spell.hit(player, monster);
				return true;
			case alter:
				return false;
			case help:
				System.out.println(commands.get(1).helpText);
				return false;
			case menu:
				printMenu();
				return false;
			case quit:
				stop();
				return true;
			default:
				System.out.println("Invalid command");
				return false;
		}
	}

	/**
	 * Parses a given input into commands.
	 *
	 * @param input the input
	 * @return a list of command
	 */
	public List<Command> parse(String input) {
		try {
			return Arrays.stream(input.split(" "))
			             .map(Command::valueOf)
			             .collect(Collectors.toCollection(ArrayList<Command>::new));
		} catch (IllegalArgumentException e) {
			System.out.println("The command doesn't exist");
			throw e;
		}
	}

	/**
	 * Stop the game
	 */
	public void stop() {
		System.out.println("Bye bye");
		running = false;
	}

	/**
	 * Print the menu
	 */
	public void printMenu() {
		System.out.println("Commands:");
		for (Command c : Command.values()) {
			System.out.printf("%s: %s\n", c.name(), c.description);
		}
	}

	private enum Command {
		fire("Throw a fire spell", "Fire help", Element.FIRE),
		water("Throw a water spell", "Water help", Element.WATER),
		rock("Throw a rock spell", "Rock help", Element.ROCK),
		leaf("Throw a leaf spell", "leaf help", Element.LEAF),
		thunder("Throw a thunder spell", "Thunder help", Element.THUNDER),
		prepare("Prepare a spell", ""),
		cast("Cast a prepared spell", ""),
		alter("Alter a spell", ""),
		menu("Displays the menu ", ""),
		help("Display the help. ex: 'help fire'", ""),
		quit("Quit the Mā", "");
		String description;
		String helpText;
		Element element;

		Command(String description, String help) {
			this.description = description;
			this.helpText = help;
		}

		Command(String description, String help, Element element) {
			this(description, help);
			this.element = element;
		}
	}

}
