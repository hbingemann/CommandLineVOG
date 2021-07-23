package Combat;

import Combat.displays.Display;
import Combat.health.Health;
import Combat.inputs.Input;

import java.util.ArrayList;
import java.util.Scanner;

// main class for combat
public class Battle {

    // holds the input class
    public static final Scanner input = new Scanner(System.in);

    /*

    Attack example:
    private static Attack poison;

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

    // role of battle is:
    // - to initialize the fighters (DONE)
    // - be an access point to classes outside of the package (DONE)
    // - to handle the battle

    private final ArrayList<Fighter> fighters = new ArrayList<>();
    private final Display displayComponent;

    public Battle(Display displayComponent) {
        this.displayComponent = displayComponent;
    }

    // function to be called when the battle should begin, returns the winning fighter and null if tie
    public Fighter run() {
        assert fighters.size() > 1;

        // begin the turn taking loop
        int turnNumber = 0;
        Fighter currentFighter;
        while (getAliveFighters().size() == fighters.size()) {
            currentFighter = fighters.get(turnNumber % fighters.size());
            currentFighter.takeTurn();
            turnNumber++;
            // display then ask for conformation to continue
            displayComponent.display();
            System.out.print("Hit <enter> to continue: ");
            input.nextLine();
        }
        // end of match, return the winner and null if its a tie
        return getWinner();
    }

    // function to initialize fighters
    public void addFighter(Input inputComponent, Health healthComponent, String[] attackIds, String name) {
        // -- NOTE -- The order in which they are initialized is the order of their turns
        fighters.add(new Fighter(displayComponent, inputComponent, healthComponent,this, attackIds, name));
    }

    // change this, please i beg
    public void damageOpponent(int damage, Fighter attacker) {
        Fighter opponent = getOpponent(attacker);
        assert opponent != null;
        opponent.healthComponent.takeDamage(damage);
        // give display some things
        displayComponent.setOpponent(opponent);
        displayComponent.setDeltaOpponentHealth(-damage);  // negative damage is the change in health
    }

    // get the opponent of a specific fighter
    private Fighter getOpponent(Fighter fighterWantingOpponent) {
        for (Fighter fighter: fighters) {
            if (!fighter.equals(fighterWantingOpponent)) {
                return fighter;
            }
        }
        return null;
    }  // top ten worstest functions

    // check for dead fighter, returns dead fighter if there is one, otherwise null
    private ArrayList<Fighter> getAliveFighters() {
        ArrayList<Fighter> aliveFighters = new ArrayList<>();
        for (Fighter fighter: fighters) {
            if (fighter.healthComponent.getHealth() > 0) {
                aliveFighters.add(fighter);
            }
        }
        return aliveFighters;
    }

    // return the winner
    private Fighter getWinner() {
        ArrayList<Fighter> aliveFighters = getAliveFighters();
        return aliveFighters.isEmpty() ? null : aliveFighters.get(0);
    }

}
