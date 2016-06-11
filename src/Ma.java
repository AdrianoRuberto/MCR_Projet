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
	private Spell spell;

	public Ma() {
		scanner = new Scanner(System.in);
		System.out.println("Welcome to Mā ! You will fight against evil monsters using your powerful magics !");
		System.out.print("Enter your magician name: ");
		String name = scanner.nextLine();
		player = new Player(name);
		running = true;
	}

	public void start() {
		ArrayList<Command> commands;


		Monster monster = generateMonster();
		System.out.printf("A wild %s appears ! What are you going to do ?\n", monster.getName());

		do{
			printMenu();

		} while (!executeCommand());

		//Throws spell

	}

	public void stop() {
		running = false;
	}

	private Monster generateMonster() {
		return new Monster("Goblin", 1, 10, 2, 1);
	}

	public boolean executeCommand(){
		String input = scanner.nextLine();
		try
		{
			ArrayList<Command> commands = Arrays.stream(input.split(" ")).map(Command::valueOf).collect(Collectors.toCollection(ArrayList<Command>::new));
			switch (commands.get(0))
			{

				case prepare:
					if (commands.size() > 1)
					{
						spell = new Spell(commands.get(1).element);

						for (int i = 2; i < commands.size(); ++i)
						{
							spell = new CombinedSpell(spell, commands.get(i).element);
						}

						System.out.println("You have successfully prepare a spell");
						return false;
					} else
					{
						System.out.println("Prepare spell command not valid, use help ");
						return false;
					}

				case cast:
					if (spell != null)
					{
						;//exec spell
						return true;
					} else
					{
						return false;
					}

				case menu:
					printMenu();
					return true;

				case quit:
					stop();
					return true;

				case help:
					if (commands.size() == 2)
					{
						System.out.println(commands.get(1).helpText);
						return true;
					} else
					{
						System.out.println("help comand should be folloed by another command\n Please retry!");
						return false;
					}

				default:

					Spell spell = new Spell(commands.get(0).element);

					for (int i = 1; i < commands.size(); ++i)
					{
						spell = new CombinedSpell(spell, commands.get(i).element);
					}
			}
		} catch (IllegalArgumentException e){
			System.out.println("Invalide comande");
		}

	return false;
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
		prepare("Prepare a spell",""),
		cast("Cast a prepared spell",""),
		alter("Alter a spell", ""),
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
