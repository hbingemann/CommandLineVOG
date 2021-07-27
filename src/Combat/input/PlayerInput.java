package Combat.input;

import Combat.Attack;
import Combat.Fighter;
import Combat.Message;
import Combat.Messages;
import Debug.Debug;

import java.util.ArrayList;

public class PlayerInput extends Input {

    // for choosing attack
    public Attack getAttackChoice(Fighter fighter) {

        ArrayList<Attack> attacks = fighter.getAttacks();

        // clear for space to show attacks
        Debug.clearScreen();

        // display attacks
        System.out.println("Your Attacks: \n");
        for (int i = 0; i < attacks.size(); i++) {
            Attack attack = attacks.get(i);
            System.out.printf("%d. %s \n", i + 1, attack.getName());
            System.out.println("\t" + attack.getDescription());
            System.out.println();
        }

        // get input
        while (true) {
            try {
                System.out.print("Enter attack number: ");
                int userChoice = Integer.parseInt(input.nextLine());
                return attacks.get(userChoice - 1);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid input. Try Again.");
            }
        }
    }

    // for choosing opponent
    public Fighter getTargetChoice(Fighter fighter) {
        ArrayList<Fighter> opponents = fighter.getOpponents();
        if (opponents.size() == 1) {
            return opponents.get(0);  // only one choice so it would be redundant to ask the user for opponent choice
        } else {
            return null; // TODO: implement opponent choosing with multiple opponents
        }
    }

}
