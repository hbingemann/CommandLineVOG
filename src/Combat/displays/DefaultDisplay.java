package Combat.displays;

import Combat.Fighter;

import java.util.Scanner;

public class DefaultDisplay extends Display{

    private final Scanner input;

    // constructor
    public DefaultDisplay(Scanner input) {
        this.input = input;
    }

    // main display function
    @Override
    public void display() {
        // define a custom display of the turn
        // first assert necessary variables
        assert attacker != null &&
                attack != null &&
                attacked != null;

        // begin display by saying the attack used
        System.out.printf("%s used %s on %s!\n", attacker.getName(), attack.getName(), attacked.getName());

        // display what attack did to opponent
        if (deltaOpponentHealth < 0) {
            System.out.printf("It did %d damage!\n", Math.abs(deltaOpponentHealth));
        } else if (deltaOpponentHealth == 0) {
            System.out.print("It missed!\n");
        } else {
            System.out.printf("Oh no! %s healed %s for %d health!", attacker.getName(), attacked.getName(), Math.abs(deltaOpponentHealth));
        }

        // display if the attack did damage to self (or healed)
        if (deltaSelfHealth < 0) {
            System.out.printf("Oh no! %s did %d damage to themselves!\n", attacker.getName(), Math.abs(deltaSelfHealth));
        } else if (deltaSelfHealth > 0) {
            System.out.printf("%s healed themselves for %d health!\n", attacker.getName(), Math.abs(deltaSelfHealth));
        }

        // maybe display some status effect stuff here later

        // display the attacker's health
        System.out.printf("%s health: %d/%d \n%s\n",
                attacker.getName(),
                attacker.health.getHealth(), attacker.health.getMaxHealth(),
                getHealthVisual(attacker.health.getHealth(), attacker.health.getMaxHealth()));

        // display the attacked fighter's health
        System.out.printf("%s health: %d/%d \n%s\n",
                attacked.getName(),
                attacked.health.getHealth(), attacked.health.getMaxHealth(),
                getHealthVisual(attacked.health.getHealth(), attacked.health.getMaxHealth()));

        // if there is a winner display it
        if (attacked.health.getHealth() == 0) {
            System.out.printf("%s wins!\n", attacker.getName());
        } else if (attacker.health.getHealth() == 0) {
            System.out.printf("%s wins!\n", attacked.getName());
        }

        // ask the player to continue as they have now read through the results
        System.out.print("Hit <enter> to continue: ");
        input.nextLine();

        // reset output to show next turn
        System.out.println("CLEAR");
        System.out.print("\n\n\n");

    }

    // good function yeah yeah cool man
    private String getHealthVisual(int currentHealth, int maxHealth) {
        // change these
        int healthBarLength = 20;
        String healthCharacter = "#";
        String missingHealthCharacter = "-";
        // don't change these
        int healthLength = (int)(healthBarLength * ((double)currentHealth/(double)maxHealth));
        String healthVisual = "[";
        // should look something like this: "[################----]
        healthVisual += new String(new char[healthLength]).replace("\0", healthCharacter);
        healthVisual += new String(new char[healthBarLength - healthLength]).replace("\0", missingHealthCharacter);
        healthVisual += "]";
        return healthVisual;
    }

}
