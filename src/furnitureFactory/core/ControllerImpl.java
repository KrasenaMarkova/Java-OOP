package furnitureFactory.core;

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
import java.util.List;

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

//If there is a Factory ,in the collection ,with the same name, you have to throw a NullPointerException with the following message:
//•	"Factory with this name already exists."
        if (factories.contains(factoryName)) {
            throw new NullPointerException(FACTORY_EXISTS);
        }

        return String.format(SUCCESSFULLY_BUILD_FACTORY_TYPE, factoryType, factoryName);
    }

    @Override
    public Factory getFactoryByName(String factoryName) {

        //Returns Factory with the given name.
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
        Workshop workshop = workshopRepository.findByType(workshopType);

        //If there is no such workshop, in the WorkshopRepository you have to throw an NullPointerException with the following message:
        //•	"There is no workshop of type {workshopType} in repository."
        if (workshop == null) {
            throw new NullPointerException(String.format(NO_WORKSHOP_FOUND, workshopType));
        }

//If there is a Workshop with the same type in the Factory throw an IllegalArgumentException with the following message:
//•	"Workshop of this type already exists in this factory."
        boolean workshopExist = factories.stream()
                .anyMatch(factory -> factory.getWorkshops().equals(workshopType));

        if (workshopExist) {
            throw new IllegalArgumentException(WORKSHOP_EXISTS);
        }

        //TableWorkshop
        //It can be added only in OrdinaryFactory!
        //DeckingWorkshop
        //It can be added only in AdvancedFactory!
        boolean factoryDoesSupport = true;

        String name = factories.getClass().getName();

        if (workshopType.equals("TableWorkshop") && !name.equals("OrdinaryFactory")) {
            factoryDoesSupport = false;
        } else if (workshopType.equals("DeckingWorkshop") && !name.equals("AdvancedFactory")) {
            factoryDoesSupport = false;
        }

        if (!factoryDoesSupport) {
            return NON_SUPPORTED_WORKSHOP;
        }

        buildFactory(workshopType, factoryName);

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
        Factory factory1 = factories.stream()
                .filter(factory -> factory.getWorkshops().equals(workshopType))
                .findFirst()
                .orElse(null);

        Factory currentFactory = factories.stream()
                .filter(factory -> factory.getName().equals(factoryName))
                .findFirst()
                .orElse(null);

        //If there is no workshop in the given factory to add wood to, throw an NullPointerException with the message:
        //•	"There are no added workshops to add wood to."
        if (factory1 == null) {
            throw new NullPointerException(NO_WORKSHOP_ADDED);
        }

        Wood byType = woodRepository.findByType(woodType);

        //If there is no wood of this type in WoodRepository. throw an NullPointerException with the message:
        //•	"There is no {woodType} in wood repository."
        if (byType == null) {
            throw new NullPointerException(NO_WOOD_FOUND);
        }

//If the operation is successful you should remove wood  from WoodRepository
        Wood wood = woodRepository.findByType(woodType);
        woodRepository.remove(wood);

        return String.format(SUCCESSFULLY_ADDED_WOOD_IN_WORKSHOP, woodType, workshopType);
    }

    @Override
    public String produceFurniture(String factoryName) {
        Factory factoryByName = getFactoryByName(factoryName);

        Collection<Workshop> workshops = factoryByName.getWorkshops();
        if (workshops.isEmpty()) {
            throw new NullPointerException(String.format(THERE_ARE_NO_WORKSHOPS, factoryName));
        }
        String result = produceFurniture(factoryName);

        if (result.isEmpty()) {
            return String.format(FACTORY_DO_NOT_PRODUCED, factoryName);
        } else {
            return String.format(String.format(SUCCESSFUL_PRODUCTION, produceFurniture(factoryName)));
        }
    }

    @Override
    public String getReport() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Production by %s factory:", factories.getClass().getSimpleName())).append(System.lineSeparator());
        for (Factory factory : factories) {
            if (factory.getWorkshops().isEmpty()) {
                sb.append(String.format("Production by %s factory:", factories.getClass().getSimpleName())).append(System.lineSeparator());
            } else {
                sb.append(factory.getWorkshops() + ": furniture produced").append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
