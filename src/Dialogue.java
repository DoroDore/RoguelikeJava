import java.util.InputMismatchException;
import java.util.Scanner;

public class Dialogue {
    public static int introduction () {
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
        }
        else if (name.equals("Bacon")) {
            System.out.println("Bacon Detected");
            System.out.println("Well, let us embark on a new journey... \n\n\n\n\n------------------------------");
        }
        else if (name.equals("ben")) {
            System.out.println("Nah, you're Nate");
            System.out.println("What a clown... \n\n\n\n\n------------------------------");
        }
        else if (name.equals("www")) {
            System.out.println("Speedrun mode activated.");
            return 1;
        }
        else {
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
            return 0;
        }
        else {
            System.out.println("Very well. We shall begin.");
            stall();
            return 0;
        }
    }
    public static void stall () {
        Scanner stall = new Scanner(System.in);
        stall.nextLine();
    }
    public static void preFight() {
        System.out.println("\n\n\n\n\n------------------------------");
        System.out.println("You wake up in a strange forest, with nothing but a sharpened wooden stick.");
        stall();
        System.out.println("Suddenly, you see something coming towards you.");
        stall();
        System.out.println("Cautiously, you pick up your sharpened stick and creep towards the entity.");
        stall();
        System.out.println("You encountered a blue slime!");
    }

    public static void death() {
        System.out.println("You fought hard, but you're eventually overrun by the powerful enemy ahead of you...");
        stall();
        System.out.println("You died!");
        stall();
        System.out.println("Your inner peace level: " + Main.getPeace());
    }
    public static void enemyDefeated() {

    }
    public static void wave3Reward1() {
        System.out.println("Closing your eyes, you place your hand to the stone.");
        stall();
        System.out.println("As you channel mana into the stone, you see a blue glow resonate from the stone, and you feel power flood through your body.");
        stall();
        System.out.println("Your Magic Attack increased by 3!");
        stall();
    }
    public static void wave3Reward2() {
        System.out.println("Nabbing the stone, you toss it to the ground before crushing it with your foot.");
        stall();
        System.out.println("Beneath your foot, you see a red light erupt, completely blinding you.");
        stall();
        System.out.println("When the light subsides, you feel your body coursing with raw power.");
        stall();
        System.out.println("Your Attack increased by 3!");
        stall();
    }
    public static void buyajiReward() {
        System.out.println("With a final attack, you strike down Buyaji.");
        stall();
        System.out.println("Buyaji, enraged, lets out a final roar of anger before toppling to the floor, causing the entire floor to shake.");
        stall();
        System.out.println("Exhausted, you can't help but fall to the floor, realizing the magnitude of the feat you've just accomplished.");
        stall();
        System.out.println("Then, suddenly, Buyaji's body starts contorting and twisting. Suddenly on edge, you force yourself back up, weakly grabbing your weapon");
        stall();
        System.out.println("Then, Buyaji's body explodes.");
        stall();
        System.out.println("In the ruin of Buyaji's body lies three items.");
        stall();
    }
    public static void wave4Dialogue() {
        System.out.println("As you continue your journey, you notice the trees around you eventually thin.");
        stall();
        System.out.println("Taking a final step, you realize you've escaped the strange forest.");
        stall();
        System.out.println("Scratching your head, you're not sure what to do until you see a temple in the distance.");
        stall();
        System.out.println("Grabbing your weapon, you heave yourself up and head towards the mysterious structure.");
        stall();
        System.out.println("As you walk, you take the time to relax and restore your energy.");
        stall();
        System.out.println("Your stats have been restored");
        stall();
        System.out.println("You've leveled up!");
    }
    public static double prefightDialogue() {
        if (Main.getWave() == 1 || Main.getWave() == 2 || Main.getWave() == 3 || Main.getWave() == 4) {
            System.out.println("Satisfied, you continue your journey.");
            Dialogue.stall();
            System.out.println("Before long, another enemy stops to block your path.");
            Dialogue.stall();
            System.out.println("You encountered " + Main.getGenName() + "!");
            Dialogue.stall();
            System.out.println("\n\n\n\n\n------------------------------");
            return Main.getWave();
        }
        if (Main.getWave() == 5) {
            System.out.println("As you walk into the temple, you notice a figure-like stone structure guarding the temple");
            stall();
            System.out.println("Unsure of what it does, you just stand there.");
            stall();
            while (true) {
                System.out.println("What do you want to do?");
                stall();
                System.out.println("[1] Attack the statue\t[2] Tap the statue\t[3] Ignore the statue");
                Scanner scanner = new Scanner(System.in);
                int userInput = scanner.nextInt();
                if (userInput == 1) {
                    System.out.println("You raise your weapon high before swinging it down on the stone structure");
                    stall();
                    System.out.println("With a start, the robot whirls to life.");
                    return 5.1;
                }
                if (userInput == 2) {
                    System.out.println("Learning over, you tap the stone structure on what could be classified as it's shoulder");
                    stall();
                    System.out.println("Suddenly, the stone structure starts humming");
                    stall();
                    System.out.println("StoneGUARD V2.3 [Operation Level: FULL FUNCTION] | [Battery: REPAIR REQUIRED]");
                    stall();
                    System.out.println("The robot doesn't seem to have noticed you yet.");
                    stall();
                    while (true) {
                        System.out.println("What do you want to do?");
                        System.out.println("[1] Attack the sentry\t[2] Try talking to the sentry\t[3] Sneak past the sentry");
                        userInput = scanner.nextInt();
                        if (userInput == 1) {
                            System.out.println("Raising your weapon, you swing it down at the sentry, only for it to be blocked by a mechanical arm.");
                            stall();
                            System.out.println("Leaping back, you brace yourself for a fight.");
                            return 0;
                        }
                        if (userInput == 2) {
                            System.out.println("You try talking to the sentry");
                            stall();
                            System.out.println("Stone Sentry: \"Unregistered entity detected [Database: Null]\"");
                            stall();
                            System.out.println("Suddenly, the Stone Sentry starts making a whirring noise and steam starts coming out of it's joints.");
                            stall();
                            while (true) {
                                System.out.println("What do you want to do?");
                                System.out.println("[1] Attack the sentry\t[2] Try to logic the sentry (Level Check)");
                                userInput = scanner.nextInt();
                                if (userInput == 1) {
                                    System.out.println("Raising your weapon, you swing it down at the sentry, only for it to be blocked by a mechanical arm.");
                                    stall();
                                    System.out.println("Leaping back, you brace yourself for a fight.");
                                    return 0;
                                }
                                if (userInput == 2) {
                                    if (Main.getLevel() >= 3) {
                                        System.out.println("As the robot continues to whir, you think hard about how to stop it.");
                                        stall();
                                        System.out.println("Suddenly, you're brain is filled with mysterious knowledge, and the engineering of the sentry seems clear as day");
                                        stall();
                                        System.out.println("With the raise of your finger, you utter \"StoneGuard, configure, friendlyUnit, add, register!\"");
                                        stall();
                                        System.out.println("To your surprise, the robot stops whirring.");
                                        stall();
                                        System.out.println("Shrugging to yourself, you just walk past the robot, which stands next to you obediently");
                                        return 5.2;
                                    }
                                }
                            }
                        }

                    }
                }
                if (userInput == 3) {
                    System.out.println("You try sneaking past the sentry...");
                    stall();
                    System.out.println("Suddenly, you hear a loud sound behind you: ENEMY DETECTED [Loading Combat Ver 5DX]");
                    stall();
                    System.out.println("You've been ambushed by the sentry!");
                    return 5.3;
                }
                else {
                    System.out.println("PLease input a valid answer");
                }
            }
        }
        if (Main.getWave() == 6) {
            stall();
            System.out.println("As you continue adventuring however, your path is eventually blocked.");
            return 6;
        }
        if (Main.getWave() == 7) {
            System.out.println("Having chosen a book, you watch in shock as the other two disintegrate before your eyes");
            stall();
            System.out.println("You shrug, figuring that you at least got something from the temple. Turning around, you prepare to leave.");
            stall();
            System.out.println("However, right as you're able to step out of the central chamber, a gate suddenly comes crashing down, narrowly missing your feet and barring you from exit.");
            stall();
            while (true) {
                System.out.println("What do you want to do?");
                System.out.println("[1] Try breaking the gate (ATK Check)\t[2] Try to figure your way out (MATK Check)\t[3] Do nothing");
                Scanner scanner = new Scanner(System.in);
                int userInput = scanner.nextInt();
                if (userInput == 1) {
                    if (Main.getTotalAttack() >= 10) {
                        System.out.println("Bracing your body, you ram into the gate shoulder first, and stumble forward as you realize the old gate gave way.");
                        stall();
                        System.out.println("When the smoke finally clears, you smile seeing that the exit to the temple is just down the hallway.");
                        stall();
                        System.out.println("Sprinting down the hallway, you're just about to exit, when a huge mechanical worm stops your path");
                        stall();
                        System.out.println("Stumbling backwards in shock, you can help but tremble at the strength of the enemy before you.");
                        stall();
                        System.out.println("You've encountered Buyaji, Eater of Worlds!");
                        return 7.1;
                    }
                    else {
                        System.out.println("Bracing your body, you ram into the wall shoulder first, only to realize with a painful jolt that your actions did more to your shoulder than the gate, which hasn't budged.");
                        stall();
                        System.out.println("As you rub your sore shoulder, you suddenly feel the floor beneath you tremble. As a tile in the floor slides open, you realize there's something rising out of the ground.");
                        stall();
                        System.out.println("Upon closer inspection, it's a huge ruin sentry, at least three times as large as the stone one at the entrance to the temple.");
                        stall();
                        System.out.println("INTRUDER DETECTED. MUST DESTROY. ENGAGING [KHON BATTLE MODE]");
                        stall();
                        System.out.println("\"I AM KHON\" the golem bellows. \"YOU HAVE ENTERED FORBIDDEN TERRITORY, AND YOU WILL PAY THE PRICE!\"");
                        stall();
                        System.out.println("You've encountered Khon the Stone Wall!");
                        return 7.2;
                    }
                }
                if (userInput == 2) {
                    if (Main.getTotalMAttack() >= 12) {
                        System.out.println("Closing your eyes, you sense the mana around you.");
                        stall();
                        System.out.println("Though it's faint, you detect a sense of mana flowing from the wall opposite you.");
                        stall();
                        System.out.println("Instinctively, your fingers trace over the wall, and you realize there's a hidden button in the wall.");
                        stall();
                        while (true) {
                            System.out.println("What do you want to do?");
                            System.out.println("[1] Press the button\t[2] Don't press the button");
                            userInput = scanner.nextInt();
                            if (userInput == 1) {
                                System.out.println("Your curiosity wins over you, and you lean forward to press the button.");
                                stall();
                                System.out.println("Suddenly, you feel the room shake as the entire back wall opens up, revealing a shriveled old man.");
                                stall();
                                System.out.println("\"Hello?\" you ask");
                                stall();
                                System.out.println("The man starts babbling. However, before you can ask him what he's saying, his body erupts with cracks of green light, glowing brighter and brighter until you can barely see anything.");
                                stall();
                                System.out.println("Then, the light subsides. In front of you, stands a huge green elemental.");
                                stall();
                                System.out.println("\"I AM VOX THE WIND ELEMENTAL!\"");
                                stall();
                                System.out.println("Brandishing your weapon, you prepare for a long fight ahead.");
                                stall();
                                System.out.println("You've encountered Vox the Wind Elemental!");
                                return 7.3;
                            }
                            if (userInput == 2) {
                                System.out.println("In the end, you decide not to press the button, instead opting to sit down and ignore the trace of mana instead.");
                                stall();
                                System.out.println("As you sit around, you feel an inner sense of peace.");
                                stall();
                                System.out.println("Suddenly, you feel the floor beneath you tremble. As a tile in the floor slides open, you realize there's something rising out of the ground.");
                                stall();
                                System.out.println("Upon closer inspection, it's a huge ruin sentry, at least three times as large as the stone one at the entrance to the temple.");
                                stall();
                                System.out.println("INTRUDER DETECTED. MUST DESTROY. ENGAGING [KHON BATTLE MODE]");
                                stall();
                                System.out.println("\"I AM KHON\" the golem bellows. \"YOU HAVE ENTERED FORBIDDEN TERRITORY, AND YOU WILL PAY THE PRICE!\"");
                                stall();
                                System.out.println("You've encountered Khon the Stone Wall!");
                                return 7.21;
                            }

                        }
                    }
                    else {
                        System.out.println("Closing your eyes, you try to scan the room for any sort of magical traces.");
                        stall();
                        System.out.println("However, after a minute or two of searching, you are unable to find any.");
                        stall();
                        System.out.println("Deflated, you sag to the floor.");
                        stall();
                        System.out.println("Suddenly, you feel the floor beneath you tremble. As a tile in the floor slides open, you realize there's something rising out of the ground.");
                        stall();
                        System.out.println("Upon closer inspection, it's a huge ruin sentry, at least three times as large as the stone one at the entrance to the temple.");
                        stall();
                        System.out.println("INTRUDER DETECTED. MUST DESTROY. ENGAGING [KHON BATTLE MODE]");
                        stall();
                        System.out.println("\"I AM KHON\" the golem bellows. \"YOU HAVE ENTERED FORBIDDEN TERRITORY, AND YOU WILL PAY THE PRICE!\"");
                        stall();
                        System.out.println("You've encountered Khon the Stone Wall!");
                        return 7.2;

                    }
                }
                if (userInput == 3) {
                    System.out.println("Sitting down, you decide to do nothing.");
                    stall();
                    System.out.println("As you sit around, you feel an inner sense of peace.");
                    stall();
                    System.out.println("Suddenly, you feel the floor beneath you tremble. As a tile in the floor slides open, you realize there's something rising out of the ground.");
                    stall();
                    System.out.println("Upon closer inspection, it's a huge ruin sentry, at least three times as large as the stone one at the entrance to the temple.");
                    stall();
                    System.out.println("INTRUDER DETECTED. MUST DESTROY. ENGAGING [KHON BATTLE MODE]");
                    stall();
                    System.out.println("\"I AM KHON\" the golem bellows. \"YOU HAVE ENTERED FORBIDDEN TERRITORY, AND YOU WILL PAY THE PRICE!\"");
                    stall();
                    System.out.println("You've encountered Khon the Stone Wall!");
                    return 7.21;
                }
            }
        }
        return 0;
    }
}
