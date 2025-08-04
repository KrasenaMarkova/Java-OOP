package goldDigger;
//https://alpha.judge.softuni.org/contests/java-oop-retake-exam-22-august-2022/3586/practice

import goldDigger.core.Controller;
import goldDigger.core.ControllerImpl;
import goldDigger.core.Engine;
import goldDigger.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
