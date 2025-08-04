package working_with_abstraction_lab01.hotelreservation04;

public enum Season {
    STRING(2),
    SUMMER(4),
    AUTUMN(1),
    WINTER(3);

    private final int multiplier;

    Season(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
