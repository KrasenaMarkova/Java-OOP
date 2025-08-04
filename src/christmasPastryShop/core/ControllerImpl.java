package christmasPastryShop.core;

import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.*;

import static christmasPastryShop.common.ExceptionMessages.BOOTH_EXIST;
import static christmasPastryShop.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private final DelicacyRepository<Delicacy> delicacyRepository;
    private final CocktailRepository<Cocktail> cocktailRepository;
    private final BoothRepository<Booth> boothRepository;
    private double totalStoreIncome;

    public ControllerImpl() {
        this.delicacyRepository = new DelicacyRepositoryImpl();
        this.cocktailRepository = new CocktailRepositoryImpl();
        this.boothRepository = new BoothRepositoryImpl();
        this.totalStoreIncome = 0;
    }

    @Override
    public String addDelicacy(String type, String name, double price) {
       Delicacy delicacy;

       switch (type) {
           case "Gingerbread":
               delicacy = new Gingerbread(name, price);
               break;
           case "Stolen":
               delicacy = new Stolen(name, price);
               break;
           default:
               throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
       }
        delicacyRepository.add(delicacy);

        return String.format(DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail;

        switch (type) {
            case "Hibernation":
                cocktail = new Hibernation(name, size, brand);
                break;
            case "MulledWine":
                cocktail = new MulledWine(name, size, brand);
                break;
            default:
                throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        cocktailRepository.add(cocktail);

        return String.format(COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth;

        switch (type) {
            case "OpenBooth":
                booth = new OpenBooth(boothNumber, capacity);
            break;
            case "PrivateBooth":
                booth = new PrivateBooth(boothNumber, capacity);
                break;
            default:
                throw new IllegalArgumentException(String.format(BOOTH_EXIST, boothNumber));
        }
        boothRepository.add(booth);

        return String.format(BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        for (Booth booth : boothRepository.getAll()) {
            //Finds a booth that is not reserved
            // and its capacity is enough for the number of people provided
            if (booth.getCapacity() >= numberOfPeople && !booth.isReserved()) {
                booth.reserve(numberOfPeople);
                return String.format(BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        //Finds the booth with the same booth number
        Booth booth = boothRepository.getByNumber(boothNumber);
        //Gets the bill for that booth
        double bill = booth.getBill();
        //clears the bill
        booth.clear();
        //adds the sum to the total store income.
        totalStoreIncome += bill;

        return String.format(BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {

        return String.format(TOTAL_INCOME, totalStoreIncome);
    }
}
