import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static HashMap<String, Weapon> gWeaponMap = new HashMap<>();
    public static int worldNo = 1;
    public static int wave = 1;
    public static int gBaseATK = 5;
    public static int gBaseHP = 20;
    public static int gBaseMana = 10;

    private static Weapon gCurrentWeapon;

    public static void main(String[] args) {
        Dialogue.introduction();
        Dialogue.preFight();
        primeEnemy();
        actions();
    }
    private static void createWeapons() {
        Weapon weapon = new Weapon("Sharpened Stick", 5, 0);
        gWeaponMap.put("sharpenedStick", weapon);
        weapon = new Weapon("Hammer", 5, 0);
        gWeaponMap.put("hammer", weapon);
        gCurrentWeapon = weapon;
    }
    private static void primeEnemy() {
        Enemy blueSlime = new Enemy("Blue Slime", 5, 10);
        String enemyName;
        int enemyAttack;
        int enemyMhp;
        if (worldNo == 1 && wave == 1) {
            enemyName = blueSlime.getName();
            enemyAttack = blueSlime.getAttack();
            enemyMhp = blueSlime.getHP();
        }

    }
    private static void actions() {
        String wpCodeName = "sharpenedStick";
        int wpAttack = gCurrentWeapon.getWeaponAttack();
        int wpMana = gCurrentWeapon.getWeaponMana();
        String wpName = gCurrentWeapon.getWeaponName();
        System.out.println("ATK: " + wpAttack+gBaseATK + "HP: " + gBaseHP + "Mana: " + wpMana+gBaseMana + "\tWeapon: " + wpName);
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

}
