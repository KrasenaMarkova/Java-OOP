package handball.core;


import handball.entities.equipment.ElbowPad;
import handball.entities.equipment.Equipment;
import handball.entities.equipment.Kneepad;
import handball.entities.gameplay.Gameplay;
import handball.entities.gameplay.Indoor;
import handball.entities.gameplay.Outdoor;
import handball.entities.team.Bulgaria;
import handball.entities.team.Germany;
import handball.entities.team.Team;
import handball.repositories.EquipmentRepository;

import java.util.ArrayList;
import java.util.Collection;

import static handball.common.ConstantMessages.*;
import static handball.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private EquipmentRepository equipmentRepository;
    private Collection<Gameplay> gameplays;

    public ControllerImpl() {
        this.equipmentRepository = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        Gameplay gameplay;

        switch (gameplayType) {
            case "Outdoor":
                gameplay = new Outdoor(gameplayName);
                break;
            case "Indoor":
                gameplay = new Indoor(gameplayName);
                break;
            default:
                throw new NullPointerException(INVALID_GAMEPLAY_TYPE);
        }

        this.gameplays.add(gameplay);

        return String.format(SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        Equipment equipment1;

        switch (equipmentType) {
            case "Kneepad":
                equipment1 = new Kneepad();
                break;
            case "ElbowPad":
                equipment1 = new ElbowPad();
                break;
            default:
                throw new IllegalArgumentException(INVALID_EQUIPMENT_TYPE);
        }

        this.equipmentRepository.add(equipment1);

        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_TYPE);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Equipment currentType = this.equipmentRepository.findByType(equipmentType);

        if (currentType == null) {
            throw new IllegalArgumentException(String.format(NO_EQUIPMENT_FOUND, equipmentType));
        }

        this.equipmentRepository.remove(currentType);
        addGameplay(gameplayName, equipmentType);

        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Team team;

        switch (teamType) {
            case "Bulgaria":
                team = new Bulgaria(teamName,country, advantage);
                break;
            case "Germany":
                team = new Germany(teamName, country, advantage);
                break;
            default:
                throw new IllegalArgumentException(INVALID_TEAM_TYPE);
        }

        Gameplay gameplay = getGameByName(gameplayName);

        boolean isTrue = gameplay.getClass().getSimpleName().equals("Outdoor") && teamType.equals("Bulgaria")
                || gameplay.getClass().getSimpleName().equals("Indoor") && teamType.equals("Germany");

        if (!isTrue) {
            return GAMEPLAY_NOT_SUITABLE;
        }
       getGameByName(gameplayName).addTeam(team);

        return String.format(SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
    }

    @Override
    public String playInGameplay(String gameplayName) {
        Gameplay gameplay = getGameByName(gameplayName);
        gameplay.teamsInGameplay();

        return String.format(TEAMS_PLAYED, gameplay.getTeam().size());
    }

    @Override
    public String percentAdvantage(String gameplayName) {

        return String.format(ADVANTAGE_GAMEPLAY, gameplayName,
                getGameByName(gameplayName).getTeam().stream().mapToInt(Team::getAdvantage).sum());
        //return String.format(ADVANTAGE_GAMEPLAY, gameplayName,
        //                this.gameplays.get(gameplayName).getTeam().stream().mapToInt(Team::getAdvantage).sum());
    }

  /*  public String getStatistics() {
        StringBuilder output = new StringBuilder();
        this.gameplays.values().forEach(output::append);
        return output.toString().trim();
    }*/
    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        gameplays.stream().iterator().hasNext();

       /* for (Gameplay gameplay : gameplays) {
            sb.append(String.format(gameplay.getName() + " " + findByType(gameplay.getName())));
            sb.append(System.lineSeparator());
            if (gameplays.isEmpty()) {
                sb.append(String.format("Team: none"));
            } else {
                String nameGamePlay = String.valueOf(getGameByName(gameplay.getName()));
                sb.append(nameGamePlay);
            }
            sb.append(String.format("Equipment: " + equipment + ", Protection: " + gameplay.allProtection()));
            getGameByName(gameplay.getName());
        }*/

        return sb.toString().trim();
    }

    public Gameplay findByType(String type) {
        return this.gameplays.stream().filter(e -> e.getClass().getSimpleName().equals(type))
                .findAny()
                .orElse(null);
    }
    public Gameplay getGameByName(String gameName) {
        return gameplays.stream()
                .filter(gameplay -> gameplay.getName().equals(gameName))
                .findFirst()
                .orElse(null);
    }
}
