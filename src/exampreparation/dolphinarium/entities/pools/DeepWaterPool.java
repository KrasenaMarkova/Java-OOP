package exampreparation.dolphinarium.entities.pools;

public class DeepWaterPool extends BasePool {

   /* public class DeepWaterPool extends BasePool {
        public DeepWaterPool(String name) {
            super(name, 5);
        }*/
    private static final int CAPACITY = 5;

    public DeepWaterPool(String name) {
        super(name, CAPACITY);
    }
}
