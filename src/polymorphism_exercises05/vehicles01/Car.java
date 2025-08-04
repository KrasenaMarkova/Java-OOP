package polymorphism_exercises05.vehicles01;

public class Car extends Vehicle {

    public Car(double fuelQuantity, double fuelCostPerKm) {
        super(fuelQuantity, fuelCostPerKm + 0.9);
    }

    @Override
    public void refuel(double fuelToRefill) {
        this.setFuelQuantity(this.getFuelQuantity() + fuelToRefill);
    }
}
