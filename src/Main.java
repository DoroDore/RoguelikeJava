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
    private static int gMaxHP = 20;
    private static int gBaseMana = 10;
    private static int gMaxMana = 10;
    private static int gwpAttack;
    private static int gwpMAttack;
    private static int gwpMana;
    private static String wpName;
    private static int gCurrentMana;
    private static int gLevel = 1;
    private static int innerPeace = 0;
    private static String gfirstSpell;
    private static int gfirstSpellCost;
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
            if (genHP != 0) {
                actions();
            }
            if (genHP != 0) {
                enemyTurn();
            }
            if (genHP == 0) {
                System.out.println("You defeated " + genName + "!");
                Dialogue.stall();
                rewards();
                wave = wave + 1;
                changeEnemy();
                double result = Dialogue.prefightDialogue();
                if (result == 5.1) {
                    System.out.println("The Stone Sentry starts with 10 damage!");
                    genHP = genHP - 10;
                }
                if (result == 5.2) {
                    System.out.println("From the experience, you gained +1 MATK!");
                    gBaseMATK = gBaseMATK + 1;
                    genHP = 0;
                }
                if (result == 5.3) {
                    System.out.println("You took 10 damage from the ambush!");
                    gBaseHP = gBaseHP - 10;
                }
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
        gWeaponMap.put("ironSword", weapon);
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
                gMaxHP = gMaxHP + 5;
                gBaseHP = gMaxHP;
                gMaxMana = gMaxMana + 5;
                gBaseMana = gMaxMana;
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
            System.out.println("[1] Reactivate the rune\t[2] Destroy the rune\t[3] Ignore the rune");
            Scanner userChoice = new Scanner(System.in);
            int userInput;
            userInput = userChoice.nextInt();
            if (userInput == 1) {
                Dialogue.wave3Reward1();
                gBaseMATK = gBaseMATK + 3;
            }
            else if (userInput == 2) {
                Dialogue.wave3Reward2();
                gBaseATK = gBaseATK + 3;
            }
            else if (userInput == 3) {
                System.out.println("Shrugging, you ignore the rune.");
                Dialogue.stall();
                System.out.println("You feel inner peace.");
                innerPeace = innerPeace +1;
            }
        }
        if (wave == 4) {
            Dialogue.wave4Dialogue();
            gLevel = gLevel+1;
            gBaseATK = gBaseATK + 1;
            gBaseMATK = gBaseMATK + 1;
            gMaxHP = gMaxHP + 5;
            gBaseHP = gMaxHP;
            gMaxMana = gMaxMana + 5;
            gBaseMana = gMaxMana;
        }
        if (wave == 5) {
            System.out.println("After your encounter with the Stone Sentry, you continue to venture deeper into the temple...");
        }
        if (wave == 6) {
            System.out.println("Defeating the " + genName + ", you continue to head deeper into the temple.");
            Dialogue.stall();
            System.out.println("As you continue to walk, you eventually reach the end of the hallway, where you see two books floating on pedestals in front of you.");
            Dialogue.stall();
            Scanner userChoice = new Scanner(System.in);
            int userInput;
            while (true) {
                System.out.println("Which book do you pick?");
                System.out.println("[1] The Blue Book\t[2] The Red Book");
                userInput = userChoice.nextInt();
                if (userInput == 1) {
                    System.out.println("Leaning forward, you grab the blue book.");
                    Dialogue.stall();
                    System.out.println("As soon as you touch the book however, an almost crippling tingling sensation runs through your body, and you fall to the floor, your vision shaking and your head dizzy.");
                    Dialogue.stall();
                    System.out.println("However, almost as soon as it starts, the seizure is gone, and you're left with an inner sense of peace.");
                    Dialogue.stall();
                    System.out.println("You've learnt Mana Draw!");
                    gfirstSpell = "Mana Draw";
                    gfirstSpellCost = 0;
                    return;
                }
                if (userInput == 2) {
                    System.out.println("Leaning forward, you grab the red book.");
                    Dialogue.stall();
                    System.out.println("As soon as you touch the book however, an almost crippling tingling sensation runs through your body, and you fall to the floor, your vision shaking and your head dizzy.");
                    Dialogue.stall();
                    System.out.println("However, almost as soon as it starts, the seizure is gone, and you're left with an inner sense of peace.");
                    Dialogue.stall();
                    System.out.println("You've learnt Blood Shard!");
                    gfirstSpell = "Blood Shard";
                    gfirstSpellCost = 0;
                    return;
                }
                System.out.println("Please input a valid answer");
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
        enemy = new Enemy("Rocky", 8, 40);
        gEnemyMap.put("rocky", enemy);
        enemy = new Enemy("Stone Sentry", 10, 40);
        gEnemyMap.put("stoneSentry", enemy);
        enemy = new Enemy("Animated Puppet", 10, 30);
        gEnemyMap.put("animatedPuppet", enemy);
        enemy = new Enemy("Ancient Slime", 12, 50);
        gEnemyMap.put("ancientSlime", enemy);
        gCurrentEnemy = gEnemyMap.get("blueSlime");
    }
    private static void changeEnemy() {
        if (wave == 2) {
            gCurrentEnemy = gEnemyMap.get("redSlime");
        }
        if (wave == 3) {
            gCurrentEnemy = gEnemyMap.get("iridescentSlime");
        }
        if (wave == 4) {
            gCurrentEnemy = gEnemyMap.get("rocky");
        }
        if (wave == 5) {
            gCurrentEnemy = gEnemyMap.get("stoneSentry");
        }
        if (wave == 6) {
            Random roll = new Random();
            int rand = roll.nextInt(2)+1;
            if (rand == 1) {
                gCurrentEnemy = gEnemyMap.get("animatedPuppet");
            }
            if (rand == 2) {
                gCurrentEnemy = gEnemyMap.get("ancientSlime");
            }
        }
        genName = gCurrentEnemy.getName();
        genMAttack = gCurrentEnemy.getAttack();
        genMHP = gCurrentEnemy.getHP();
        genHP = genMHP;
        genAttack = genMAttack;
    }
    private static void actions() {
        gwpAttack = gCurrentWeapon.getWeaponAttack();
        gwpMana = gCurrentWeapon.getWeaponMana();
        gwpMAttack = gCurrentWeapon.getWeaponMagicAttack();
        wpName = gCurrentWeapon.getWeaponName();

        gCurrentMana = add(gwpMana, gBaseMana);
        while (true) {
            System.out.println("Your Level: " + gLevel + "\tYour stats:");
            System.out.println("ATK: " + add(gwpAttack, gBaseATK) + "\tMATK: " + add(gwpMAttack,gBaseMATK) + "\tHP: " + gBaseHP + "\tMana: " + gCurrentMana + "\tWeapon: " + wpName);
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
                System.out.println("You dealt " + add(gwpAttack, gBaseATK) + " damage!");
                Dialogue.stall();
                genHP = genHP - add(gwpAttack, gBaseATK);
                return;
            }
            else if (userInputUpper.equals("S")) {
                System.out.println("Which spell would you like to cast?");
                if (gfirstSpell != null) {
                    System.out.println("[1] Fireball - 5mp\t[2] Heal - 10mp\t[3] " + gfirstSpell + " - " + gfirstSpellCost);
                }
                else {
                    System.out.println("[1] Fireball - 5mp\t[2] Heal - 10mp");
                }
                userInput = userChoice.next();
                if (Objects.equals(userInput, "1") && gCurrentMana >= 5) {
                    System.out.println("You cast Fireball!");
                    genHP = genHP - (gLevel * 4 + add(gwpMAttack,gBaseMATK));
                    Dialogue.stall();
                    System.out.println("You dealt " + (gLevel * 4 + add(gwpMAttack,gBaseMATK)) + " damage!");
                    gBaseMana = gBaseMana - 5;
                    Dialogue.stall();
                    return;
                }
                else if (Objects.equals(userInput, "1") && gCurrentMana < 5){
                  System.out.println("You don't have enough mana!");
                }
                if (Objects.equals(userInput, "2") && gCurrentMana >= 10) {
                    System.out.println("You cast Heal!");
                    gBaseHP = gBaseHP + add(gwpMAttack,gBaseMATK)*2;
                    Dialogue.stall();
                    System.out.println("You healed " + add(gwpMAttack,gBaseMATK)*2 + " HP!");
                    gBaseMana = gBaseMana - 10;
                    Dialogue.stall();
                    return;
                }
                else if (Objects.equals(userInput, "2") && gCurrentMana < 10){
                    System.out.println("You don't have enough mana!");
                }
                if (Objects.equals(userInput, "3") && gCurrentMana >= gfirstSpellCost && Objects.equals(gfirstSpell, "Mana Draw")) {
                    System.out.println("You cast Mana Draw!");
                    Dialogue.stall();
                    gBaseMana = gBaseMana + add(gwpMAttack,gBaseMATK)/2;
                    System.out.println("You drew " + (add(gwpMAttack,gBaseMATK)/2) + " mana from the air!");
                    return;
                }
                if (Objects.equals(userInput, "3") && gCurrentMana >= gfirstSpellCost && Objects.equals(gfirstSpell, "Blood Shard")) {
                    System.out.println("How much blood do you want to draw?");
                    int bloodDraw = userChoice.nextInt();
                    if (bloodDraw >gBaseHP-1) {
                        System.out.println("You can't draw that much blood!");
                    }
                    else if (bloodDraw < 1) {
                        System.out.println("You can't draw less than 1HP worth of blood!");
                    }
                    else {
                        System.out.println("You drew " + bloodDraw + "HP worth of blood!");
                        Dialogue.stall();
                        System.out.println("You dealt a total of " + bloodDraw*2 + " damage!");
                        genHP = genHP - (bloodDraw*2);
                        gBaseHP = gBaseHP - bloodDraw;
                        return;
                    }
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
            if (chance == 1) {
                enSpellSparkleSpell();
                return;
            }
        }
        if (genName.equals("Rocky")) {
            Random attackChoice = new Random();
            int chance = attackChoice.nextInt(5)+1;
            if (chance == 1) {
                enSpellBulldose();
                return;
            }
            if (chance == 2) {
                enSpellSelfDestruct();
                return;
            }
        }
        if (genName.equals("Stone Sentry")) {
            Random attackChoice = new Random();
            if (genHP <= 15) {
                enSpellReformat();
                return;
            }
            else {
                int chance = attackChoice.nextInt(3)+1;
                if (chance == 1) {
                    enSpellRockBomb();
                    return;
                }
            }
        }
        if (genName.equals("Animated Puppet")) {
            Random attackChoice = new Random();
            int chance = attackChoice.nextInt(3)+1;
            if (chance == 1) {
                enSpellMimic();
                return;
            }
            if (chance == 2) {
                enSpellCurse();
                return;
            }
        }
        if (genName.equals("Ancient Slime")) {
            Random attackChoice = new Random();
            int chance = attackChoice.nextInt(3)+1;
            System.out.println("Roll: " + chance);
            if (chance == 1) {
                enSpellCurse();
                return;
            }
            if (chance == 2) {
                enSpellSagesong();
                return;
            }
        }
        System.out.println("The " + genName + " attacks!");
        Dialogue.stall();
        System.out.println("You took " + genAttack + " damage...");
        gBaseHP = gBaseHP - genAttack;
        Dialogue.stall();
    }


    /* This is the database of enemy attacks! All of them can be found here! :) */
    private static void enSpellSparkleSpell() {
        Random attackChoice = new Random();
        int heals = attackChoice.nextInt(10)+4;
        System.out.println("The " + genName + " uses Sparkle Spell!");
        Dialogue.stall();
        System.out.println("The " + genName + " healed " + heals + "HP!");
        genHP = genHP + heals;
    }
    private static void enSpellBulldose() {
        System.out.println("The " + genName + " uses Bulldose!");
        Dialogue.stall();
        System.out.println("You took " + genHP/2 + " damage...");
        gBaseHP = gBaseHP - genHP/2;
    }
    private static void enSpellSelfDestruct() {
        System.out.println("The " + genName + " uses Self Destruct!");
        Dialogue.stall();
        System.out.println("The Rocky exploded!");
        Dialogue.stall();
        System.out.println("You took 15 damage...");
        gBaseHP = gBaseHP - 15;
        genHP = 0;
    }
    private static void enSpellReformat() {
        Random attackChoice = new Random();
        System.out.println("The " + genName + " uses Reformat!");
        Dialogue.stall();
        int damageRoll = attackChoice.nextInt(10)+1;
        System.out.println("You took " + damageRoll*2 + " damage!");
        gBaseHP = gBaseHP - damageRoll*2;
        System.out.println("The " + genName + " repaired itself!");
        System.out.println("The " + genName + " healed for 10HP!");
        genHP = genHP + 10;
    }
    private static void enSpellRockBomb() {
        Random attackChoice = new Random();
        System.out.println("The " + genName + " uses Rock Bomb!");
        Dialogue.stall();
        System.out.println("The " + genName + " launches part of it's body to explode you!");
        Dialogue.stall();
        int damageRoll = attackChoice.nextInt(10)+5;
        System.out.println("You took " + damageRoll + " damage!");
        gBaseHP = gBaseHP - damageRoll;
        System.out.println("The attack harmed the " + genName + " too!");
        System.out.println("The " + genName + " took 5 points of recoil!");
        genHP = genHP -5;
    }
    private static void enSpellMimic() {
        System.out.println("The " + genName + " uses Mimic!");
        Dialogue.stall();
        System.out.println("The " + genName + " copies your fireball spell!");
        Dialogue.stall();
        System.out.println("You took " + (gLevel * 4 + add(gwpMAttack,gBaseMATK)) + " damage...");
        Dialogue.stall();
        gBaseHP = gBaseHP - (gLevel * 4 + add(gwpMAttack,gBaseMATK));
    }
    private static void enSpellCurse() {
        Random roll = new Random();
        System.out.println("The " + genName + " uses Curse!");
        Dialogue.stall();
        int curse = roll.nextInt(4)+1;
        System.out.println("The curse has struck " + curse + " times!");
        Dialogue.stall();
        int baseDMG = roll.nextInt(5)+1;
        for (int i = 1; i <= curse; i++) {
            System.out.println("You took " + baseDMG + " damage!");
            gBaseHP = gBaseHP - baseDMG;
            Dialogue.stall();
            System.out.println("The curse intensified!");
            Dialogue.stall();
            baseDMG = baseDMG + 3;
        }
    }
    private static void enSpellSagesong() {
        System.out.println("The " + genName + " uses Sagesong!");
        Dialogue.stall();
        System.out.println("The " + genName + " sings a primordial song!");
        Dialogue.stall();
        System.out.println("The " + genName + " stole " + genAttack/2 + " mana!");
        gCurrentMana = gCurrentMana - genAttack/2;
    }
    /* These are methods generally used either in: 1. Other Classes 2. To help me calculate stats with variables */
    private static int add(int numOne, int numTwo) {
        return numOne+numTwo;
    }
    public static int getPeace() {
        return innerPeace;
    }
    public static int getWave() {
        return wave;
    }
    public static String getGenName () {
        return genName;
    }
    public static int getLevel() {
        return gLevel;
    }
}
