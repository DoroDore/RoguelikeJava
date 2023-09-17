import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static HashMap<String, Weapon> gWeaponMap = new HashMap<>();
    static HashMap<String, Enemy> gEnemyMap = new HashMap<>();
    private static int wave = 1;
    private static int gBaseATK = 3;
    private static int gBaseMATK = 3;
    private static int gBaseHP = 20;
    private static int gBaseMana = 10;
    private static int gLevel = 1;
    private static int innerPeace = 0;
    private static Weapon gCurrentWeapon;
    private static Enemy gCurrentEnemy;
    private static int genMAttack;
    private static int genMHP;
    private static String genName;
    private static int genAttack;
    private static int genHP;


    public static void main(String[] args) {
        createWeapons();
        Dialogue.introduction();
        Dialogue.preFight();
        createWeapons();
        createEnemies();
        genName = gCurrentEnemy.getName();
        genMAttack = gCurrentEnemy.getAttack();
        genMHP = gCurrentEnemy.getHP();
        genHP = genMHP;
        genAttack = genMAttack;

        while (gBaseHP > 0) {
            actions();
            enemyTurn();
            if (genHP == 0) {
                System.out.println("You defeated " + genName + "!");
                Dialogue.stall();
                rewards();
                wave = wave + 1;
                changeEnemy();
                System.out.println("Satisfied, you continue your journey.");
                Dialogue.stall();
                System.out.println("Before long, another enemy stops to block your path.");
                Dialogue.stall();
                System.out.println("You encountered " + genName + "!");
                Dialogue.stall();
                System.out.println("\n\n\n\n\n------------------------------");
            }
        }
        Dialogue.death();
    }
    private static void createWeapons() {
        Weapon weapon = new Weapon("Sharpened Stick", 5, 0, 0);
        gWeaponMap.put("sharpenedStick", weapon);
        weapon = new Weapon("Hammer", 5, 0,0);
        gWeaponMap.put("hammer", weapon);
        weapon = new Weapon("Stone Saber", 7, 0,0);
        gWeaponMap.put("stoneSaber", weapon);
        weapon = new Weapon("Sagewood Staff", 3, 5,10);
        gWeaponMap.put("sagewoodStaff", weapon);
        weapon = new Weapon ("Iron Sword", 9, 0, 5);
        gWeaponMap.put("ironSword", weapon);1
        gCurrentWeapon = gWeaponMap.get("sharpenedStick");
    }
    private static void rewards() {
        if (wave == 1) {
            System.out.println("Pick your reward!");
            System.out.println("[1] Stone Saber\t[2] Sagewood Staff");
            Scanner userChoice = new Scanner(System.in);
            String userInput;
            userInput = userChoice.next();
            if (Objects.equals(userInput, "1")) {
                gCurrentWeapon = gWeaponMap.get("stoneSaber");
            }
            else {
                gCurrentWeapon = gWeaponMap.get("sagewoodStaff");
            }
        }
        if (wave == 2) {
            System.out.println("Pick your reward!");
            System.out.println("[1] Level Up!\t[2] Relax");
            Scanner userChoice = new Scanner(System.in);
            String userInput;
            userInput = userChoice.next();
            if (Objects.equals(userInput, "1")) {
                gLevel = gLevel+1;
                gBaseATK = gBaseATK + 1;
                gBaseMATK = gBaseMATK + 1;
                gBaseHP = 25;
            }
            else {
                System.out.println("You take a rare opportunity to take a break and relax");
                System.out.println("Your feel inner peace.");
                innerPeace = innerPeace +1;
            }
        }
        if (wave == 3) {
            System.out.println("As you continue to walk, you come across a deactivated runeset");
            Dialogue.stall();
            System.out.println("What do you want to do?");
            Dialogue.stall();
            System.out.println("[1] Reactivate the rune (-10MP)\t[2] Destroy the rune\t[3] Ignore the rune");
            Scanner userChoice = new Scanner(System.in);
            String userInput;
            userInput = userChoice.next();
            switch (userInput) {
                case 1:
                    //Some stuff happens here
            }
        }
    }
    private static void createEnemies() {
        Enemy enemy = new Enemy("Blue Slime", 5, 10);
        gEnemyMap.put("blueSlime", enemy);
        enemy = new Enemy("Red Slime", 6, 20);
        gEnemyMap.put("redSlime", enemy);
        enemy = new Enemy("Iridescent Slime", 8, 25);
        gEnemyMap.put("iridescentSlime", enemy);
        gCurrentEnemy = gEnemyMap.get("blueSlime");
    }
    private static void changeEnemy() {
        if (wave == 2) {
            gCurrentEnemy = gEnemyMap.get("redSlime");
        }
        if (wave == 3) {
            gCurrentEnemy = gEnemyMap.get("iridescentSlime");
        }
        genName = gCurrentEnemy.getName();
        genMAttack = gCurrentEnemy.getAttack();
        genMHP = gCurrentEnemy.getHP();
        genHP = genMHP;
        genAttack = genMAttack;
    }
    private static void actions() {
        int wpAttack = gCurrentWeapon.getWeaponAttack();
        int wpMana = gCurrentWeapon.getWeaponMana();
        int wpMAttack = gCurrentWeapon.getWeaponMagicAttack();
        String wpName = gCurrentWeapon.getWeaponName();

        int currentMana = add(wpMana, gBaseMana);
        while (true) {
            System.out.println("Your Level: " + gLevel + "\tYour stats:");
            System.out.println("ATK: " + add(wpAttack, gBaseATK) + "\tMATK: " + add(wpMAttack,gBaseMATK) + "\tHP: " + gBaseHP + "\tMana: " + currentMana + "\tWeapon: " + wpName);
            System.out.println("------------------------------");
            System.out.println("Enemy: " + genName + "\tEnemy Attack: " + genAttack + "\t Enemy HP: " + genHP);
            System.out.println("------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("[A] To Attack or [S] to Bring up Spells list");
            Scanner userChoice = new Scanner(System.in);
            String userInput;
            boolean validInput = false;
            String userInputUpper = null;
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
            if (userInputUpper.equals("A")) {
                System.out.println("You swing your " + wpName + "!");
                Dialogue.stall();
                System.out.println("You dealt " + add(wpAttack, gBaseATK) + " damage!");
                Dialogue.stall();
                genHP = genHP - add(wpAttack, gBaseATK);
                return;
            }
            else if (userInputUpper.equals("S")) {
                System.out.println("Which spell would you like to cast?");
                System.out.println("[1] Fireball - 5mp \t [2] Heal - 10mp");
                userInput = userChoice.next();
                if (Objects.equals(userInput, "1") && currentMana >= 5) {
                    System.out.println("You cast fireball!");
                    genHP = genHP - (gLevel * 5 + add(wpMAttack,gBaseMATK));
                    Dialogue.stall();
                    System.out.println("You dealt " + (gLevel * 5 + add(wpMAttack,gBaseMATK)) + " damage!");
                    gBaseMana = gBaseMana - 5;
                    Dialogue.stall();
                    return;
                }
                else if (Objects.equals(userInput, "1") && currentMana < 5){
                  System.out.println("You don't have enough mana!");
                }
                if (Objects.equals(userInput, "2") && currentMana >= 10) {
                    System.out.println("You cast heal!");
                    gBaseHP = gBaseHP + add(wpMAttack,gBaseMATK)*2;
                    Dialogue.stall();
                    System.out.println("You healed " + gLevel*10 + " HP!");
                    gBaseMana = gBaseMana - 10;
                    Dialogue.stall();
                    return;
                }
                else if (Objects.equals(userInput, "2") && currentMana < 10){
                    System.out.println("You don't have enough mana!");
                }
            }
        }
    }
    private static void enemyTurn() {
        if (genHP  < 0 ) {
            genHP = 0;
            return;
        }
        if (genName.equals("Iridescent Slime")) {
            Random attackChoice = new Random();
            int chance = attackChoice.nextInt(3)+1;
            int heals = attackChoice.nextInt(10)+4;
            if (chance == 1) {
                System.out.println("The " + genName + " uses Sparkle Spell!");
                Dialogue.stall();
                System.out.println("The " + genName + " healed " + heals + "HP!");
                genHP = genHP + heals;
                return;
            }
        }
        System.out.println("The " + genName + " attacks!");
        Dialogue.stall();
        System.out.println("You took " + genAttack + " damage...");
        gBaseHP = gBaseHP - genAttack;
        Dialogue.stall();
    }
    private static int add(int numOne, int numTwo) {
        return numOne+numTwo;
    }
    public static int getPeace() {
        return innerPeace;
    }
}
