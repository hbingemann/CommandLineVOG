import Combat.Battle;
import Combat.display.DefaultDisplay;
import Combat.Fighter;
import Combat.display.Display;
import Combat.health.Health;
import Combat.input.ComputerInput;
import Combat.input.Input;
import Combat.input.PlayerInput;
import Combat.turns.DefaultTurnTaking;
import Combat.turns.TurnTaking;

import java.util.ArrayList;
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

        Display displayComponent = new DefaultDisplay();
        String[] attacks = new String[] {"coolAttack", "lameAttack"};
        Health healthComponent = new Health(200);
        TurnTaking turnTakingComponent = new DefaultTurnTaking();

        Battle battle = new Battle(turnTakingComponent);
        battle.addFighterTeamA(new PlayerInput(), displayComponent, healthComponent, attacks, "Henrik");
        battle.addFighterTeamB(new ComputerInput(), displayComponent, healthComponent, attacks, "Computer");
        battle.run();
        System.out.println("Main received " + battle.getWinningTeam().toString() + " as winners.");  // make sure proper winners returned

    }

    private static void WelcomePlayer(Scanner input) {
        // print out the welcoming messages
        System.out.println("Hello!\nWelcome to the 'Vault of Glass' in the console!");
        System.out.print("Hit <enter> to begin: ");
        input.nextLine();
    }

}
