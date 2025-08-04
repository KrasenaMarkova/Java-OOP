package exampreparation.furnitureFactoryNewMine.core;

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

import java.util.ArrayList;
import java.util.Collection;

import static furnitureFactory.common.ConstantMessages.*;
import static furnitureFactory.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
  private WoodRepository woodRepository;
  private WorkshopRepository workshopRepository;

  private Collection<Factory> factories;

    public ControllerImpl() {
        this.woodRepository = new WoodRepositoryImpl();
        this.workshopRepository = new WorkshopRepositoryImpl();
        this.factories = new ArrayList<>();
    }

    @Override
    public String buildFactory(String factoryType, String factoryName) {
        Factory factory;

        switch (factoryType) {
            case "OrdinaryFactory":
                factory = new OrdinaryFactory(factoryName);
                break;
            case "AdvancedFactory":
                factory = new AdvancedFactory(factoryName);
                break;
            default:
                throw new IllegalArgumentException(INVALID_FACTORY_TYPE);
        }
        factories.add(factory);

        if (factories.getClass().getSimpleName().equals(factoryName)) {
            throw new NullPointerException(FACTORY_EXISTS);
        } else {
            factories.add(factory);
            return String.format(SUCCESSFULLY_BUILD_FACTORY_TYPE, factoryType, factoryName);
        }
    }

    @Override
    public Factory getFactoryByName(String factoryName) {
        return factories.stream()
                .filter(factory -> factory.getName().equals(factoryName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String buildWorkshop(String workshopType, int woodCapacity) {
        Workshop workshop;

        switch (workshopType) {
            case "TableWorkshop":
                workshop = new TableWorkshop(woodCapacity);
                break;
            case "DeckingWorkshop":
                workshop = new DeckingWorkshop(woodCapacity);
                break;
            default:
                throw new IllegalArgumentException(INVALID_WORKSHOP_TYPE);
        }
        workshopRepository.add(workshop);

        return String.format(SUCCESSFULLY_BUILD_WORKSHOP_TYPE, workshopType);
    }

    @Override
    public String addWorkshopToFactory(String factoryName, String workshopType) {
        Factory factory = factories.stream()
                .filter(factory1 -> factory1.getName().equals(factoryName))
                .findFirst()
                .orElse(null);
        Workshop workshop = workshopRepository.findByType(workshopType);

        //If there is no such workshop, in the WorkshopRepository you have to throw an NullPointerException with the following message:
        if (factory == null) {
            throw new NullPointerException(String.format(NO_WORKSHOP_FOUND, workshopType));
        }

        //If there is a Workshop with the same type in the Factory throw an IllegalArgumentException with the following message:
        boolean sameTypeWorkshopInFactory  = factory.getWorkshops().equals(workshopType);
        if (sameTypeWorkshopInFactory) {
            throw new IllegalArgumentException(WORKSHOP_EXISTS_IN_REPOSITORY);
        }

        if ((workshop.equals("TableWorkshop ") && factory.equals("AdvancedFactory"))
        || (workshop.equals("DeckingWorkshop ") && factory.equals("OrdinaryFactory"))) {
            throw new IllegalArgumentException(NON_SUPPORTED_WORKSHOP);
        }
        workshopRepository.add(workshop);

        return String.format(SUCCESSFULLY_ADDED_WORKSHOP_IN_FACTORY, workshopType, factoryName);
    }


    @Override
    public String buyWoodForFactory(String woodType) {
        Wood wood;

        switch (woodType) {
            case "OakWood":
                wood = new OakWood();
                break;
            default:
                throw new IllegalArgumentException(INVALID_WOOD_TYPE);
        }

        woodRepository.add(wood);

        return String.format(SUCCESSFULLY_BOUGHT_WOOD_FOR_FACTORY, woodType);
    }

    @Override
    public String addWoodToWorkshop(String factoryName, String workshopType, String woodType) {
        Factory currentFactory = factories.stream()
                .filter(factory -> factory.getName().equals(factoryName))
                .findFirst()
                .orElse(null);

        Workshop currentWorkshop = workshopRepository.findByType(workshopType);

        //If there is no workshop in the given factory to add wood to, throw an NullPointerException with the message:
        //•	"There are no added workshops to add wood to."
        if (currentFactory.getWorkshops().isEmpty()) {
            throw new NullPointerException(NO_WORKSHOP_ADDED);
        }

        //If there is no wood of this type in WoodRepository. throw an NullPointerException with the message:
        //•	"There is no {woodType} in wood repository."
        Wood currentWood = woodRepository.findByType(woodType);
        if (currentWood == null) {
            throw new NullPointerException(String.format(NO_WOOD_FOUND, woodType));
        }

        currentWorkshop.addWood(currentWood);
        woodRepository.remove(currentWood);

        return String.format(SUCCESSFULLY_ADDED_WOOD_IN_WORKSHOP, woodType, workshopType);
    }

    @Override
    public String produceFurniture(String factoryName) {
        Factory currentFactory = factories.stream()
                .filter(factory -> factory.getName().equals(factoryName))
                .findFirst()
                .orElse(null);

//If there are no added workshops in the given factory throw an NullPointerException with the message:
//•	"There are no added workshops in {factory name} factory to produce furniture."
        if (currentFactory.getWorkshops().isEmpty()) {
            throw new NullPointerException(String.format(THERE_ARE_NO_WORKSHOPS, factoryName));
        }

        return null;
    }

    @Override
    public String getReport() {
        return null;
    }
}
