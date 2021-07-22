package Combat;

import Combat.displays.Display;
import Combat.inputs.Input;

import java.util.ArrayList;

public class Fighter extends Subject {

    private final String name;
    public Health health; // represents fighter health
    private final ArrayList<Attack> attacks; // all the attacks available to this fighter
    private final Battle battle;
    private final Display displayComponent;
    private final Input inputComponent;
    //private ArrayList<StatusEffect> currentConditions; // store all active special conditions on fighter

    public Fighter(Display displayComponent, Input inputComponent, Battle battle, int maxHealth, String[] attackIds, String name) {
        // init variables
        this.health = new Health(maxHealth);
        this.name = name;
        this.battle = battle;
        this.displayComponent = displayComponent;
        this.inputComponent = inputComponent;
        //currentConditions = new ArrayList<StatusEffect>();

        // add all attack objects to attack list
        attacks = new ArrayList<>();
        for (String attackId: attackIds) {
            attacks.add(new Attack(attackId));
        }
    }

    public String getName() {
        return this.name;
    }

    /* temp deleted
    public void addCondition(String name) {
        currentConditions.add(new StatusEffect(name, this));
    }
    */


    /*
    ########################################

                TURN TAKING

    ########################################
     */


    /* Current turn flow:
        - choose attack
        - use it on opponent
     */

    public void takeTurn() {
        // IGNORE: loop through current special conditions and tell them to do their stuff (miss turn, take damage, )

        // SECTION   ->    choosing attack
        Attack attackChosen = inputComponent.getAttackChoice(this);

        // SECTION  ->  use attack on opponent
        int damage = attackChosen.getDamage();

        // this code really sucks
        battle.damageOpponent(damage, this);

        // Give the display the result
        displayComponent.setAttacker(this);
        displayComponent.setAttack(attackChosen);
        displayComponent.setDeltaAttackerHealth(0); // this will likely change later when healing is introduced


        // IGNORE: if attack wants to add special condition(s) to opponent, call function in battle to do so

        sendNotification(Notifications.FIGHTER_END_TURN);
    }

    public ArrayList<Attack> getAttacks() { return attacks; }
}
