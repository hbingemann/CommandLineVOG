package Combat.inputs;

import Combat.Attack;
import Combat.Battle;
import Combat.Fighter;

import java.util.ArrayList;

public class PlayerInput extends Input {

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

}
