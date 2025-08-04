package inheritance_exercises03.needforspeed04;

public class SportCar extends Car{
    public SportCar(double fuel, int horsePower) {
        super(fuel, horsePower);
        setFuelConsumption(10);
    }
}
