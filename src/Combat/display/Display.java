package Combat.display;

import Combat.Attack;
import Combat.Fighter;

public class Display {

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
