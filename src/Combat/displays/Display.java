package Combat.displays;

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
    protected int deltaOpponentHealth, deltaSelfHealth;
    protected Fighter attacker, attacked;
    protected Attack attack;

    public void display() {}

    public void setAttack(Attack attack) { this.attack = attack; }

    public void setAttacked(Fighter attacked) {
        this.attacked = attacked;
    }

    public void setAttacker(Fighter attacker) {
        this.attacker = attacker;
    }

    public void setDeltaOpponentHealth(int deltaOpponentHealth) {
        this.deltaOpponentHealth = deltaOpponentHealth;
    }

    public void setDeltaSelfHealth(int deltaSelfHealth) {
        this.deltaSelfHealth = deltaSelfHealth;
    }
}
