package Combat;

import org.json.JSONObject;

import java.util.ArrayList;

public class Fighter extends Subject {

    private final String name;
    public Health health; // represents fighter health
    private final ArrayList<Attack> attacks; // all the attacks available to this fighter
    private final boolean isPlayer; // if it's a player ask for input
    private final Battle battle;
    //private ArrayList<StatusEffect> currentConditions; // store all active special conditions on fighter

    public Fighter(Battle battle, int maxHealth, String[] attackIds, String name, boolean isPlayer) {
        // init variables
        this.health = new Health(maxHealth);
        this.name = name;
        this.isPlayer = isPlayer;
        this.battle = battle;
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
        Attack attackChosen;
        if (isPlayer) {
            // get input
            displayAttacks();
            System.out.print("Enter attack number: ");
            attackChosen = attacks.get(Integer.parseInt(Battle.input.nextLine()) - 1);
            System.out.println();
        } else {
            // computer so pick random attack (maybe AI later?)
            attackChosen = attacks.get((int) (Math.random() * attacks.size()));
        }

        // SECTION  ->  use attack on opponent
        int damage = attackChosen.getDamage();

        // Display the attack result
        System.out.printf("%s used %s ", name, attackChosen.getName());
        if (damage == 0) {
            System.out.print("but it missed! \n\n");
        } else {
            System.out.printf("and it did %d damage! \n\n", damage);
        }

        // this code really sucks, I'll need to use a display class to make it better
        battle.damageOpponent(damage, this);

        // IGNORE: if attack wants to add special condition(s) to opponent, call function in battle to do so

        sendNotification(Notifications.FIGHTER_END_TURN);
    }

    private void displayAttacks() {
        System.out.println("Your Attacks: \n");
        for (int i = 0; i < attacks.size(); i++) {
            Attack attack = attacks.get(i);
            System.out.printf("%d. %s \n", i + 1, attack.getName());
            System.out.println("\t" + attack.getDescription());
            System.out.println();
        }
        sendNotification(Notifications.ATTACKS_DISPLAYED);
    }

}
