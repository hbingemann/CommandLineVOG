package Combat;

import Combat.display.Display;
import Combat.health.Health;
import Combat.input.Input;

import java.util.ArrayList;

public class Fighter extends ComponentContainer {

    private final String name; // the fighter's name
    public final Health healthComponent; // represents fighter health
    private final ArrayList<Attack> attacks = new ArrayList<>(); // all the attacks available to this fighter
    private final Battle battle; // the battle the fighter is in
    private final Input inputComponent; // for getting input
    private final Display displayComponent;
    //private ArrayList<StatusEffect> currentConditions; // store all active special conditions on fighter

    public Fighter(Input inputComponent, Display displayComponent, Health healthComponent, String[] attackIds, String name, Battle battle) {
        // init variables
        this.healthComponent = healthComponent;
        this.name = name;
        this.battle = battle;
        this.inputComponent = inputComponent;
        this.displayComponent = displayComponent;

        // add all attack objects to attack list
        for (String attackId: attackIds) {
            attacks.add(new Attack(attackId));
        }

        // add the components
        this.components.add(inputComponent);
        this.components.add(healthComponent);
        this.components.add(displayComponent);
    }

    /*
    ########################################

                TURN TAKING

    ########################################
     */

    public void takeTurn() {

        // SECTION   ->    choosing attack and opponents
        Attack attackChosen = inputComponent.getAttackChoice(this);
        Fighter opponent = inputComponent.getOpponentChoice(this);

        // SECTION  ->  use attack on opponent
        int damage = attackChosen.getDamage();
        opponent.healthComponent.takeDamage(damage);

        // SECTION  ->  give the display the info it needs
        displayComponent.setAttacker(this);
        displayComponent.setAttack(attackChosen);
        displayComponent.setDeltaAttackerHealth(0); // this will likely change later when healing is introduced
        displayComponent.setDeltaOpponentHealth(-damage);
        displayComponent.setOpponent(opponent);
        displayComponent.display();
    }

    // getters
    public String getName() {
        return this.name;
    }
    public ArrayList<Attack> getAttacks() { return attacks; }
    public ArrayList<Fighter> getOpponents() { return battle.getOpponents(this); }
}

