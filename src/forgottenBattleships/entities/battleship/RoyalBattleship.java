package forgottenBattleships.entities.battleship;

public class RoyalBattleship extends BaseBattleship{
    public RoyalBattleship(String name, int health) {
        super(name, health, 100, 25);
    }

    @Override
    public void attack(Battleship battleship) {
        setAmmunition((getAmmunition() - 25));
    }

    @Override
    public void takeDamage(Battleship battleship) {
        super.takeDamage(battleship);
    }
}
