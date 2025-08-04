package exampreparation.dolphinarium.entities.dolphins;


import exampreparation.dolphinarium.entities.foods.Food;

public interface Dolphin {
    String getName();
    int getEnergy();

    void jump();

    void eat(Food food);

}
