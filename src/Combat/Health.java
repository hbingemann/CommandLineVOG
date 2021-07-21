package Combat;

public class Health {

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

    public void heal(int healthIncrease) {
        health += healthIncrease;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    // getters and setters
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() { return maxHealth; }

}
