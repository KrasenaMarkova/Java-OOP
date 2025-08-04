package inheritance_exercises03.animals6;

public class Kitten extends Cat{
    public Kitten(String name, int age) {
        super(name, age,"Female");
    }
    @Override
    public String produceSound() {
        return "Meow";
    }
}
