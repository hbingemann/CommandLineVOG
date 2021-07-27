package Combat.display;

import Combat.Attack;
import Combat.Component;
import Combat.Fighter;
import Combat.Message;

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
    protected Integer deltaTargetHealth, deltaAttackerHealth;
    protected Fighter attacker, target;
    protected Attack attack;

    public void handle(Fighter fighter) {}

    // for setting vars
    @Override
    public void receive(Message message) {
        switch (message.getMessage()) {
            case ATTACKER -> attacker = message.getFighterData();
            case ATTACK -> attack = message.getAttackData();
            case TARGET -> target = message.getFighterData();
            case DELTA_ATTACKER_HEALTH -> deltaAttackerHealth = message.getIntData();
            case DELTA_TARGET_HEALTH -> deltaTargetHealth = message.getIntData();
        }
    }
}
