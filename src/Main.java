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
    //All gVars below are related to character stats!
    private static int gBaseATK = 3;
    private static int gBaseMATK = 3;
    private static int gBaseHP = 20;
    private static int gMaxHP = 20;
    private static int gBaseMana = 10;
    private static int gMaxMana = 10;
    private static int gCurrentMana;
    private static int gLevel = 1;
    private static int innerPeace = 0;
    //All gVars below are related to weapon stats
    private static int gwpAttack;
    private static int gwpMAttack;
    private static int gwpMana;
    private static String wpName;
    private static int gwpDefense;
    private static Weapon gCurrentWeapon;
    //All gVars below are related to enemies
    private static Enemy gCurrentEnemy;
    private static int genMAttack;
    private static int genMHP;
    private static String genName;
    private static int genAttack;
    private static int genHP;
    //All gVars below are related to spells
    private static String gfirstSpell = "Fireball";
    private static int gfirstSpellCost = 5;
    private static String gthirdSpell;
    private static int gthirdSpellCost;
    private static String gfourthSpell;
    private static int gfourthSpellCost;
    private static String gsoulspell;
    private static int gsoulspellCost;
    private static String gsoulspellBoss;
    //All gVars below are related to status effects
    private static int gShadowDance;
    private static int gResidualWinds;
    private static int gBaseDefense;
    private static int gGaleforce;
    private static int gManaRegen;
    private static int gStoneboundStack;
    private static int gTectonicStack;
    private static int gPeacekeeperTime;
    //All gVars below are related to specific attacks
    private static int gaerobombCharge = 0;


    public static void main(String[] args) {
        createWeapons();
        int speedrun = Dialogue.introduction();
        if (speedrun == 1) {
            gMaxHP = 1000;
            gMaxMana = 1000;
            gBaseHP = 1000;
            gBaseMana = 1000;
        }
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
                if (genHP < 0) {
                    genHP = 0;
                }
            }
            if (genHP != 0) {
                enemyTurn();
            }
            if (genHP == 0) {
                if (Objects.equals(genName, "hStone Sentry")) {
                    System.out.println("You've defeated Stone Sentry!");
                }
                else {
                    System.out.println("You defeated " + genName + "!");
                }
                Dialogue.stall();
                cleanseStatus();
                rewards();
                wave = wave + 1;
                if (wave == 2 || wave == 3 || wave == 4) {
                    changeEnemy(wave);
                }
                double result = Dialogue.prefightDialogue();
                changeEnemy(result);
                if (result == 5.1) {
                    System.out.println("The Stone Sentry starts with 10 damage!");
                    genHP = genHP - 10;
                }
                if (result == 5.2) {
                    System.out.println("From the experience, you gained +1 MATK!");
                    gBaseMATK = gBaseMATK + 1;
                    genName = "hStone Sentry";
                    genHP = 0;
                }
                if (result == 5.3) {
                    System.out.println("You took 10 damage from the ambush!");
                    gBaseHP = gBaseHP - 10;
                }
                if (result == 7.21) {
                    innerPeace = innerPeace + 1;
                }
            }
        }
        if (innerPeace >= 3) {
            Dialogue.deathRevive();
            gBaseHP = gMaxHP;
            gBaseMana = gMaxMana;
            innerPeace = innerPeace - 3;
        }
        else {
            Dialogue.death();
        }
    }
    private static void createWeapons() {
        Weapon weapon = new Weapon("Sharpened Stick", 5, 0, 0, 0);
        gWeaponMap.put("sharpenedStick", weapon);
        weapon = new Weapon("Hammer", 5, 0,0, 0);
        gWeaponMap.put("hammer", weapon);
        weapon = new Weapon("Stone Saber", 7, 0,0, 4);
        gWeaponMap.put("stoneSaber", weapon);
        weapon = new Weapon("Sagewood Staff", 3, 5,10, 0);
        gWeaponMap.put("sagewoodStaff", weapon);
        weapon = new Weapon ("Iron Sword", 9, 0, 2, 5);
        gWeaponMap.put("ironSword", weapon);
        weapon = new Weapon("Venomspike Maw", 12, 4, 5,3 );
        gWeaponMap.put("venomspikeMaw", weapon);
        weapon = new Weapon("Wyrmwood Scepter", 5, 8, 15, 1);
        gWeaponMap.put("wyrmwoodScepter", weapon);
        weapon = new Weapon("Stormrender", 14, 5, 0, 0);
        gWeaponMap.put("stormrender", weapon);
        weapon = new Weapon("Galebound Grimoire", 3, 10, 20, -1);
        gWeaponMap.put("galeboundGrimoire", weapon);
        weapon = new Weapon("Stonewrought Maul", 10, 0, 10, 8);
        gWeaponMap.put("stonewroughtMaul", weapon);
        weapon = new Weapon("Runebound Earthshaker", 4, 8, 13, 1);
        gWeaponMap.put("runeboundEarthshaker", weapon);
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
                gfirstSpell = "Fortify";
                gfirstSpellCost = 3;
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
        if (wave == 5 && !Objects.equals(genName, "hStone Sentry")) {
            System.out.println("With a final blow, you strike down the Stone Sentry, which gives a sad little hum before exploding.");
            Dialogue.stall();
            System.out.println("Within the burning mess that is the remains of the Stone Sentry, you spot a few items lying around.");
            Dialogue.stall();
            while (true) {
                System.out.println("You can only save one object while the others burn beyond repair. Which one do you save?");
                System.out.println("[1] Iron Sword\t[2] Mana Core\t[3] Watch it burn");
                Scanner userChoice = new Scanner(System.in);
                int userInput = userChoice.nextInt();
                if (userInput == 1) {
                    System.out.println("Rushing into the fire, you save the iron sword.");
                    Dialogue.stall();
                    System.out.println("You got an Iron Sword!");
                    gCurrentWeapon = gWeaponMap.get("ironSword");
                    gfirstSpell = "Fortify";
                    gfirstSpellCost = 3;
                    return;
                }
                if (userInput == 2) {
                    System.out.println("Rushing into the fire, you save the mana core.");
                    Dialogue.stall();
                    System.out.println("Upon touching the mana core, you feel your body hum with energy as the mana from the core seeps into your body.");
                    Dialogue.stall();
                    System.out.println("Your mana increased!");
                    gMaxMana = gMaxMana + 5;
                    Dialogue.stall();
                    return;
                }
                if (userInput == 3) {
                    System.out.println("You are perfectly content with watching the remains of the Stone Sentry burn.");
                    Dialogue.stall();
                    System.out.println("You feel an inner sense of peace watching the fire show.");
                    Dialogue.stall();
                    innerPeace = innerPeace +1;
                    return;
                }
            }
        }
        if (wave == 5 && Objects.equals(genName, "hStone Sentry")) {
            System.out.println("After your encounter with the Stone Sentry, you continue to venture deeper into the temple...");
        }
        if (wave == 6) {
            System.out.println("Defeating the " + genName + ", you continue to head deeper into the temple.");
            Dialogue.stall();
            System.out.println("As you continue to walk, you eventually reach the end of the hallway, where you see three books floating on pedestals in front of you.");
            Dialogue.stall();
            Scanner userChoice = new Scanner(System.in);
            int userInput;
            while (true) {
                System.out.println("Which book do you pick?");
                System.out.println("[1] The Blue Book\t[2] The Red Book\t[3] The Black Book");
                userInput = userChoice.nextInt();
                if (userInput == 1) {
                    System.out.println("Leaning forward, you grab the blue book.");
                    Dialogue.stall();
                    System.out.println("As soon as you touch the book however, an almost crippling tingling sensation runs through your body, and you fall to the floor, your vision shaking and your head dizzy.");
                    Dialogue.stall();
                    System.out.println("However, almost as soon as it starts, the seizure is gone, and you're left with an inner sense of peace.");
                    Dialogue.stall();
                    System.out.println("You've learnt Mana Draw!");
                    gthirdSpell = "Mana Draw";
                    gthirdSpellCost = 0;
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
                    gthirdSpell = "Blood Shard";
                    gthirdSpellCost = 0;
                    return;
                }
                if (userInput == 3) {
                    System.out.println("Leaning forward, you grab the black book");
                    Dialogue.stall();
                    System.out.println("As your fingers brush the book however, you feel your entire vision go dark as an agonizing pain rips through your body, every fiber of your being seemingly lit on fire.");
                    Dialogue.stall();
                    System.out.println("You stay in this state for what seems like an eternity. However, when the pain finally subsides, your body flows with a dark energy.");
                    Dialogue.stall();
                    System.out.println("You've learnt Shadow Dance!");
                    Dialogue.stall();
                    System.out.println("You feel an inner sense of disturbance.");
                    gthirdSpell = "Shadow Dance";
                    gthirdSpellCost = 3;
                    gShadowDance = 0;
                    innerPeace = innerPeace - 2;
                    return;
                }
                System.out.println("Please input a valid answer");
            }
        }
        if (Objects.equals(genName, "Buyaji, Eater of Worlds")) {
            Dialogue.buyajiReward();
            Scanner userChoice = new Scanner(System.in);
            while (true) {
                System.out.println("Which one do you choose?");
                System.out.println("[1] Venomspike Maw\t[2] Wyrmwood Scepter\t[3] Soul of Buyaji");
                int choice = userChoice.nextInt();
                if (choice == 1) {
                    System.out.println("Walking forward, you claim the Venomspike Maw, and watch as the other two items disintegrate.");
                    Dialogue.stall();
                    System.out.println("You learnt the new skill Venomstrike!");
                    System.out.println("Venomstrike does increased damage to enemies with a higher HP than you!");
                    Dialogue.stall();
                    gfourthSpell = "Venomstrike";
                    gfourthSpellCost = 10;
                    gCurrentWeapon = gWeaponMap.get("venomspikeMaw");
                    return;
                }
                if (choice == 2) {
                    System.out.println("Walking forward, you claim the Wyrmwood Scepter, and watch as the other two items disintegrate.");
                    Dialogue.stall();
                    System.out.println("You learnt the new skill Wrymrock Strike!");
                    System.out.println("On top of dealing damage, Wyrmrock Strike has a chance to boost defense!");
                    Dialogue.stall();
                    gfourthSpell = "Wyrmrock Strike";
                    gfourthSpellCost = 8;
                    gCurrentWeapon = gWeaponMap.get("wyrmwoodScepter");
                    return;
                }
                if (choice == 3) {
                    System.out.println("You choose to claim Buyaji's rapidly fading soul for yourself.");
                    Dialogue.stall();
                    System.out.println("On contact, you feel energy rush through your body, boosting your strength.");
                    System.out.println("You also feel an inner sense of peace.");
                    Dialogue.stall();
                    System.out.println("You learnt the new soulspell Eternal Devourer's Grasp!");
                    System.out.println("Eternal Devourer's Grasp is an extreme skill that when activated, will kill any enemy with an HP less than yours. Once the enemy is defeated, you will heal HP as a result!");
                    Dialogue.stall();
                    System.out.println("However, if you don't kill the enemy, you will still be able to deal damage. The only downside is your HP will be halved!");
                    Dialogue.stall();
                    gsoulspell = "Eternal Devourer's Grasp";
                    gsoulspellCost = 15;
                    gsoulspellBoss = "Buyaji, Eater of Worlds";
                    innerPeace = innerPeace + 3;
                    return;
                }
            }
        }
        if (Objects.equals(genName, "Vox the Wind Elemental")) {
            Dialogue.voxReward();
            Scanner userChoice = new Scanner(System.in);
            while (true) {
                System.out.println("Which one do you choose?");
                System.out.println("[1] Stormrender\t[2] Galebound Grimoire\t[3] Soul of Vox");
                int choice = userChoice.nextInt();
                if (choice == 1) {
                    System.out.println("Walking forward, you claim the Stormrender, and watch as the other two items disintegrate.");
                    Dialogue.stall();
                    System.out.println("You learnt the new skill Galeforce Surge!");
                    System.out.println("Each time Galeforce Surge is used, your normal attacks will be empowered by winds generated by Stormrender!");
                    Dialogue.stall();
                    gfourthSpell = "Galeforce Surge";
                    gfourthSpellCost = 4;
                    gCurrentWeapon = gWeaponMap.get("stormrender");
                    return;
                }
                if (choice == 2) {
                    System.out.println("Walking forward, you claim the Galebound Grimoire, and watch as the other two items disintegrate.");
                    Dialogue.stall();
                    System.out.println("You learnt the new skill Zephyr's Veil!");
                    System.out.println("On top of providing armor, Zephyr's Veil also grants the caster mana regeneration!");
                    Dialogue.stall();
                    gfourthSpell = "Zephyr's Veil";
                    gfourthSpellCost = 20;
                    gCurrentWeapon = gWeaponMap.get("galeboundGrimoire");
                    return;
                }
                if (choice == 3) {
                    System.out.println("You choose to claim Vox's rapidly fading soul for yourself.");
                    Dialogue.stall();
                    System.out.println("On contact, you feel energy rush through your body, boosting your strength.");
                    System.out.println("You also feel an inner sense of peace.");
                    Dialogue.stall();
                    System.out.println("You learnt the new soulspell Soulstorm Convergence!");
                    System.out.println("Soulstorm Convergence is a powerful spell that scales off of inner peace. If you choose to consume inner peace, it changes the spell and does even more damage!");
                    Dialogue.stall();
                    gsoulspell = "Soulstorm Convergence";
                    gsoulspellCost = 12;
                    gsoulspellBoss = "Vox the Wind Elemental";
                    innerPeace = innerPeace + 3;
                    return;

                }
            }
        }
        if (Objects.equals(genName, "Khon the Stone Wall")) {
            Dialogue.khonReward();
            Scanner userChoice = new Scanner(System.in);
            while (true) {
                System.out.println("Which one do you choose?");
                System.out.println("[1] Stonewrought Maul\t[2] Runebound Earthshaker\t[3]Soul of Khon");
                int choice = userChoice.nextInt();
                if (choice == 1) {
                    System.out.println("Walking forward, you claim the Stonewrought Maul, and watch as the other two items disintegrate.");
                    Dialogue.stall();
                    System.out.println("You learnt the new skill Tectonic Annihilation!");
                    System.out.println("The skill Tectonic Annihilation has two forms: Seismic Surge and Cataclysmic Quake.");
                    System.out.println("Seismic Surge allows you to deal damage and gain stacks, while Cataclysmic Quake releases all those stacks to do additional damage.");
                    Dialogue.stall();
                    gfourthSpell = "Tectonic Annihilation";
                    gfourthSpellCost = 5;
                    gCurrentWeapon = gWeaponMap.get("stonewroughtMaul");
                    return;
                }
                if (choice == 2) {
                    System.out.println("Walking forward, you claim the Runebound Earthshaker.");
                    Dialogue.stall();
                    System.out.println("You learnt the new skill Earthen Torrent!");
                    System.out.println("Earthen Torrent is a spell that unleashes waves of earth and stone to crush enemies. When used, you can continue casting it as long as you have mana to do increased damage!");
                    Dialogue.stall();
                    gfourthSpell = "Earthen Torrent";
                    gfourthSpellCost = 6;
                    gCurrentWeapon = gWeaponMap.get("runeboundEarthshaker");
                    return;
                }
                if (choice == 3) {
                    System.out.println("You choose to claim Khon's rapidly fading soul for yourself.");
                    Dialogue.stall();
                    System.out.println("On contact, you feel energy rush through your body, boosting your strength.");
                    System.out.println("You also feel an inner sense of peace.");
                    Dialogue.stall();
                    System.out.println("You learnt the new soulspell Peacekeeper's Promise!");
                    System.out.println("Peacekeeper's Promise is a powerful spell that instantly boosts your stats for three turns, making you nearly invincible. However, if you fail to defeat the enemy within three turns, you'll instantly die. Thus, use it wisely!");
                    Dialogue.stall();
                    gsoulspell = "Peacekeeper's Promise";
                    gsoulspellCost = 0;
                    gsoulspellBoss = "Khon the Stone Wall";
                    innerPeace = innerPeace + 3;
                    return;
                }
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
        enemy = new Enemy("Buyaji, Eater of Worlds", 17, 100);
        gEnemyMap.put("buyaji", enemy);
        enemy = new Enemy("Khon the Stone Wall", 13, 150);
        gEnemyMap.put("khon", enemy);
        enemy = new Enemy("Vox the Wind Elemental", 18, 80);
        gEnemyMap.put("vox", enemy);
        enemy = new Enemy("Big Slime", 12, 80);
        gEnemyMap.put("bigSlime", enemy);
        enemy = new Enemy ("Chilly Slime", 15, 50);
        gEnemyMap.put("chillySlime", enemy);
        enemy = new Enemy ("Frostbite Fiend", 1, 80);
        gEnemyMap.put("frostbiteFiend", enemy);
        enemy = new Enemy ("Frostfire Golem", 11, 110);
        gEnemyMap.put("frostfireGolem", enemy);
        gCurrentEnemy = gEnemyMap.get("blueSlime");
    }
    private static void changeEnemy(double choice) {
        if (choice == 2) {
            gCurrentEnemy = gEnemyMap.get("redSlime");
        }
        if (choice == 3) {
            gCurrentEnemy = gEnemyMap.get("iridescentSlime");
        }
        if (choice == 4) {
            gCurrentEnemy = gEnemyMap.get("rocky");
        }
        if (choice  == 5.1 || choice == 5.2 || choice == 5.3) {
            gCurrentEnemy = gEnemyMap.get("stoneSentry");
        }
        if (choice == 6) {
            Random roll = new Random();
            int rand = roll.nextInt(2)+1;
            if (rand == 1) {
                gCurrentEnemy = gEnemyMap.get("animatedPuppet");
            }
            if (rand == 2) {
                gCurrentEnemy = gEnemyMap.get("ancientSlime");
            }
        }
        if (choice == 7.1) {
            gCurrentEnemy = gEnemyMap.get("buyaji");
            System.out.println("Adrenaline restores your stats!");
            gBaseHP = gMaxHP;
            gBaseMana = gMaxMana;
        }
        if (choice == 7.2 || choice == 7.21) {
            gCurrentEnemy = gEnemyMap.get("khon");
            System.out.println("Adrenaline restores your stats!");
            gBaseHP = gMaxHP;
            gBaseMana = gMaxMana;
        }
        if (choice == 7.3) {
            gCurrentEnemy = gEnemyMap.get("vox");
            System.out.println("Adrenaline restores your stats!");
            gBaseHP = gMaxHP;
            gBaseMana = gMaxMana;
        }
        if (choice == 8) {
            Random roll = new Random();
            int rand = roll.nextInt(4)+1;
            if (rand == 1) {
                gCurrentEnemy = gEnemyMap.get("bigSlime");
            }
            if (rand == 2) {
                gCurrentEnemy = gEnemyMap.get("chillySlime");
            }
            if (rand == 3) {
                gCurrentEnemy = gEnemyMap.get("frostbiteFiend");
            }
            if (rand == 4) {
                gCurrentEnemy = gEnemyMap.get("frostfireGolem");
            }
        }
        genName = gCurrentEnemy.getName();
        genMAttack = gCurrentEnemy.getAttack();
        genMHP = gCurrentEnemy.getHP();
        genHP = genMHP;
        genAttack = genMAttack;
    }
    private static void cleanseStatus() {
        gShadowDance = 0;
        gBaseDefense = 0;
        gResidualWinds = 0;
        gGaleforce = 0;
        gStoneboundStack = 0;
        gTectonicStack = 0;
    }
    private static void actions() {
        gwpAttack = gCurrentWeapon.getWeaponAttack();
        gwpMana = gCurrentWeapon.getWeaponMana();
        gwpMAttack = gCurrentWeapon.getWeaponMagicAttack();
        wpName = gCurrentWeapon.getWeaponName();
        gwpDefense = gCurrentWeapon.getWeaponDefense();
        if (gCurrentMana < 0) {
            gCurrentMana = 0;
        }
        if (gManaRegen > 0) {
            System.out.println("You regenerated " + gManaRegen + " mana!");
            gBaseMana = gBaseMana + gManaRegen;
            gManaRegen = gManaRegen -1;
            Dialogue.stall();
        }
        if (gResidualWinds > 0) {
            System.out.println("Residual winds harm you!");
            Dialogue.stall();
            System.out.println("You took " + gResidualWinds + " damage!");
            gBaseHP = gBaseHP - gResidualWinds;
            Dialogue.stall();
        }
        gCurrentMana = add(gwpMana, gBaseMana);
        if (gCurrentMana < 0) {
            gCurrentMana = 0;
        }
        while (true) {
            System.out.println("Your Level: " + gLevel + "\tYour stats:");
            System.out.println("ATK: " + add(gwpAttack, gBaseATK) + "\tMATK: " + add(gwpMAttack,gBaseMATK) + "\tHP: " + gBaseHP + "\tMana: " + gCurrentMana + "\tDefense: " + add(gwpDefense,gBaseDefense) + "\tWeapon: " + wpName);
            System.out.println("------------------------------");
            System.out.println("Enemy: " + genName + "\tEnemy Attack: " + genAttack + "\t Enemy HP: " + genHP);
            System.out.println("------------------------------");
            System.out.println("What would you like to do?");
            if (gsoulspell != null) {
                System.out.println("[A] To Attack  [S] to Bring up Spells list or [D] to Cast Your Soulspell");
            }
            else {
                System.out.println("[A] To Attack or [S] to Bring up Spells list");
            }
            Scanner userChoice = new Scanner(System.in);
            String userInput;
            boolean validInput = false;
            String userInputUpper = null;
            while (!validInput) {
                try {
                    userInput = userChoice.next();
                    userInputUpper = userInput.toUpperCase();
                    if (gsoulspell != null) {
                        if (userInputUpper.equals("A") || userInputUpper.equals("S") || userInputUpper.equals("D")) {
                            validInput = true;
                        }
                    }
                    else if (userInputUpper.equals("A") || userInputUpper.equals("S")) {
                        validInput = true;
                    }
                    else {
                        throw new InputMismatchException();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please input a valid answer.");
                }
            }
            if (userInputUpper.equals("A")) {
                System.out.println("You swing your " + wpName + "!");
                Dialogue.stall();
                if (gGaleforce > 0 && gGaleforce <= 10) {
                    System.out.println("The Galeforce guides your attack!");
                    System.out.println("You dealt " + (add(gwpAttack, gBaseATK)+gGaleforce) + " damage!");
                    Dialogue.stall();
                    genHP = genHP - (add(gwpAttack, gBaseATK)+gGaleforce);
                    return;
                }
                else if (gGaleforce > 10) {
                    System.out.println("The Galeforce fully empowers your attack!");
                    System.out.println("You dealt " + (add(gwpAttack, gBaseATK)+gGaleforce*2) + " damage!");
                    Dialogue.stall();
                    genHP = genHP - (add(gwpAttack, gBaseATK)+gGaleforce*2);
                    return;
                }
                if (wpName.equals("Venomspike Maw")) {
                    Random random = new Random();
                    int roll = random.nextInt(5)+1;
                    if (roll == 1) {
                        System.out.println("You've landed a critical hit!");
                        System.out.println("You dealt " + (add(gwpAttack, gBaseATK)*2) + " damage!");
                        Dialogue.stall();
                        genHP = genHP - (add(gwpAttack, gBaseATK)*2);
                        return;
                    }
                }
                System.out.println("You dealt " + add(gwpAttack, gBaseATK) + " damage!");
                Dialogue.stall();
                genHP = genHP - add(gwpAttack, gBaseATK);
                return;
            }
            else if (userInputUpper.equals("S")) {
                System.out.println("Which spell would you like to cast?");
                if (gfourthSpell != null) {
                    System.out.println("[1] " + gfirstSpell + " - " + gfirstSpellCost + "mp\t[2] Heal - 10mp\t[3] " + gthirdSpell + " - " + gthirdSpellCost + "mp\t[4] " + gfourthSpell + " - " + gfourthSpellCost + "mp");
                }
                else if (gthirdSpell != null) {
                    System.out.println("[1] " + gfirstSpell + " - " + gfirstSpellCost + "mp\t[2] Heal - 10mp\t[3] " + gthirdSpell + " - " + gthirdSpellCost + "mp");
                }
                else {
                    System.out.println("[1] " + gfirstSpell + " - " + gfirstSpellCost + "mp\t[2] Heal - 10mp");
                }
                userInput = userChoice.next();
                if (Objects.equals(userInput, "1") && gCurrentMana >= gfirstSpellCost && Objects.equals(gfirstSpell, "Fireball")) {
                        System.out.println("You cast Fireball!");
                        genHP = genHP - (gLevel * 4 + add(gwpMAttack,gBaseMATK));
                        Dialogue.stall();
                        System.out.println("You dealt " + (gLevel * 4 + add(gwpMAttack,gBaseMATK)) + " damage!");
                        gBaseMana = gBaseMana - 5;
                        Dialogue.stall();
                        return;
                }
                else if (Objects.equals(userInput, "1") && gCurrentMana >= gfirstSpellCost && Objects.equals(gfirstSpell, "Fortify")) {
                    System.out.println("You used Fortify!");
                    Dialogue.stall();
                    System.out.println("You gained " + gLevel + " points of defense!");
                    gBaseDefense = gBaseDefense + gLevel;
                    gBaseMana = gBaseMana - 3;
                    Dialogue.stall();
                    return;
                }
                else if (Objects.equals(userInput, "1") && gCurrentMana < gfirstSpellCost){
                  System.out.println("You don't have enough mana!");
                }
                if (Objects.equals(userInput, "2") && gCurrentMana >= 10) {
                    System.out.println("You cast Heal!");
                    gBaseHP = gBaseHP + (add(gwpMAttack,gBaseMATK)*2+add(gwpDefense,gBaseDefense)*2);
                    Dialogue.stall();
                    System.out.println("You healed " + (add(gwpMAttack,gBaseMATK)*2+add(gwpDefense,gBaseDefense)*2) + " HP!");
                    gBaseMana = gBaseMana - 10;
                    Dialogue.stall();
                    return;
                }
                else if (Objects.equals(userInput, "2") && gCurrentMana < 10){
                    System.out.println("You don't have enough mana!");
                }
                if (Objects.equals(userInput, "3") && gCurrentMana >= gthirdSpellCost && Objects.equals(gthirdSpell, "Mana Draw")) {
                    System.out.println("You cast Mana Draw!");
                    Dialogue.stall();
                    gBaseMana = gBaseMana + add(gwpMAttack,gBaseMATK);
                    System.out.println("You drew " + (add(gwpMAttack,gBaseMATK)) + " mana from the air!");
                    return;
                }
                if (Objects.equals(userInput, "3") && gCurrentMana >= gthirdSpellCost && Objects.equals(gthirdSpell, "Blood Shard")) {
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
                if (Objects.equals(userInput, "3") && gCurrentMana >= gthirdSpellCost && Objects.equals(gthirdSpell, "Shadow Dance")) {
                    System.out.println("You use Shadow Dance!");
                    Dialogue.stall();
                    System.out.println("Shadows engulf you as you unleash a flurry of attacks at the enemy, dealing " + (gShadowDance*5+add(gBaseATK,gwpAttack)) + " damage!");
                    Dialogue.stall();
                    System.out.println("The shadows strengthen!");
                    gBaseMana = gBaseMana - 3;
                    genHP = genHP - (gShadowDance*5+add(gBaseATK,gwpAttack));
                    gShadowDance = gShadowDance + 1;
                    return;
                }
                else if (Objects.equals(userInput, "3") && gCurrentMana < gthirdSpellCost && Objects.equals(gthirdSpell, "Shadow Dance")) {
                    System.out.println("You don't have enough mana!");
                }
                if (Objects.equals(userInput, "4") && gfourthSpell != null) {
                    if (gCurrentMana >= gfourthSpellCost) {
                        if (gfourthSpell.equals("Venomstrike")) {
                            System.out.println("You used Venomstrike!");
                            Dialogue.stall();
                            if (genHP > gBaseHP)  {
                                System.out.println("The venom deals critical damage to the stronger enemy!");
                                Dialogue.stall();
                                System.out.println("You dealt " + (add(gBaseATK, gwpAttack)*3) + " damage!");
                                Dialogue.stall();
                                genHP = genHP - (add(gBaseATK, gwpAttack)*3);
                                gBaseMana = gBaseMana - gfourthSpellCost;
                                return;
                            }
                            else {
                                System.out.println("You dealt " + (add(gBaseATK, gwpAttack)*2 + " damage!"));
                                Dialogue.stall();
                                genHP = genHP - (add(gBaseATK, gwpAttack)*2);
                                gBaseMana = gBaseMana - gfourthSpellCost;
                                return;
                            }
                        }
                        else if (gfourthSpell.equals("Wyrmrock Strike")) {
                            System.out.println("You cast Wyrmrock Strike!");
                            Dialogue.stall();
                            System.out.println("You launch out a series of three attacks!");
                            for (int i = 1; i < 4; i = i+1) {
                                Random random = new Random();
                                int roll = random.nextInt(5)+1;
                                System.out.println(genName + " was struck by rock number " + i + "!");
                                System.out.println("You dealt " + (add(gBaseMATK, gwpMAttack)+roll) + " damage!");
                                genHP = (genHP-add(gBaseMATK, gwpMAttack)-roll);
                                roll = random.nextInt(4)+1;
                                if (roll == 1) {
                                    System.out.println("Rocks solidify around you, increasing your defense!");
                                    Dialogue.stall();
                                    System.out.println("You gained 1 Defense!");
                                    gBaseDefense = gBaseDefense + 1;
                                }
                                Dialogue.stall();
                            }
                            gBaseMana = gBaseMana - gfourthSpellCost;
                            return;
                        }
                        else if (gfourthSpell.equals("Galeforce Surge")) {
                            System.out.println("You use Galeforce Surge!");
                            Dialogue.stall();
                            System.out.println("You dealt " + (add(gBaseATK, gwpAttack)*2+gLevel*2) + " damage!");
                            Random random = new Random();
                            int roll = random.nextInt(4)+1;
                            System.out.println("Your Galeforce strengthened by " + roll + "!");
                            gGaleforce = gGaleforce + roll;
                            genHP = genHP - (add(gBaseATK, gwpAttack)*2+gLevel*2);
                            Dialogue.stall();
                            gBaseMana = gBaseMana - gfourthSpellCost;
                            return;
                        }
                        else if (gfourthSpell.equals("Zephyr's Veil")) {
                            System.out.println("You cast Zephyr's Veil!");
                            Dialogue.stall();
                            System.out.println("You gained " + (add(gwpMAttack,gBaseMATK)/5) + " defense!");
                            Dialogue.stall();
                            System.out.println("You gained 4 mana regen!");
                            gBaseDefense = gBaseDefense + (add(gwpMAttack,gBaseMATK)/5);
                            gManaRegen = gManaRegen + 4;
                            gBaseMana = gBaseMana - gfourthSpellCost;
                            return;
                        }
                        else if (gfourthSpell.equals("Tectonic Annihilation")) {
                            System.out.println("You use Tectonic Annihilation!");
                            Dialogue.stall();
                            System.out.println("Do you want to use Seismic Surge or Cataclysmic Quake?");
                            System.out.println("Current Tectonic Stack(s): " + gTectonicStack);
                            System.out.println("[S] for Seismic Surge, [C] for Cataclysmic Quake");
                            userInput = userChoice.next();
                            userInputUpper = userInput.toUpperCase();
                            if (userInputUpper.equals("S")) {
                                System.out.println("You use Tectonic Annihilation: Seismic Surge!");
                                Dialogue.stall();
                                System.out.println("The earth tremors with power as you gather your power.");
                                Dialogue.stall();
                                System.out.println("You dealt " + (add(gBaseATK, gwpAttack)*2+gLevel) + " damage!");
                                System.out.println("Your seismic power increased!");
                                genHP = genHP - (add(gBaseATK, gwpAttack)*2+gLevel);
                                gTectonicStack = gTectonicStack + 1;
                                gBaseMana = gBaseMana - gfourthSpellCost;
                                return;
                            }
                            else if (userInputUpper.equals("C") && gTectonicStack != 0) {
                                System.out.print("You use Tectonic Annihilation: Cataclysmic Quake!");
                                System.out.println("You discharge all energy, dealing " + ((gLevel+15)*2*gTectonicStack) + " damage!");
                                Dialogue.stall();
                                genHP = genHP - ((gLevel+15)*2*gTectonicStack);
                                gTectonicStack = 0;
                                gBaseMana = gBaseMana - gfourthSpellCost;
                                return;
                            }
                            else if (userInputUpper.equals("C") && gTectonicStack <= 0) {
                                System.out.println("You have no Tectonic Stacks!");
                                System.out.println("You use Tectonic Annihilation: Seismic Surge instead!");
                                Dialogue.stall();
                                System.out.println("The earth tremors with power as you gather your power.");
                                Dialogue.stall();
                                System.out.println("You dealt " + (add(gBaseATK, gwpAttack)*2+gLevel) + " damage!");
                                System.out.println("Your seismic power increased!");
                                genHP = genHP - (add(gBaseATK, gwpAttack)*2+gLevel);
                                gTectonicStack = gTectonicStack + 1;
                                gBaseMana = gBaseMana - gfourthSpellCost;
                                return;
                            }
                        }
                        else if (gfourthSpell.equals("Earthen Torrent")) {
                            System.out.println("You cast Earthen Torrent!");
                            int EarthenTorrent = 0;
                            Dialogue.stall();
                            System.out.println("A flood of earth and stone torrent the enemy, dealing " + (add(gBaseMATK,gwpMAttack)+EarthenTorrent*gLevel) + " damage!");
                            genHP = genHP - (add(gBaseMATK,gwpMAttack)+EarthenTorrent);
                            gBaseMana = gBaseMana - 6;
                            EarthenTorrent = EarthenTorrent + 1;
                            while (true) {
                                if (gCurrentMana >= 6) {
                                    System.out.println("Do you want to continue the torrent?");
                                    System.out.println("[Y] for Yes, [N] for No");
                                    userInput = userChoice.next();
                                    userInputUpper = userInput.toUpperCase();
                                    if (userInputUpper.equals("Y")) {
                                        System.out.println("The torrent continues, dealing an additional " + (add(gBaseMATK,gwpMAttack)+EarthenTorrent*gLevel) + " damage!");
                                        genHP = genHP - (add(gBaseMATK,gwpMAttack)+EarthenTorrent);
                                        gBaseMana = gBaseMana - 6;
                                        EarthenTorrent = EarthenTorrent + 1;
                                    }
                                    else {
                                        System.out.println("The torrent stopped!");
                                        return;
                                    }
                                }
                                else {
                                    System.out.println("You ran out of mana, so the torrent stopped!");
                                    return;
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("You don't have enough mana!");
                    }
                }
            }
            else if (userInputUpper.equals("D")) {
                System.out.println("Your current soulspell is: " + gsoulspell + ", and will cost " + gsoulspellCost + "mp.");
                System.out.println("Do you want to cast the spell? [Y] for Yes, [N] for No.");
                userInput = userChoice.next();
                userInputUpper = userInput.toUpperCase();
                if (userInputUpper.equals("Y") && gCurrentMana >= gsoulspellCost) {
                    System.out.println("You call on " + gsoulspellBoss + "'s soul!" );
                    Dialogue.stall();
                    if (gsoulspell.equals("Eternal Devourer's Grasp")) {
                        System.out.println("You use the soulspell " + gsoulspell + "!");
                        if (genHP < gBaseHP) {
                            System.out.println("Mechanical tendrils and teeth launched from your hand to grab the enemy, consuming them into an eternal void!");
                            Dialogue.stall();
                            System.out.println("The enemy was one shot!");
                            Dialogue.stall();
                            System.out.println("You healed " + gLevel*5 + "HP as a result!");
                            genHP = 0;
                            gBaseMana = gBaseMana - gsoulspellCost;
                            return;
                        }
                        System.out.println("Mechanical tendrils and teeth are launched from your hand to grab the enemy, but they resist.");
                        Dialogue.stall();
                        System.out.println("You were still able to deal " + gLevel*11 + " damage.");
                        Dialogue.stall();
                        System.out.println("The spell casts it's toll on you!");
                        Dialogue.stall();
                        System.out.println("Your HP has been halved!");
                        Dialogue.stall();
                        gBaseHP = gBaseHP/2;
                        gBaseMana = gBaseMana - gsoulspellCost;
                        return;
                    }
                    else if (gsoulspell.equals("Soulstorm Convergence")) {
                        System.out.println("Do you want to consume inner peace?");
                        System.out.println("Current Inner Peace: " + innerPeace);
                        System.out.println("[Y] for Yes, [N] for No");
                        userInput = userChoice.next();
                        userInputUpper = userInput.toUpperCase();
                        if (userInputUpper.equals("Y")) {
                            System.out.println("You draw upon your inner peace to empower your spell.");
                            Dialogue.stall();
                            System.out.println("As you channel, huge dark clouds start amassing above your head, rumbling with power strong enough to make the ground shake.");
                            Dialogue.stall();
                            System.out.println("You cast Voxian Tempest Ascendance!");
                            Dialogue.stall();
                            System.out.println("The enemy is pummeled by winds and struck by lightning, dealing " + innerPeace*25 + " damage!");
                            Dialogue.stall();
                            genHP = genHP - innerPeace*25;
                            innerPeace = innerPeace - 1;
                            gBaseMana = gBaseMana - gsoulspellCost;
                            return;
                        }
                        else if (userInputUpper.equals("N")) {
                            System.out.println("Closing your eyes, you channel Vox's soul energy, and see clouds amass above your head. Opening your eyes, you unleash it's fury.");
                            Dialogue.stall();
                            System.out.println("You cast Soulstorm Convergence!");
                            Dialogue.stall();
                            System.out.println("You dealt " + innerPeace*17 + " damage!");
                            Dialogue.stall();
                            genHP = genHP - innerPeace*17;
                            gBaseMana = gBaseMana - gsoulspellCost;
                            return;
                        }
                    }
                    else if (gsoulspell.equals("Peacekeeper's Promise")) {
                        if (gPeacekeeperTime == -1) {
                            System.out.println("You've already called for power!");
                        }
                        else {
                            System.out.println("You use Peacekeeper's Promise!");
                            System.out.println("You were granted extreme power by the gods!");
                            System.out.println("You gained " + gLevel*3 + " ATK!");
                            System.out.println("You gained " + gLevel+1 + " Defense!");
                            System.out.println("You have three more turns left to live!");
                            return;
                        }
                    }
                }
                else {
                    System.out.println("You don't have enough mana!");
                }
            }
        }
    }
    private static void enemyTurn() {
        if (genHP  <= 0 ) {
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
            if (chance == 1) {
                enSpellCurse();
                return;
            }
            if (chance == 2) {
                enSpellSagesong();
                return;
            }
        }
        if (genName.equals("Buyaji, Eater of Worlds")) {
            if (genHP <= 30) {
                System.out.println("Buyaji roars furiously!");
            }
            Random attackChoice = new Random();
            int chance = attackChoice.nextInt(5)+1;
            if (chance == 1) {
                enSpellRockMunch();
                return;
            }
            if (chance == 2) {
                enSpellAvalanche();
                return;
            }
            if (chance == 3 || chance == 4) {
                enSpellRampage();
                return;
            }
        }
        if (genName.equals("Vox the Wind Elemental")) {
            if (genHP <= 20) {
                System.out.println("Primordial winds heal Vox!");
                System.out.println("Vox healed 5HP!");
                genHP = genHP + 5;
            }
            Random attackChoice = new Random();
            int chance = attackChoice.nextInt(5)+1;
            if (chance == 1) {
                enSpellAerobomb();
                return;
            }
            if (chance == 2) {
                enSpellHealingWinds();
                return;
            }
            if (chance == 3 || chance == 4) {
                enSpellWindEdge();
                return;
            }
        }
        if (genName.equals("Khon the Stone Wall")) {
            if (genHP <= 75) {
                System.out.println("Khon hums a carefree tune.");
            }
            Random attackChoice = new Random();
            int chance = attackChoice.nextInt(5)+1;
            if (chance == 1) {
                enSpellGigaStrike();
                return;
            }
            if (chance == 2) {
                enSpellShatteringImpact();
                return;
            }
            if (chance == 3) {
                enSpellStoneboundRenewal();
                return;
            }
            if (chance == 4 && genHP >= 50) {
                enSpellSunderingVigor();
                return;
            }
        }
        System.out.println("The " + genName + " attacks!");
        Dialogue.stall();
        if (add(gwpDefense,gBaseDefense) >= genAttack) {
            System.out.println("You took no damage!");
            return;
        }
        System.out.println("You took " + (genAttack-add(gwpDefense,gBaseDefense)) + " damage...");
        gBaseHP = gBaseHP - (genAttack-add(gwpDefense,gBaseDefense));
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
        if (add(gwpDefense,gBaseDefense) >= genHP/2) {
            System.out.println("You took no damage!");
            Dialogue.stall();
        }
        else {
            System.out.println("You took " + (genHP/2-add(gwpDefense,gBaseDefense)) + " damage...");
            gBaseHP = gBaseHP - (genHP/2-add(gwpDefense,gBaseDefense));
        }
    }
    private static void enSpellSelfDestruct() {
        System.out.println("The " + genName + " uses Self Destruct!");
        Dialogue.stall();
        System.out.println("The Rocky exploded!");
        Dialogue.stall();
        if (add(gwpDefense,gBaseDefense) >= 15) {
            System.out.println("You took no damage!");
            Dialogue.stall();
        }
        else {
            System.out.println("You took " + (15-add(gwpDefense,gBaseDefense)) + " damage...");
            gBaseHP = gBaseHP - (15-add(gwpDefense,gBaseDefense));
        }
        genHP = 0;
    }
    private static void enSpellReformat() {
        Random attackChoice = new Random();
        System.out.println("The " + genName + " uses Reformat!");
        Dialogue.stall();
        int damageRoll = attackChoice.nextInt(10)+1;
        if (add(gwpDefense,gBaseDefense) >= damageRoll*2) {
            System.out.println("You took no damage!");
            Dialogue.stall();
        }
        else {
            System.out.println("You took " + (damageRoll*2-add(gwpDefense,gBaseDefense)) + " damage!");
            gBaseHP = gBaseHP - (damageRoll*2-add(gwpDefense,gBaseDefense));
        }
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
        if (add(gwpDefense,gBaseDefense) >= damageRoll) {
            System.out.println("You took no damage!");
            Dialogue.stall();
        }
        else {
            System.out.println("You took " + (damageRoll-add(gwpDefense,gBaseDefense)) + " damage!");
            gBaseHP = gBaseHP - (damageRoll-add(gwpDefense,gBaseDefense));
        }
        System.out.println("The attack harmed the " + genName + " too!");
        System.out.println("The " + genName + " took 5 points of recoil!");
        genHP = genHP -5;
    }
    private static void enSpellMimic() {
        System.out.println("The " + genName + " uses Mimic!");
        Dialogue.stall();
        System.out.println("The " + genName + " copies your fireball spell!");
        Dialogue.stall();
        if (add(gwpDefense,gBaseDefense) >= (gLevel * 4 + add(gwpMAttack,gBaseMATK))) {
            System.out.println("You took no damage!");
            Dialogue.stall();
        }
        else {
            System.out.println("You took " + ((gLevel * 4 + add(gwpMAttack,gBaseMATK))-add(gwpDefense,gBaseDefense)) + " damage...");
            Dialogue.stall();
            gBaseHP = gBaseHP - ((gLevel * 4 + add(gwpMAttack,gBaseMATK))-add(gwpDefense,gBaseDefense));
        }
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
        gBaseMana = gBaseMana - genAttack/2;
    }
    private static void enSpellAvalanche() {
        System.out.println(genName + " uses Avalanche!");
        Dialogue.stall();
        System.out.println("Rocks start falling!");
        Dialogue.stall();
        Random roll = new Random();
        int rolled = roll.nextInt(3)+1;
        while (rolled != 1) {
            System.out.println("You were pummeled by rocks!");
            Dialogue.stall();
            if (add(gwpDefense,gBaseDefense) >= genAttack/2){
                System.out.println("You took no damage!");
                Dialogue.stall();
                rolled = roll.nextInt(3)+1;
            }
            else {
                System.out.println("You took " + (genAttack/2-add(gwpDefense,gBaseDefense)) + " damage!");
                gBaseHP = gBaseHP - (genAttack/2-add(gwpDefense,gBaseDefense));
                rolled = roll.nextInt(3)+1;
            }
            if (rolled == 1) {
                System.out.println("The rocks stopped falling!");
                return;
            }
            else {
                System.out.println("Rocks keep falling!");
            }
        }
        System.out.println("The rocks stopped falling!");
    }
    private static void enSpellRockMunch() {
        System.out.println(genName + " uses Rock Munch!");
        Dialogue.stall();
        System.out.println(genName + " ate the surrounding rocks!");
        Dialogue.stall();
        System.out.println(genName + " gained +2ATK!");
        Dialogue.stall();
        System.out.println(genName + " healed 5HP!");
        genHP = genHP + 5;
        genAttack = genAttack + 2;
    }
    private static void enSpellRampage() {
        System.out.println(genName + " goes on a complete rampage!");
        Dialogue.stall();
        if (add(gwpDefense,gBaseDefense) >= (genAttack+5)) {
            System.out.println("You took no damage!");
            Dialogue.stall();
        }
        else {
            System.out.println("You took " + (genAttack+5-add(gwpDefense,gBaseDefense)) + " damage!");
            Dialogue.stall();
            System.out.println(genName + " took 10HP of recoil!");
            genHP = genHP - 10;
            if (genHP < 0) {
                genHP = 0;
            }
            gBaseHP = gBaseHP - (genAttack+5-add(gwpDefense,gBaseDefense));
        }
    }
    private static void enSpellWindEdge() {
        System.out.println(genName + " uses Wind Edge!");
        Random roll = new Random();
        int rolled = genAttack+roll.nextInt(5)+1;
        if (add(gwpDefense,gBaseDefense) >= rolled) {
            System.out.println("You took no damage!");
            Dialogue.stall();
        }
        else {
            System.out.println("You took " + (rolled-add(gwpDefense,gBaseDefense)) + " damage!");
            Dialogue.stall();
            gBaseHP = gBaseHP - (rolled-add(gwpDefense,gBaseDefense));
        }
        System.out.println("Residual winds were kicked up!");
        Dialogue.stall();
        gResidualWinds = gResidualWinds+1;
    }
    private static void enSpellAerobomb() {
        if (gaerobombCharge == 0) {
            System.out.println(genName + " uses Aerobomb!");
            Dialogue.stall();
            System.out.println(genName + " started charging a powerful attack!");
            gaerobombCharge = 1;
        }
        else if (gaerobombCharge == 1) {
            System.out.println(genName + " uses Aerobomb!");
            System.out.println(genName + " unleashes their stored energy!");
            Dialogue.stall();
            if (add(gwpDefense,gBaseDefense) >= genAttack*2) {
                System.out.println("You took no damage!");
                Dialogue.stall();
            }
            else {
                System.out.println("Struck by the powerful vortex, you take " + (genAttack*2-add(gwpDefense,gBaseDefense)) + " damage!");
                Dialogue.stall();
                gBaseHP = gBaseHP - (genAttack*2-add(gwpDefense,gBaseDefense));
                System.out.println(genName + "\'s energy dissipated.");
                Dialogue.stall();
                gaerobombCharge = 0;
            }
        }
    }
    private static void enSpellHealingWinds() {
        System.out.println(genName + " uses Healing Winds!");
        Dialogue.stall();
        Random roll = new Random();
        int rolled = roll.nextInt(6)+20;
        System.out.println(genName + " healed " + rolled + "HP!");
        genHP = genHP + rolled;
        Dialogue.stall();
        System.out.println("You also healed 10HP!");
        gBaseHP = gBaseHP +10;
        Dialogue.stall();
    }
    private static void enSpellGigaStrike() {
        System.out.println(genName + " uses Giga Strike!");
        Dialogue.stall();
        System.out.println(genName + "crushes you under their massive fist!");
        Dialogue.stall();
        System.out.println("You took " + gBaseHP/4 + " damage!");
        gBaseHP = gBaseHP - gBaseHP/4;
    }
    private static void enSpellShatteringImpact() {
        System.out.println(genName + " uses Shattering Impact!");
        Dialogue.stall();
        if ((genAttack*2-10) <= add(gwpDefense,gBaseDefense)) {
            System.out.println("You took no damage!");
            Dialogue.stall();
        }
        else {
            System.out.println("You took " + ((genAttack*2-10)-add(gwpDefense,gBaseDefense)) + " damage!");
            gBaseHP = gBaseHP - ((genAttack*2-10)-add(gwpDefense,gBaseDefense));
        }
        Random random = new Random();
        int roll = random.nextInt(2)+1;
        System.out.println("Shockwaves created by Khon's fists reduce your defense!");
        System.out.println("You lost " + roll + " defense!");
        Dialogue.stall();
        gBaseDefense = gBaseDefense-roll;
    }
    private static void enSpellStoneboundRenewal() {
        System.out.println(genName + " uses Stonebound Renewal!");
        Dialogue.stall();
        System.out.println("A pulsating aura of earthy energy envelopes " + genName + " as they channel natrual energy.");
        Dialogue.stall();
        Random random = new Random();
        int roll = 5*(random.nextInt(3)+1);
        System.out.println(genName + " healed " + (roll+(gStoneboundStack*2)) + "HP!");
        Dialogue.stall();
        System.out.println(genName + "'s connection with nature strengthened!");
        genHP = genHP + (roll+(gStoneboundStack*2));
        gStoneboundStack = gStoneboundStack + 1;
        Dialogue.stall();
    }
    private static void enSpellSunderingVigor() {
        System.out.println(genName + " uses Sundering Vigor!");
        Dialogue.stall();
        System.out.println(genName + " taps into their inner vitality, sacrificing HP and gaining a surge of power!");
        Dialogue.stall();
        System.out.println(genName + " lost 20HP!");
        System.out.println(genName + " gained 3ATK!");
        genHP = genHP - 20;
        genAttack = genAttack + 3;
        Dialogue.stall();
    }
    private static void enSpellChillingWind() {
        System.out.println("The " + genName + " uses Chilling Wind!");
        Dialogue.stall();
        System.out.println("Cold winds pummel you, chilling you to the bone!");
        System.out.println("The Frigid Aura revitalizes the enemy!");
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
    public static int getTotalAttack() {
        return add(gBaseATK,gwpAttack);
    }
    public static int getTotalMAttack() {
        return add(gBaseMATK,gwpMAttack);
    }
}
