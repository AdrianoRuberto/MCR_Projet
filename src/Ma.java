import entities.GearedMonster;
import entities.Monster;
import entities.Player;
import spells.*;

import java.util.*;
import java.util.stream.Collectors;

public class Ma {
	private Player player;
	private Scanner scanner;
	private Spell spell;
	private Random random;

	public Ma() {
		scanner = new Scanner(System.in);
		System.out.println("Welcome to Mā ! You will fight against evil monsters using your powerful magics !");
		System.out.print("Enter your magician name: ");
		String name = scanner.nextLine();
		player = new Player(name);
		random = new Random();
	}

	public static void main(String[] args) {
		Ma game = new Ma();
		try {
			game.start();
		} catch (InterruptedException e) {
			System.out.println("Unexpected quit !");
		}
	}

	public void start() throws InterruptedException {
		List<Command> commands;
		printMenu();
		while (true) {
			Thread.sleep(random.nextInt(3000) + 1000);
			Monster monster = GearedMonster.generateGearedMonster(player.getLevel());
			System.out.printf("%s appears ! What are you going to do ?\n", monster);

			while (monster.isAlive()) {
				do {
					System.out.print("> ");
					commands = Command.parse(scanner.nextLine());
				} while (!firstPhase(commands));

				do {
					System.out.print("> ");
					commands = Command.parse(scanner.nextLine());
				} while (!secondPhase(monster, commands));

				// Monster play
				if (monster.isAlive())
					player.receiveDamage(monster.hit());
			}

			System.out.println("You killed the " + monster.getName());
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
	public boolean firstPhase(List<Command> commands) {
		if (commands.isEmpty()) return false;

		switch (commands.get(0)) {
			case help:
				if (commands.size() == 2) {
					System.out.println(commands.get(1).helpText);
					return false;
				}
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
					try {
						for (Command command : commands) {
							spell = new ElementSpellDecorator(spell, command.element);
						}
						System.out.println("You have successfully prepare the spell : " + spell);
						return true;
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
						return false;
					}
				}
		}
		System.out.println("Invalid command");
		return false;
	}

	/**
	 * The second phase of the fight.
	 * The player can use the followed commands :
	 * - cast
	 * - alter
	 * - help
	 * - menu
	 * - quit
	 *
	 * @return true if the second phase is finished
	 */
	private boolean secondPhase(Monster monster, List<Command> commands) {
		if (commands.isEmpty()) return false;
		switch (commands.get(0)) {
			case cast:
				System.out.println("Casting " + spell + " on " + monster.getName());
				spell.hit(player, monster);
				return true;
			case alter:
				if (commands.size() == 3) {
					final Element toFind = commands.get(1).element;
					int pos = ((SpellDecorator) spell).getPos(spell, (s -> (s instanceof ElementSpellDecorator && (
							(ElementSpellDecorator) s).getElement() == toFind)));
					try {
						((SpellDecorator) spell).alter(pos, new ElementSpellDecorator(null, commands.get(2).element));
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					System.out.println("The spell is : " + spell);
					return false;
				}
			case help:
				if (commands.size() == 2) {
					System.out.println(commands.get(1).helpText);
				}
				return false;
			case menu:
				printMenu();
				return false;
			case quit:
				stop();
				return true;
		}

		System.out.println("Invalid command");
		return false;
	}

	/**
	 * Stop the game
	 */
	public void stop() {
		System.out.println("Bye bye");
		System.exit(0);
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

	/**
	 * All the supported commands
	 */
	private enum Command {
		fire(Element.FIRE),
		water(Element.WATER),
		thunder(Element.THUNDER),
		rock(Element.ROCK),
		leaf(Element.LEAF),
		prepare("Prepare a spell", "ex: 'prepare fire thunder rock'"),
		cast("Cast a prepared spell", "ex: 'cast'"),
		alter("Alter a spell", "ex: 'alter [src] [dst]'"),
		menu("Displays the menu", "ex: 'menu'"),
		help("Displays the help for a command", "ex: 'help fire'"),
		quit("Quit Mā", "");
		String description;
		String helpText;
		Element element;

		Command(String description, String help) {
			this.description = description;
			this.helpText = help;
		}

		Command(Element element) {
			this("The " + element + " element", "Strong against " + element.getStrong() + ", weak against " + element.getWeak());
			this.element = element;
		}

		/**
		 * Parses a given input into commands.
		 *
		 * @param input the input
		 * @return a list of command
		 */
		public static List<Command> parse(String input) {
			try {
				return Arrays.stream(input.split(" "))
				             .map(Command::valueOf)
				             .collect(Collectors.toCollection(ArrayList<Command>::new));
			} catch (IllegalArgumentException e) {
				System.out.println("The command doesn't exist");
				return new ArrayList<>();
			}
		}
	}
}
