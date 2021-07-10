package Combat;

public class Health {

    private int health;
    private final int maxHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    public void changeHealth(int amount) {
        health += amount;
    }

    public int getHealth() {
        return health;
    }

    public void heal(int healthIncrease) {
        health += healthIncrease;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

}
