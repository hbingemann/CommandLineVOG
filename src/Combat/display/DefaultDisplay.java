package Combat.display;

import Combat.Fighter;
import Combat.Message;
import Combat.Messages;
import Debug.Debug;
import org.jetbrains.annotations.NotNull;

public class DefaultDisplay extends Display{

    // main display function
    @Override
    public void handle(Fighter fighter) {
        // define a custom display of the turn
        // first assert necessary variables
        assert attacker != null &&
                attack != null &&
                target != null &&
                deltaTargetHealth != null &&
                deltaAttackerHealth != null : "The Display is missing a variable!";


        // reset output to show this turn
        Debug.clearScreen();

        // begin display by saying the attack used
        System.out.printf("%s used %s on %s!\n", attacker.getName(), attack.getName(), target.getName());

        // display what attack did to opponent
        if (deltaTargetHealth < 0) {
            System.out.printf("It did %d damage!\n", Math.abs(deltaTargetHealth));
        } else if (deltaTargetHealth == 0) {
            System.out.print("It missed!\n");
        } else {
            System.out.printf("Oh no! %s healed %s for %d health!", attacker.getName(), target.getName(), Math.abs(deltaTargetHealth));
        }
        System.out.println();

        // display if the attack did damage to self (or healed)
        if (deltaAttackerHealth < 0) {
            System.out.printf("Oh no! %s did %d damage to themselves!\n", attacker.getName(), Math.abs(deltaAttackerHealth));
        } else if (deltaAttackerHealth > 0) {
            System.out.printf("%s healed themselves for %d health!\n", attacker.getName(), Math.abs(deltaAttackerHealth));
        }

        // maybe display some status effect stuff here later

        // display the attacker's health
        System.out.printf("%s health: %d/%d \n%s\n",
                attacker.getName(),
                attacker.healthComponent.getHealth(), attacker.healthComponent.getMaxHealth(),
                getHealthVisual(attacker));
        System.out.println();

        // display the opponent fighter's health
        System.out.printf("%s health: %d/%d \n%s\n",
                target.getName(),
                target.healthComponent.getHealth(), target.healthComponent.getMaxHealth(),
                getHealthVisual(target));
        System.out.println();

        // if there is a death, display it
        if (target.healthComponent.getHealth() == 0) {
            System.out.printf("%s died.\n", target.getName());
        } else if (attacker.healthComponent.getHealth() == 0) {
            System.out.printf("%s died.\n", attacker.getName());
        }
        System.out.println();

        // ask for confirmation to continue
        System.out.print("Hit <enter> to continue: ");
        input.nextLine();

        // send a message that the turn has ended
        fighter.send(new Message(Messages.END_TURN));

        // reset all variables
        attacker = null;
        attack = null;
        target = null;
        deltaAttackerHealth = null;
        deltaTargetHealth = null;

    }

    // good function yeah yeah cool man
    private @NotNull String getHealthVisual(Fighter fighter) {
        // change these
        int healthBarLength = 20;
        String healthCharacter = "#";
        String missingHealthCharacter = "-";
        // don't change these
        int currentHealth = fighter.healthComponent.getHealth();
        int maxHealth = fighter.healthComponent.getMaxHealth();
        int healthLength = (int)(healthBarLength * ((double)currentHealth/(double)maxHealth));
        // should look something like this: "[################----]"
        String healthVisual = "[";
        healthVisual += new String(new char[healthLength]).replace("\0", healthCharacter);
        healthVisual += new String(new char[healthBarLength - healthLength]).replace("\0", missingHealthCharacter);
        healthVisual += "]";
        return healthVisual;
    }

}
