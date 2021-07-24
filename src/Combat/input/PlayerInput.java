package Combat.input;

import Combat.Attack;
import Combat.Battle;
import Combat.Fighter;

import java.util.ArrayList;

public class PlayerInput extends Input {

    // for choosing attack
    @Override
    public Attack getAttackChoice(Fighter fighter) {

        ArrayList<Attack> attacks = fighter.getAttacks();

        // clear to allow for space to show attacks
        System.out.println("CLEAR");
        System.out.println("\n\n\n");

        // display attacks
        System.out.println("Your Attacks: \n");
        for (int i = 0; i < attacks.size(); i++) {
            Attack attack = attacks.get(i);
            System.out.printf("%d. %s \n", i + 1, attack.getName());
            System.out.println("\t" + attack.getDescription());
            System.out.println();
        }

        // get input
        System.out.print("Enter attack number: ");
        return attacks.get(Integer.parseInt(Battle.input.nextLine()) - 1);
    }

    // for choosing opponent
    @Override
    public Fighter getOpponentChoice(Fighter fighter) {
        ArrayList<Fighter> opponents = fighter.getOpponents();
        if (opponents.size() == 1) {
            return opponents.get(0);  // only one choice so it would be redundant to ask the user for opponent choice
        } else {
            return null; // TODO: implement opponent choosing
        }
    }

}
