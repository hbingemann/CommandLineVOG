package Combat;

import org.json.JSONObject;

import java.util.ArrayList;

public class Fighter extends Subject {

    private Health health; // represents fighter health
    private String[] attacks; // all the attacks available to this fighter
    private boolean isPlayer; // if it's a player ask for input
    private ArrayList<StatusEffect> currentConditions; // store all active special conditions on fighter

    public Fighter(int maxHealth, String[] attacks, boolean isPlayer) {
        health = new Health(maxHealth);
        this.attacks = attacks;
        this.isPlayer = isPlayer;
        currentConditions = new ArrayList<StatusEffect>();
    }

    public void addCondition(String name) {
        currentConditions.add(new StatusEffect(name, this));
    }

    /* Planned turn flow:
        - take damage
        - add effects
     */

    public String takeTurn(String opponentAttack) {

        // carry out the opponents attack using JSONObject from attack class and check for weaknesses to that type
        JSONObject attack = Attacks.getAttack(opponentAttack);

        // loop through current special conditions and tell them to do their stuff (miss turn, take damage, )

        // if player: ask for attack
        // if computer: pick random attack (maybe AI later?)

        // if attack wants to add special condition(s) to opponent, call function in battle to do so

        sendNotification(Notifications.FIGHTER_END_TURN);
        return null;
    }

}
