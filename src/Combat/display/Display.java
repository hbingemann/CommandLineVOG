package Combat.display;

import Combat.Attack;
import Combat.Battle;
import Combat.Component;
import Combat.Fighter;

import java.util.Scanner;

public class Display extends Component {

    protected static final Scanner input = new Scanner(System.in);  // probably change this, fix through messaging maybe?

    /*
     keep track of all aspects to display
     Things to Display:
        - health
        - available attacks
        - Fighter turns:
            -- conditions
            -- damage dealt
            -- self inflicted damage
            -- miss / no miss
            -- result after dealing damage
     */
    protected Integer deltaOpponentHealth, deltaAttackerHealth;
    protected Fighter attacker, opponent;
    protected Attack attack;

    // these might be able to be replaced by the messaging system
    public void display() {}

    public void setAttack(Attack attack) { this.attack = attack; }

    public void setOpponent(Fighter opponent) {
        this.opponent = opponent;
    }

    public void setAttacker(Fighter attacker) {
        this.attacker = attacker;
    }

    public void setDeltaOpponentHealth(int deltaOpponentHealth) {
        this.deltaOpponentHealth = deltaOpponentHealth;
    }

    public void setDeltaAttackerHealth(int deltaAttackerHealth) {
        this.deltaAttackerHealth = deltaAttackerHealth;
    }
}
