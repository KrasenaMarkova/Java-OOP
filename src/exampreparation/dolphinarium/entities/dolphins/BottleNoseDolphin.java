package exampreparation.dolphinarium.entities.dolphins;


public class BottleNoseDolphin extends BaseDolphin {

/*    public class BottleNoseDolphin extends BaseDolphin {
        public BottleNoseDolphin(String name, int energy) {
            super(name, energy);
        }

        @Override
        public void jump() {
            decreaseEnergy(200);
        }
    public BottleNoseDolphin(String name, int energy) {

        super(name, energy);
    }*/


    @Override
    public void jump() {
        super.jump();
        this.setEnergy(this.getEnergy() - 190);
    }

}
