package Combat.health;

import Combat.Attack;
import Combat.Component;
import Combat.Message;

public class Health extends Component {

    private int health;
    private final int maxHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void changeHealth(int amount) {
        health += amount;
        health = Math.max(0, Math.min(maxHealth, health)); // clamp it between the range of 0 and maxHealth
    }

    // getters
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

    @Override
    public void receive(Message message) {
        switch (message.getMessage()) {
            case DELTA_ATTACKER_HEALTH -> changeHealth(message.getIntData());
        }
    }

}
