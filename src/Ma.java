import game.Element;
import game.entities.GearedMonster;
import game.entities.Monster;
import game.entities.Player;
import game.spells.ConcreteSpell;
import game.spells.ElementSpellDecorator;
import game.spells.Spell;
import game.spells.SpellDecorator;
import utils.AsciiImages;
import utils.TerminalUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Ma {
	private final int manaCost = 1;
	private Player player;
	private Scanner scanner;
	private Spell spell;
	private Random random;

	public Ma() {
		scanner = new Scanner(System.in);
		try {
			System.out.println(TerminalUtils.colorize(AsciiImages.parseAscii("sprites/sorcier.ascii"), TerminalUtils.Colors.DARKBLUE));
		} catch (FileNotFoundException ignored) {
		}
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

	/**
	 * Start the game
	 *
	 * @throws InterruptedException
	 */
	public void start() throws InterruptedException {
		List<Command> commands;
		printMenu();
		while (true) {
			Monster monster = GearedMonster.generateGearedMonster(player.getLevel());
			System.out.println(new String(new char[100]).replace("\0", "\n")); // clears the monitor
			System.out.printf("%s appears ! What are you going to do ?\n", monster);

			while (monster.isAlive()) {

				while (!firstPhase(commands = Command.parse(scanner.nextLine()))) {
					if (!commands.isEmpty() && commands.get(0) == Command.rest) {
						monsterPlay(monster);
					}
				}

				player.setMana(player.getMana() - spell.getManaCost());
				System.out.println(player.status());

				while (!secondPhase(monster, Command.parse(scanner.nextLine()))) ;


				monsterPlay(monster);
			}

			System.out.println("You killed the " + monster.getName());
			player.levelUp();
			System.out.println("Are you ready for some more fight ? (press enter)");
			scanner.nextLine();
		}
	}

	/**
	 * The monster hits the player
	 *
	 * @param monster the monster
	 */
	private void monsterPlay(Monster monster) {
		if (monster.isAlive()) {
			System.out.println("\nThe monster attack !");
			player.receiveDamage(monster.hit());

			if (!player.isAlive()) {
				System.out.println("YOU DIED");
				stop();
			}
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
				break;
			case menu:
				printMenu();
				return false;
			case quit:
				stop();
				return true;
			case prepare:
				commands.remove(0);
				spell = new ConcreteSpell(10, manaCost);
				try {
					for (Command command : commands) {
						spell = new ElementSpellDecorator(spell, command.element, manaCost);
					}
					if (spell.getManaCost() > player.getMana()) {
						System.out.println("You don't have enough mana for casting "
								                   + spell + "(" + spell.getManaCost() + ")");
						return false;
					} else {
						System.out.println("You have successfully prepare the spell : "
								                   + spell + " (" + spell.getManaCost() + ")");
						return true;
					}
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					return false;
				}
			case rest:
				System.out.println("You decide to rest");
				player.setMana(Math.min(player.getMana() + manaCost * 3, player.getMaxMana()));
				player.setHealthPoints(Math.min(player.getHealthPoints() + player.getLevel(), player.getMaxHealthPoints()));
				System.out.println(player.status());
				return false;
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
				if (!(spell instanceof SpellDecorator)) {
					System.out.println("You can't alter the spell because there isn't any decorator on it");
					return false;
				}

				if (commands.size() == 3) {
					try {
						final Element toFind = commands.get(1).element;
						int pos = ((SpellDecorator) spell).getPos(spell, (s -> (s instanceof ElementSpellDecorator && (
								(ElementSpellDecorator) s).getElement() == toFind)));
						((SpellDecorator) spell).alter(pos, new ElementSpellDecorator(null, commands.get(2).element, manaCost));
					} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
						System.out.println(e.getMessage());
					}
					System.out.println("The spell is : " + spell + " mana : " + spell.getManaCost());
					return false;
				}
				break;
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
		rest("Recover to gain mana", "ex: 'rest'"),
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
