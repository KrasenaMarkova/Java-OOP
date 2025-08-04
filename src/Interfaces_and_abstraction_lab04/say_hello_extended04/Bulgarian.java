package Interfaces_and_abstraction_lab04.say_hello_extended04;

public class Bulgarian extends BasePerson{

    public Bulgarian(String name) {
       super(name);
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
