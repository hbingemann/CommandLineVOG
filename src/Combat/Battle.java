package Combat;

import jdk.swing.interop.SwingInterOpUtils;
import org.json.JSONObject;

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

    private ArrayList<Fighter> fighters = new ArrayList<>();

    // function to be called when the battle should begin, returns the winning fighter and null if tie
    public Fighter run() {
        assert fighters.size() > 1;

        // begin the turn taking loop
        int turnNumber = 0;
        Fighter currentFighter = fighters.get(0);
        while (getAliveFighters().size() == fighters.size()) {
            currentFighter.takeTurn();
            System.out.print("Hit <enter> to continue: ");
            input.nextLine();
            // reset output to show next turn
            System.out.println("CLEAR");
            System.out.print("\n\n\n");
            turnNumber++;
            currentFighter = fighters.get(turnNumber % fighters.size());
        }
        return getWinner();
    }

    // function to initialize fighters
    public void initFighter(int maxHealth, String[] attackIds, String fighterName, boolean isPlayer) {
        // -- NOTE --: The order in which they are initialized is the order of their turns
        fighters.add(new Fighter(this, maxHealth, attackIds, fighterName, isPlayer));
    }

    // function to attack an opposing fighter
    public void damageOpponent(int damage, Fighter attacker) {
        Fighter opponent = getOpponent(attacker);
        assert opponent != null;
        opponent.health.takeDamage(damage);
        System.out.printf("%s now has %d/%d health:\n", opponent.getName(), opponent.health.getHealth(), opponent.health.getMaxHealth());
        System.out.println(opponent.health.getHealthVisual());
    }

    // get the opponent of a specific fighter
    private Fighter getOpponent(Fighter fighterWantingOpponent) {
        for (Fighter fighter: fighters) {
            if (!fighter.equals(fighterWantingOpponent)) {
                return fighter;
            }
        }
        return null;
    }

    // check for dead fighter, returns dead fighter if there is one, otherwise null
    private ArrayList<Fighter> getAliveFighters() {
        ArrayList<Fighter> aliveFighters = new ArrayList<>();
        for (Fighter fighter: fighters) {
            if (!fighter.health.isDead()) {
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
