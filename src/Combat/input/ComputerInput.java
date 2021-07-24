package Combat.input;

import Combat.Attack;
import Combat.Fighter;

import java.util.ArrayList;

public class ComputerInput extends Input {

    // for choosing attack
    @Override
    public Attack getAttackChoice(Fighter fighter) {

        ArrayList<Attack> attacks = fighter.getAttacks();

        // since it is a computer, right now it is just random number
        int choice = (int) (Math.random() * attacks.size());
        return attacks.get(choice);
    }

    // for choosing opponent
    @Override
    public Fighter getOpponentChoice(Fighter fighter) {

        ArrayList<Fighter> opponents = fighter.getOpponents();

        // since it is a computer, right now it is just random number
        int choice = (int) (Math.random() * opponents.size());
        return opponents.get(choice);

    }

}
