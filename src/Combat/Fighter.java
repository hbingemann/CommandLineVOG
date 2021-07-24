package Combat;

import Combat.display.Display;
import Combat.health.Health;
import Combat.input.Input;

import java.util.ArrayList;

public class Fighter extends Subject {

    private final String name;
    public final Health healthComponent; // represents fighter health
    private final ArrayList<Attack> attacks = new ArrayList<>(); // all the attacks available to this fighter
    private final Battle battle;
    private final Input inputComponent;
    //private ArrayList<StatusEffect> currentConditions; // store all active special conditions on fighter

    public Fighter(Input inputComponent, Health healthComponent, String[] attackIds, String name, Battle battle) {
        // init variables
        this.healthComponent = healthComponent;
        this.name = name;
        this.battle = battle;
        this.inputComponent = inputComponent;

        // add all attack objects to attack list
        for (String attackId: attackIds) {
            attacks.add(new Attack(attackId));
        }
    }

    /*
    ########################################

                TURN TAKING

    ########################################
        Current turn flow:
            - choose attack
            - use it on opponent
     */

    public void takeTurn() {
        // IGNORE: loop through current special conditions and tell them to do their stuff (miss turn, take damage, )

        // SECTION   ->    choosing attack and opponents
        Attack attackChosen = inputComponent.getAttackChoice(this);
        Fighter opponent = inputComponent.getOpponentChoice(this);

        // SECTION  ->  use attack on opponent
        int damage = attackChosen.getDamage();
        opponent.healthComponent.takeDamage(damage);

        // Give the display the info it needs
        Display display = battle.getDisplayComponent();
        display.setAttacker(this);
        display.setAttack(attackChosen);
        display.setDeltaAttackerHealth(0); // this will likely change later when healing is introduced
        display.setDeltaOpponentHealth(-damage);
        display.setOpponent(opponent);

        // IGNORE: if attack wants to add special condition(s) to opponent, call function in battle to do so

        sendNotification(Notifications.FIGHTER_END_TURN);
    }

    // getters
    public String getName() {
        return this.name;
    }
    public ArrayList<Attack> getAttacks() { return attacks; }
    public ArrayList<Fighter> getOpponents() { return battle.getOpponents(this); }
}

