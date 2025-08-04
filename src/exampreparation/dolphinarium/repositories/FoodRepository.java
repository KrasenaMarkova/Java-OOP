package exampreparation.dolphinarium.repositories;

import exampreparation.dolphinarium.entities.foods.Food;

public interface FoodRepository {
    void add(Food food);

    boolean remove(Food food);

    Food findByType(String type);
}
