package exampreparation.christmasPastryShop.entities.booths.interfaces;

import exampreparation.christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import exampreparation.christmasPastryShop.entities.drinks.interfaces.Cocktail;

public interface Booth {
    int getBoothNumber();

    int getCapacity();

    boolean isReserved();

    double getPrice();

    void reserve(int numberOfPeople);

    void orderDelicacy(Delicacy food);

    void orderCocktail(Cocktail cocktail);

    double getBill();

    void clear();
}
