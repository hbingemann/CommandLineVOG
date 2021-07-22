package Combat.inputs;

import Combat.Attack;
import Combat.Battle;
import Combat.Fighter;

import java.util.ArrayList;

public class ComputerInput extends Input {

    @Override
    public Attack getAttackChoice(Fighter fighter) {

        ArrayList<Attack> attacks = fighter.getAttacks();

        // since it is a computer, right now it is just random number
        int choice = (int) (Math.random() * attacks.size());
        return attacks.get(choice);
    }

}
