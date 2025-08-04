package magicGame;

//https://alpha.judge.softuni.org/contests/java-oop-retake-exam-19-december-2022/3745/practice#1

import magicGame.core.Engine;
import magicGame.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
