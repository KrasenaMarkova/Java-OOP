package exampreparation.dolphinarium.entities.dolphins;


public class SpottedDolphin extends BaseDolphin {

   /* public class SpottedDolphin extends BaseDolphin {
        public SpottedDolphin(String name, int energy) {
            super(name, energy);
        }

        @Override
        public void jump() {
            decreaseEnergy(100);
        }*/
    public SpottedDolphin(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void jump() {
        super.jump();
        this.setEnergy(this.getEnergy() - 90);
    }

}
