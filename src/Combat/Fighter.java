package Combat;

import Combat.battle.Battle;
import Combat.display.Display;
import Combat.distributor.DamageDistributor;
import Combat.health.Health;
import Combat.input.Input;

import java.util.ArrayList;

public class Fighter extends ComponentContainer {

    private final String name; // the fighter's name
    public final Health healthComponent; // represents fighter health
    private final ArrayList<Attack> attacks = new ArrayList<>(); // all the attacks available to this fighter
    private final Battle battleComponent; // the battle the fighter is in
    private final Input inputComponent; // for getting input
    private final Display displayComponent;
    //private ArrayList<StatusEffect> currentConditions; // store all active special conditions on fighter

    public Fighter(Input inputComponent, Display displayComponent, Health healthComponent, Battle battleComponent, String[] attackIds, String name) {
        // init variables
        this.healthComponent = healthComponent;
        this.battleComponent = battleComponent;
        this.inputComponent = inputComponent;
        this.displayComponent = displayComponent;
        this.name = name;

        // add all attack objects to attack list
        for (String attackId: attackIds) {
            attacks.add(new Attack(attackId));
        }

        // add the components for messaging
        this.components.add(inputComponent);
        this.components.add(healthComponent);
        this.components.add(displayComponent);
        this.components.add(battleComponent);
        this.components.add(new DamageDistributor());  // hard-coded, might change

        // register this fighter in the battle
        battleComponent.registerFighter(this);
    }

    // for taking turn
    public void takeTurn() {
        // first get input
        inputComponent.handle(this);

        // end it by displaying
        displayComponent.handle(this);
    }

    // getters
    public String getName() {
        return this.name;
    }
    public ArrayList<Attack> getAttacks() { return attacks; }
    public ArrayList<Fighter> getOpponents() { return battleComponent.getOpponents(this); }
}

