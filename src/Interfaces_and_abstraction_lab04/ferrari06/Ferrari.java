package Interfaces_and_abstraction_lab04.ferrari06;

public class Ferrari implements Car{

    private String driverName;
    private static final String MODEL = "488-Spider";

    public Ferrari(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    @Override
    public String toString() {
        //"{model}/{brakes}/{gas}/{driver's name}"
        return "%s/%s/%s/%s".formatted(MODEL, brakes(), gas(), getDriverName());
    }
    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String gas() {
        return "brum-brum-brum-brrrrr";
    }
}
