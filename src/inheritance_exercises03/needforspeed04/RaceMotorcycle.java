package inheritance_exercises03.needforspeed04;

public class RaceMotorcycle extends Motorcycle{
    public RaceMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        setFuelConsumption(8);
    }
}
