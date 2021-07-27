import Combat.Fighter;
import Combat.battle.Battle;
import Combat.battle.TwoTeamBattle;
import Combat.display.DefaultDisplay;
import Combat.display.Display;
import Combat.health.Health;
import Combat.input.ComputerInput;
import Combat.input.PlayerInput;

import java.util.Scanner;

// libgdx would be a good graphics library if need be


/* idea for save data:
    Use the hashcode from System.getProperty("user.name") ->
    with that hashcode and the players current data ->
    encode a number that can be decoded and isn't too long.
    Later: ask user for number and decode it to load save data
 */

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

        String[] attacks = new String[] {"coolAttack", "lameAttack"};

        // as of now, battle automatically assigns teams, probably change later
        Battle battleComponent = new TwoTeamBattle();
        new Fighter(new PlayerInput(), new DefaultDisplay(), new Health(200), battleComponent, attacks, "Henrik");
        new Fighter(new ComputerInput(), new DefaultDisplay(), new Health(200), battleComponent, attacks, "Computer");
        battleComponent.run();
        System.out.println("Main received " + battleComponent.getWinners() + " as winner(s).");  // make sure proper winners returned

    }

    private static void WelcomePlayer(Scanner input) {
        // print out the welcoming messages
        System.out.println("Hello!\nWelcome to the 'Vault of Glass' in the console!");
        System.out.print("Hit <enter> to begin: ");
        input.nextLine();
    }

}
