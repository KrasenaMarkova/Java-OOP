package harpoonDiver;

import harpoonDiver.core.Controller;
import harpoonDiver.core.Engine;
import harpoonDiver.core.EngineImpl;
//https://alpha.judge.softuni.org/contests/java-oop-regular-exam-9-december-2023/4418/practice

public class Main {

    public static void main(String[] args) {
        Controller controller = null; //TODO new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
