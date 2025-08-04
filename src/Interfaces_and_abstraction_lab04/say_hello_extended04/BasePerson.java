package Interfaces_and_abstraction_lab04.say_hello_extended04;

public abstract class BasePerson implements Person{
    private String name;

    protected BasePerson(String name) {
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
