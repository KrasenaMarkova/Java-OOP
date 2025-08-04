package interfaces_and_abstraction_exercises04.birthdaycelebrations03;

public class Robot implements Identifiable {

    private String id;
    private String model;

    public Robot(String id, String model) {
        this.id = id;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public String getId() {
        return id;
    }
}
