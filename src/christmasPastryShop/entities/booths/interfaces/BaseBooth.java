package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

import static christmasPastryShop.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static christmasPastryShop.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public abstract class BaseBooth implements Booth{
    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        this.delicacyOrders = new ArrayList<>();
        this.cocktailOrders = new ArrayList<>();
        this.boothNumber = boothNumber;
        setCapacity(capacity);
        setNumberOfPeople(numberOfPeople);
        this.pricePerPerson = pricePerPerson;
        this.isReserved = false;
        this.price = 0;
    }

    public void setBoothNumber(int boothNumber) {
        this.boothNumber = boothNumber;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople < 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getBoothNumber() {
        return this.boothNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        //Reserve the booth
        isReserved = true;
        // the count of people given
        this.setNumberOfPeople(numberOfPeople);
        //calculate the price of the booth
        this.price = this.pricePerPerson * numberOfPeople;
    }

    @Override
    public double getBill() {
        double bill = 0;
        bill += cocktailOrders.stream().mapToDouble(Cocktail::getPrice).sum();
        bill += delicacyOrders.stream().mapToDouble(Delicacy::getPrice).sum();
        bill += getPrice();
        return bill;
    }
/*
    @Override
    public void orderDelicacy(Delicacy delicacy) {
        delicacyOrders.add(delicacy);
    }

    @Override
    public void orderCocktail(Cocktail cocktail) {
        this.cocktailOrders.add(cocktail);
    }*/

    @Override
    public void clear() {
        // Frees the booth
        isReserved = false;
        // sets the count of people to 0
        this.setNumberOfPeople(0);
        // sets price to 0.
        this.price = 0;
        // Removes all the ordered cocktails and delicacies
        this.cocktailOrders.clear();
        this.delicacyOrders.clear();
    }
}
