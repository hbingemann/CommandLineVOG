package Combat.input;

import Combat.Attack;
import Combat.Fighter;
import Combat.Message;
import Combat.Messages;

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
    public Fighter getTargetChoice(Fighter fighter) {

        ArrayList<Fighter> targets = fighter.getOpponents();

        // since it is a computer, right now it is just random number
        int choice = (int) (Math.random() * targets.size());
        return targets.get(choice);

    }

}
