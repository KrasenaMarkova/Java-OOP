package exampreparation.dolphinarium.entities.foods;

public abstract class BaseFood implements Food{

   /* public abstract class BaseFood implements Food {
        private int calories;

        protected BaseFood(int calories) {
            this.calories = calories;
        }

        @Override
        public int getCalories() {
            return calories;
        }*/
    private int calories;

//    @Override
//    public String getName() {
//        return this.getClass().getSimpleName();
//    }

    public BaseFood(int calories) {
        this.calories = calories;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        BaseFood food = (BaseFood) o;
//        return Objects.equals(getName(), food.getName());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getName());
//    }

    @Override
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

}
