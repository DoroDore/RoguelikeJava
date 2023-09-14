import java.util.InputMismatchException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static int hp = 10;
    public static int baseMana = 20;
    public static int mana;
    public static int attack;
    public static String weapon = "Sharpened Stick";
    public static int worldNo = 1;
    public static int wave = 1;
    public static String enemy;
    public static int enemyAttack;
    public static int enemyHP;
    public static void main(String[] args) {
        introduction();
        System.out.println("\n\n\n\n\n------------------------------");
        System.out.println("You wake up in a strange forest, with nothing but a sharpened wooden stick.");
        stall();
        System.out.println("Suddenly, you see something coming towards you.");
        stall();
        System.out.println("Cautiously, you pick up your sharpened stick and creep towards the entity.");
        stall();
        System.out.println("You encountered a blue slime!");
        primeEnemy();
        actions();
    }
    public static void primeEnemy() {
        preBattleCheck();
        if (worldNo == 1 && wave == 1) {
            enemy = "Blue Slime";
            enemyAttack = 1;
            enemyHP = 10;
        }
    }
    public static void preBattleCheck() {
        if (weapon.equals("Sharpened Stick")) {
            attack = 5;
            mana = baseMana;
        }
    }
    public static void actions() {
        System.out.println("ATK: " + attack + "HP: " + hp + "\tMana: " + mana + "\tWeapon: " + weapon);
        System.out.println("What would you like to do?");
        System.out.println("[A] To Attack or [S] to Bring up Spells list");
        Scanner userChoice = new Scanner(System.in);
        String userInput;
        boolean validInput = false;
        String userInputUpper;
        while (!validInput) {
            try {
                userInput = userChoice.next();
                userInputUpper = userInput.toUpperCase();
                if (userInputUpper.equals("A") || userInputUpper.equals("S")) {
                    validInput = true;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a valid answer.");
            }
        }
        System.out.println("Success!");
    }
    public static void introduction () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome adventurer to the text based version of Roguelike Adventures!");
        System.out.println("Before we begin, what is your name?");
        String name = scanner.nextLine();
        if (name.equals("Baboos")) {
            System.out.println("Oh hello Baboos, fancy meeting you here");
            System.out.println("Well, let us embark on a new journey... \n\n\n\n\n------------------------------");
        } else if (name.equals("Legendaried")) {
            System.out.println("Hello, you are very legendary");
            System.out.println("Well, let us embark on a new journey... \n\n\n\n\n------------------------------");
        } else {
            System.out.println("I see. Your name is " + name + ". Well, let us embark on a new journey...\n\n\n\n\n------------------------------");
        }
        System.out.println("Wait. One more thing. Do you know the rules of this game?");
        System.out.println("[Y] for yes, [N] for no.");
        String yesNo;
        boolean validAnswer = false;
        String yesNoUpper = null;
        while (!validAnswer) {
            try {
                yesNo = scanner.next();
                yesNoUpper = yesNo.toUpperCase();
                if (yesNoUpper.equals("Y") || yesNoUpper.equals("N")) {
                    validAnswer = true;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a valid answer.");
            }
        }
        if (yesNoUpper.equals("N")) {
            System.out.println("I see. Well, let me explain.");
            System.out.println("First of all, from now on, when reading text, press return after each statement.");
            stall();
            System.out.println("In this game, you will encounter enemies, each of which will increase in difficulty.");
            stall();
            System.out.println("The game operates on a turn based combat system, where you will be prompted to use letters and numbers to control your character.");
            stall();
            System.out.println("After a certain amount of enemies are defeated, you will encounter a boss.");
            stall();
            System.out.println("If you defeat the boss, you will move on to the next location!");
            stall();
            System.out.println("Now, for your character stats.");
            stall();
            System.out.println("In this game, your character does NOT have an attack stat. This is instead determined by the weapon you are using.");
            stall();
            System.out.println("You can purchase weapons in between battles!");
            stall();
            System.out.println("As for magic, your mana will increase after every battle, and certain weapons will increase your mana stat too!");
            stall();
            System.out.println("Spells can be learnt every so often after boss fights.");
            stall();
            System.out.println("Well, that more or less sums up the rules! Press enter to continue.");
            stall();
        }
        else {
            System.out.println("Very well. We shall begin.");
            stall();
        }
    }
    public static void stall () {
        Scanner stall = new Scanner(System.in);
        stall.nextLine();
    }
}
