package Combat.battle;

import Combat.Fighter;
import Combat.Message;

import java.util.ArrayList;

// this is a battle with two teams
public class TwoTeamBattle extends Battle {

    /*
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
    private int turn = 0;

    // function to be called when the battle should begin
    @Override
    public void run() {
        // make sure we have enough players
        assert teamA.size() >= 1 && teamB.size() >= 1 : "Not enough fighters to begin the battle.";

        // while loop for battle
        while (getTeamAlive(teamA) && getTeamAlive(teamB)) {
            nextFighter().takeTurn();
        }
    }

    // function for next fighter to take their turn
    private Fighter nextFighter() {

        turn++;
        Fighter nextFighter;

        // swap between team A and team B
        if (turn % 2 == 1) {
            nextFighter = teamA.get(turn / 2 % teamA.size());
        } else {
            nextFighter = teamB.get(turn / 2 % teamB.size());
        }
        return nextFighter;

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
    @Override
    public ArrayList<Fighter> getWinners() {
        boolean teamAAlive = getTeamAlive(teamA);
        boolean teamBAlive = getTeamAlive(teamB);
        // make sure this function isn't called when it shouldn't be
        assert !(teamAAlive && teamBAlive) : "No winning team yet. " +
                "Either this function was called too early or " +
                "the battle ended before a winner was declared.";
        if (teamAAlive) {
            return teamA; // team A wins!
        } else if (teamBAlive) {
            return teamB; // team B wins!
        } else {
            return null; // it was a tie
        }
    }

    // register a fighter in the battle
    @Override
    public void registerFighter(Fighter fighter) {
        // add fighters to team B if team A already has more fighters
        if (teamA.size() > teamB.size()) {
            teamB.add(fighter);
        } else {
            teamA.add(fighter);
        }
    }

    // getters
    public ArrayList<Fighter> getTeamA() {return teamA; }
    public ArrayList<Fighter> getTeamB() { return teamB; }
    @Override
    public ArrayList<Fighter> getOpponents(Fighter fighter) { return teamA.contains(fighter) ? teamB : teamA; }
}
