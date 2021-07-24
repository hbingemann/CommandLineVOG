package Combat.turns;

import Combat.Battle;
import Combat.Fighter;

import java.util.ArrayList;

public class DefaultTurnTaking extends TurnTaking{

    @Override
    public Fighter next(Battle battle) {
        // next turn
        turn++;

        ArrayList<Fighter> teamA = battle.getTeamA();
        ArrayList<Fighter> teamB = battle.getTeamB();

        if (turn % 2 == 1) {
            return teamA.get(turn / 2 % teamA.size());
        } else {
            return teamB.get(turn / 2 % teamB.size());
        }
    }

}
