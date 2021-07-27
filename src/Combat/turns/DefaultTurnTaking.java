package Combat.turns;

import Combat.Battle;
import Combat.Fighter;

import java.util.ArrayList;

public class DefaultTurnTaking extends TurnTaking{

    @Override
    public Fighter next(Battle battle) {
        // next turn
        turn++;

        // the two teams
        ArrayList<Fighter> teamA = battle.getTeamA();
        ArrayList<Fighter> teamB = battle.getTeamB();

        // swap between team A and team B
        if (turn % 2 == 1) {
            return teamA.get(turn / 2 % teamA.size());
        } else {
            return teamB.get(turn / 2 % teamB.size());
        }
    }

}
