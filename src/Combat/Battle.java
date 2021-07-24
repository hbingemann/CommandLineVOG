package Combat;

import Combat.display.Display;
import Combat.health.Health;
import Combat.input.Input;
import Combat.turns.TurnTaking;

import java.util.ArrayList;
import java.util.Scanner;

// main class for combat
public class Battle {

    // holds the input class
    public static final Scanner input = new Scanner(System.in);

    /*

    Attack example:

    Special Condition ideas:
        - opponent or self miss turn
        - take damage/heal over time
        - take more/less damage
        - do more/less damage
        - higher/lower attack miss chance
        (and any combinations)

    Attack ideas:
        - poison (void)
        - confusion (void)
        - melee (basic/default)
        - fire (solar)
        - lightning (arc)
        - lightning ball (arc)
        - lava (solar)

    Further ideas regarding attacks:
        - each attack solar/arc/void
        -
        - some sort of mechanic so that users are encouraged to use many different attacks
        - maybe make using a variety of attacks more effective than using only one
        - weaknesses to different types of attack
        - attack upgrades
        - max amount of specialConditions is 5
        - ESSENTIAL: make attacks balanced

     */

    private final ArrayList<Fighter> teamA = new ArrayList<>();
    private final ArrayList<Fighter> teamB = new ArrayList<>();
    private final Display displayComponent;
    private final TurnTaking turnTakingComponent;

    public Battle(Display displayComponent, TurnTaking turnTakingComponent) {
        this.turnTakingComponent = turnTakingComponent;
        this.displayComponent = displayComponent;
    }

    // function to be called when the battle should begin, returns the winning team and null if tie
    public void run() {
        assert teamA.size() >= 1 && teamB.size() >= 1; // make sure we have enough players

        // begin the turn taking loop
        while (getTeamAlive(teamA) && getTeamAlive(teamB)) {
            // have fighter take turn
            turnTakingComponent.next(this).takeTurn();
            // display
            displayComponent.display();
        }
    }

    // functions to initialize fighters
    public void addFighterTeamA(Input inputComponent, Health healthComponent, String[] attackIds, String name) {
        teamA.add(new Fighter(inputComponent, healthComponent, attackIds, name, this));
    }

    public void addFighterTeamB(Input inputComponent, Health healthComponent, String[] attackIds, String name) {
        teamB.add(new Fighter(inputComponent, healthComponent, attackIds, name, this));
    }

    // check if specific team alive
    private boolean getTeamAlive(ArrayList<Fighter> team) {
        for (Fighter fighter : team) {
            if (fighter.healthComponent.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    // return the winners, null if tie
    public ArrayList<Fighter> getWinningTeam() {
        boolean teamAAlive = getTeamAlive(teamA);
        boolean teamBAlive = getTeamAlive(teamB);
        // make sure this function isn't called when it shouldn't be
        assert !(teamAAlive && teamBAlive) : "No winning team yet. " +
                "Either 'getWinningTeam' was called too early or " +
                "the battle ended before a winner was declared.";
        if (teamAAlive) {
           return teamA; // team A wins!
        } else if (teamBAlive) {
            return teamB; // team B wins!
        } else {
            return null; // it was a tie
        }
    }

    // getters
    public Display getDisplayComponent() {
        return displayComponent;
    }

    public ArrayList<Fighter> getTeamA() {
        return teamA;
    }

    public ArrayList<Fighter> getTeamB() {
        return teamB;
    }

    public ArrayList<Fighter> getOpponents(Fighter fighter) {
        if (teamA.contains(fighter)) {
            return teamB;
        }
        return teamA;
    }
}
