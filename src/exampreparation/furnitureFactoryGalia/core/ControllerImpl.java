package exampreparation.furnitureFactoryGalia.core;

import furnitureFactory.core.Controller;
import furnitureFactory.entities.factories.AdvancedFactory;
import furnitureFactory.entities.factories.Factory;
import furnitureFactory.entities.factories.OrdinaryFactory;
import furnitureFactory.entities.wood.OakWood;
import furnitureFactory.entities.wood.Wood;
import furnitureFactory.entities.workshops.DeckingWorkshop;
import furnitureFactory.entities.workshops.TableWorkshop;
import furnitureFactory.entities.workshops.Workshop;
import furnitureFactory.repositories.WoodRepository;
import furnitureFactory.repositories.WoodRepositoryImpl;
import furnitureFactory.repositories.WorkshopRepository;
import furnitureFactory.repositories.WorkshopRepositoryImpl;

import java.util.*;

import static furnitureFactory.common.ConstantMessages.*;
import static furnitureFactory.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private WoodRepository woodRepository;
    private WorkshopRepository workshopRepository;
    private HashMap<String, Factory> factories;
    private int total;
    private int producedItems;

    public ControllerImpl() {
        this.woodRepository = new WoodRepositoryImpl();
        this.workshopRepository = new WorkshopRepositoryImpl();
        this.factories = new HashMap<>();

    }

    @Override
    public String buildFactory(String factoryType, String factoryName) {
        Factory factory = switch (factoryType) {
            case "OrdinaryFactory" -> new OrdinaryFactory(factoryName);
            case "AdvancedFactory" -> new AdvancedFactory(factoryName);
            default -> throw new IllegalArgumentException(INVALID_FACTORY_TYPE);
        };
        if (factories.containsKey(factoryName)) {
            throw new NullPointerException(FACTORY_EXISTS);
        }
        factories.put(factoryName, factory);
        return String.format(SUCCESSFULLY_BUILD_FACTORY_TYPE, factoryType, factoryName);
    }

    @Override
    public Factory getFactoryByName(String factoryName) {
        return factories.get(factoryName);
    }

    @Override
    public String buildWorkshop(String workshopType, int woodCapacity) {
        Workshop workshop = switch (workshopType) {
            case "TableWorkshop" -> new TableWorkshop(woodCapacity);
            case "DeckingWorkshop" -> new DeckingWorkshop(woodCapacity);
            default -> throw new IllegalArgumentException(INVALID_WORKSHOP_TYPE);
        };
//        if (workshopRepository.) {
//            throw new NullPointerException(WORKSHOP_EXISTS_IN_REPOSITORY);
//        }
        workshopRepository.add(workshop);
        return String.format(SUCCESSFULLY_BUILD_WORKSHOP_TYPE, workshopType);
    }

    @Override
    public String addWorkshopToFactory(String factoryName, String workshopType) {
        Workshop workshop = workshopRepository.findByType(workshopType);
        if (workshop == null) {
            throw new NullPointerException(NO_WORKSHOP_FOUND.formatted(workshopType));
        }
        Factory factory = getFactoryByName(factoryName);
        if (factory.getWorkshops().stream().anyMatch(workshop1 -> workshop1.getClass().getSimpleName().equals(workshopType))) {
            throw new IllegalArgumentException(WORKSHOP_EXISTS);
        }
        if (factory.getClass().getSimpleName().equals("AdvancedFactory") && workshopType.equals("TableWorkshop") ||
                factory.getClass().getSimpleName().equals("OrdinaryFactory") && workshopType.equals("DeckingWorkshop")) {
            throw new IllegalArgumentException(NON_SUPPORTED_WORKSHOP);
        }
        factory.getWorkshops().add(workshop);
        return String.format(SUCCESSFULLY_ADDED_WORKSHOP_IN_FACTORY, workshopType, factoryName);
    }

    @Override
    public String buyWoodForFactory(String woodType) {
        Wood wood = switch (woodType) {
            case "OakWood" -> new OakWood();
            default -> throw new IllegalArgumentException(INVALID_WOOD_TYPE);
        };
        woodRepository.add(wood);
        return String.format(SUCCESSFULLY_BOUGHT_WOOD_FOR_FACTORY, woodType);
    }

    @Override
    public String addWoodToWorkshop(String factoryName, String workshopType, String woodType) {
        Factory factory = getFactoryByName(factoryName);
        assert factory != null;
        if (factory.getWorkshops().isEmpty()) {
            throw new NullPointerException(NO_WORKSHOP_ADDED);
        }
        Wood wood = woodRepository.findByType(woodType);
        if (wood == null) {
            throw new NullPointerException(NO_WOOD_FOUND.formatted(woodType));
        }
        Workshop workshop = null;
        for (Workshop ws : factory.getWorkshops()) {
            if (ws.getClass().getSimpleName().equals(workshopType)) {
                workshop = ws;
            }
        }
        assert workshop != null;
        workshop.addWood(wood);
        woodRepository.remove(wood);
        return String.format(SUCCESSFULLY_ADDED_WOOD_IN_WORKSHOP, woodType, workshopType);
    }

    @Override
    public String produceFurniture(String factoryName) {
        Factory factory = getFactoryByName(factoryName);


        assert factory != null;
        if (factory.getWorkshops().isEmpty()) {
            throw new NullPointerException(THERE_ARE_NO_WORKSHOPS.formatted(factoryName));
        }


        Iterator<Workshop> iterator = factory.getWorkshops().iterator();

        while (iterator.hasNext()) {
            Workshop workshop = iterator.next();

            if (workshop.getWoodQuantity() < workshop.getWoodQuantityReduceFactor() &&
                    workshop.getWoodQuantity() > 0) {
                System.out.println("Insufficient wood quantity. This workshop cannot start production.");
                continue;
            }
            if (workshop.getWoodQuantity() <= 0) {
                iterator.remove();
                workshopRepository.remove(workshop);
                factory.getRemovedWorkshops().add(workshop);
                System.out.println(workshop.getClass().getSimpleName() + " stopped working due to running out of wood.");
                continue;
            }
            workshop.produce();
            producedItems = workshop.getProducedFurnitureCount();
            total += producedItems;
        }
        if (producedItems <= 0) {
            return String.format("This time %s factory did not produce anything.", factoryName);
        } else {
            return String.format(SUCCESSFUL_PRODUCTION, producedItems, factoryName);
        }
    }

    @Override
    public String getReport() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Factory> entry : factories.entrySet()) {

            sb.append(String.format("Production by %s factory:", entry.getKey()))
                    .append(System.lineSeparator());
            String data = "";
            if (entry.getValue().getWorkshops().isEmpty()) {
                data = "No workshops were added to produce furniture.";
            } else {
                for (Workshop workshop : entry.getValue().getWorkshops()) {
                    data += String.format("%s: %d furniture produced%n",
                            workshop.getClass().getSimpleName(), total);
                }
            }
            sb.append(data).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
