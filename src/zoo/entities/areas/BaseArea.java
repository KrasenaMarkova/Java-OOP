package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.animals.BaseAnimal;
import zoo.entities.foods.BaseFood;
import zoo.entities.foods.Food;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static zoo.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area{
    private String name;
    private int capacity;
    private Collection<BaseFood> foods;
    // private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<BaseFood> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        int sum = foods.stream().mapToInt(BaseFood::getCalories).sum();
        //int sum = 0;
        //        for (BaseFood food : foods) {
        //            int calories = food.getCalories();
        //            sum += calories;
        //        }
        return sum;
    }
    //@Override
    //    public int sumCalories() {
    //        int sum = 0;
    //        for (Food food : foods) {
    //            sum += food.getCalories();
    //        }
    //        return sum;
    //    }

    @Override
    public void addAnimal(Animal animal) {
        if (animal.getKg() >= capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        //this.foods.add(food);
        foods.add((BaseFood) food);
    }

    @Override
    public void feed() {
        for (Animal animal : this.animals) {
            animal.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
        sb.append(String.format("Animals: ")).append(System.lineSeparator());

        if (animals.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(this.animals.stream().map(Animal::getName).collect(Collectors.joining(" ")));
        }
        sb.append(System.lineSeparator()).append("Foods: ")
                .append(this.foods.size());
        sb.append(System.lineSeparator()).append("Calories: ")
                .append(this.sumCalories());

        return sb.toString().trim();
    }
}
