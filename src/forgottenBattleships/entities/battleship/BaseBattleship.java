package forgottenBattleships.entities.battleship;

import static forgottenBattleships.common.ExceptionMessages.SHIP_NAME_NULL_OR_EMPTY;

public abstract class BaseBattleship implements Battleship {
    private String name;
    private int health;
    private int ammunition;
    private int hitStrength;

    public BaseBattleship(String name, int health, int ammunition, int hitStrength) {
        setName(name);
        setHealth(health);
        setAmmunition(ammunition);
        this.hitStrength = hitStrength;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(SHIP_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health < 0) {
            setHealth(0);
        }
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
        if (ammunition < 0) {
            setAmmunition(0);
        }
    }

    public void setHitStrength(int hitStrength) {
        this.hitStrength = hitStrength;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getHitStrength() {
        return this.hitStrength;
    }

    @Override
    public int getAmmunition() {
        return this.ammunition;
    }

    @Override
    abstract public void attack(Battleship battleship);

    @Override
    public void takeDamage(Battleship battleship) {
        setHealth(this.health = this.health - hitStrength);
    }
}
