package Combat.health;

import Combat.Component;

public class Health extends Component {

    private int health;
    private final int maxHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    public void heal(int healValue) {
        health += healValue;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    // getters
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() { return maxHealth; }

}
