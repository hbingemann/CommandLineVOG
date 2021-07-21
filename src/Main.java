import Combat.Battle;
import Combat.displays.DefaultDisplay;
import Combat.displays.Display;
import Combat.Fighter;

import java.util.Scanner;

// libgdx would be a good graphics library if need be

public class Main {

    public static void main(String[] args) {

        // get the scanner for input
        Scanner input = new Scanner(System.in);

        // Initialize the Encounters
        Encounter spire;
        Encounter confluxes;
        Encounter oracles;
        Encounter templar;
        Encounter gorgonsLabyrinth;
        Encounter gatekeeper;
        Encounter atheon;

        // Initialize RaidHandler
        RaidHandler raid;

        /* -- Begin the game -- */
        //WelcomePlayer(input);

        // testing things here

        /* idea for save data:
            Use the hashcode from System.getProperty("user.name") ->
            with that hashcode and the players current data ->
            encode a number that can be decoded and isn't too long.
            Later: ask user for number and decode it to load save data
         */

        Battle battle = new Battle(new DefaultDisplay(input));
        battle.addFighter(200, new String[] {"coolAttack", "lameAttack"}, "Henrik", true);
        battle.addFighter(200, new String[] {"coolAttack", "lameAttack"}, "Computer", false);
        Fighter winner = battle.run();
        System.out.println("Main received " + winner.getName() + " as winner.");  // make sure proper fighter returned

    }

    private static void WelcomePlayer(Scanner input) {
        // print out the welcoming messages
        System.out.println("Hello!\nWelcome to the 'Vault of Glass' in the console!");
        System.out.print("Hit <enter> to begin: ");
        input.nextLine();
    }

}
