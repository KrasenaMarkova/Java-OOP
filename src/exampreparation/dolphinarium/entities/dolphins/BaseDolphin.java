package exampreparation.dolphinarium.entities.dolphins;

import exampreparation.dolphinarium.common.ExceptionMessages;
import exampreparation.dolphinarium.entities.foods.Food;

public abstract class BaseDolphin implements Dolphin{

    /*public abstract class BaseDolphin implements Dolphin {
        private String name;
        private int energy;

        protected BaseDolphin(String name, int energy) {
            setName(name);
            this.energy = energy;
        }

        private void setName(String name) {
            if (name == null || name.isBlank()) {
                throw new NullPointerException(DOLPHIN_NAME_NULL_OR_EMPTY);
            }
            this.name = name;
        }

        protected void decreaseEnergy(int decrement) {
            energy -= decrement;
            energy = Math.max(energy, 0);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getEnergy() {
            return energy;
        }

        @Override
        public void eat(Food food) {
            energy += food.getCalories();
        }*/
    private String name;
    private int energy;


    public BaseDolphin(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.DOLPHIN_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
    @Override
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {

        if(energy <= 0) {
            energy = 0;
        }
        this.energy = energy;
    }

    @Override
    public void jump() {
        this.energy -= 10;
    }


    @Override
    public void eat(Food food) {
        this.energy += food.getCalories();
    }

}
