package Interfaces_and_abstraction_lab04.say_hello_extended04;

public class European extends BasePerson{
    public European(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
