package Combat;

public class Health {

    private int health;
    private final int maxHealth;
    private boolean dead;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.dead = false;
        this.health = maxHealth;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            dead = true;
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
    public boolean isDead() {
        return dead;
    }

    public String getHealthVisual() {
        // change these
        int healthBarLength = 20;
        String healthCharacter = "#";
        String missingHealthCharacter = "-";
        // don't change these
        int healthLength = (int)(healthBarLength * ((double)health/(double)maxHealth)); // how many healthBarCharacters, rest is missing health
        String healthVisual = "[";
        // should look something like this: "[################----]
        healthVisual += new String(new char[healthLength]).replace("\0", healthCharacter);
        healthVisual += new String(new char[healthBarLength - healthLength]).replace("\0", missingHealthCharacter);
        healthVisual += "]";
        return healthVisual;
    }

}
