package furnitureFactory.repositories;

import furnitureFactory.entities.workshops.Workshop;

import java.util.ArrayList;
import java.util.Collection;

import static furnitureFactory.common.ExceptionMessages.WORKSHOP_EXISTS_IN_REPOSITORY;
import static furnitureFactory.common.ExceptionMessages.WORKSHOP_WOOD_QUANTITY_BELOW_OR_EQUAL_ZERO;

public class WorkshopRepositoryImpl implements WorkshopRepository{
    private Collection<Workshop> workshops;

    public WorkshopRepositoryImpl() {
        this.workshops = new ArrayList<>();
    }

    @Override
    public void add(Workshop workshop) {
        //If the wood quantity of the workshop is below or equal to 0, throw an IllegalArgumentException with the message:
        //•	"Can not build workshop with zero or less wood quantity."
        if (workshop == null) {
            throw new IllegalArgumentException(WORKSHOP_WOOD_QUANTITY_BELOW_OR_EQUAL_ZERO);
        }
//If you are trying to add a Workshop of a type that already exists in the repository throw an IllegalArgumentException with the message:
//•	"Workshop of this type already exists in the repository."
        if (workshops.contains(workshop)) {
            throw new IllegalArgumentException(WORKSHOP_EXISTS_IN_REPOSITORY);
        }

        workshops.add(workshop);
    }

    @Override
    public boolean remove(Workshop workshop) {
        return workshops.remove(workshop);
    }

    @Override
    public Workshop findByType(String type) {
        //•	Returns the first Workshop of the given type, if there is one. Otherwise, returns null.
        //Hint: You can use - getClass().getSimpleName() method to determine the wood/workshop.
        return workshops.stream()
                .filter(workshop -> workshop.equals(type))
                .findFirst()
                .orElse(null);
    }
}
