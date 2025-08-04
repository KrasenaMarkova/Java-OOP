package exampreparation.zoo.entities.area;

import exampreparation.zoo.entities.animal.Animal;
import exampreparation.zoo.entities.food.Food;

import java.util.Collection;

public interface Area {
    int sumCalories();

    String getName();

    void addAnimal(Animal animal);

    void removeAnimal(Animal animal);

    void addFood(Food food);

    void feed();

    String getInfo();

    Collection<Animal> getAnimal();

    Collection<Food> getFood();
}
