package exampreparation.dolphinarium.entities.pools;

import exampreparation.dolphinarium.entities.dolphins.Dolphin;
import exampreparation.dolphinarium.entities.foods.Food;

import java.util.Collection;

public interface Pool {
    String getName();

    int getCapacity();

    Collection<Dolphin> getDolphins();

    Collection<Food> getFoods();


    void addDolphin(Dolphin dolphin);

    void removeDolphin(Dolphin dolphin);

    void addFood(Food food);

//    String getInfo(); remove
}
