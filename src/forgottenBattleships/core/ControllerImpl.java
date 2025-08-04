package forgottenBattleships.core;

import forgottenBattleships.entities.battleship.BaseBattleship;
import forgottenBattleships.entities.battleship.Battleship;
import forgottenBattleships.entities.battleship.PirateBattleship;
import forgottenBattleships.entities.battleship.RoyalBattleship;
import forgottenBattleships.entities.battlezone.BattleZone;
import forgottenBattleships.entities.battlezone.BattleZoneImpl;

import java.util.ArrayList;
import java.util.Collection;

import static forgottenBattleships.common.ConstantMessages.*;
import static forgottenBattleships.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private Collection<BattleZone> battleZones;
    private BattleZone battleZone;

    public ControllerImpl() {
        this.battleZones = new ArrayList<>();
        this.battleZone = battleZone;
    }

    @Override
    public String addBattleZone(String battleZoneName, int capacity) {
        BattleZone battleZone = new BattleZoneImpl(battleZoneName, capacity);

        if (battleZones.getClass().getSimpleName().equals(battleZoneName)) {
            throw new IllegalArgumentException(BATTLE_ZONE_EXISTS);
        }

        battleZones.add(battleZone);

        return String.format(SUCCESSFULLY_ADDED_BATTLE_ZONE, battleZoneName);
    }

    @Override
    public BattleZone getBattleZoneByName(String battleZoneName) {

        return battleZones.stream()
                .filter(battleZone -> battleZone.equals(battleZoneName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String addBattleshipToBattleZone(String battleZoneName, String shipType, String shipName, int health) {
        Battleship battleship;

        switch (shipType) {
            case "RoyalBattleship":
                battleship = new RoyalBattleship(shipName, health);
                break;
            case "PirateBattleship":
                battleship = new PirateBattleship(shipName, health);
                break;
            default:
                throw new IllegalArgumentException (INVALID_SHIP_TYPE);
        }

        BattleZone battleZone1 = battleZones.stream()
                .filter(battleZone -> battleZone.getName().equals(battleZoneName))
                .findFirst()
                .orElse(null);
        if (battleZone1 == null) {
            throw new NullPointerException(BATTLE_ZONE_DOES_NOT_EXISTS);
        }

        boolean containsShipName = battleZone.getShips().contains(shipName);
        if (containsShipName) {
            throw new IllegalArgumentException(SHIP_EXISTS);
        }

        battleZone.addBattleship(battleship);
        battleZones.add(battleZone);

        return String.format(SUCCESSFULLY_ADDED_SHIP, shipType, shipName, battleZoneName);
    }

    @Override
    public String startBattle(String battleZoneName, String attackingShip, String shipUnderAttack) {
        BattleZone currentBattleZone = battleZones.stream()
                .filter(battleZone1 -> battleZone1.getName().equals(battleZoneName))
                .findFirst()
                .orElse(null);


        return String.format(BATTLE_CONTINUES, battleZoneName, attackingShip);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        for (BattleZone zone : battleZones) {
            sb.append(String.format(SHIPS_IN_BATTLE_ZONE, zone.getName())).append(System.lineSeparator());

            for (Battleship ship : battleZone.getShips()) {
                if (battleZone.getShips().size() == 1) {
                    sb.append(String.format(SHIP_WINS, ship.getName())).append(System.lineSeparator());
                } else {
                    sb.append(String.format(SHIP_INFO, ship.getName(), ship.getHealth(), ship.getAmmunition())).append(System.lineSeparator());
                }

            }
        }

        return sb.toString().trim();
    }
}
