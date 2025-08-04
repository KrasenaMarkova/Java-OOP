package furnitureFactory.entities.workshops;

import furnitureFactory.entities.wood.Wood;

public abstract class BaseWorkshop implements Workshop {
    private int woodQuantity;
    private int producedFurnitureCount;
    private int woodQuantityReduceFactor;

    public BaseWorkshop(int woodQuantity, int woodQuantityReduceFactor) {
       setWoodQuantity(woodQuantity);
        this.producedFurnitureCount = 0;
        setWoodQuantityReduceFactor(woodQuantityReduceFactor);

    }

    public void setWoodQuantity(int woodQuantity) {
        this.woodQuantity = woodQuantity;
        if (this.woodQuantity < 0) {
            this.woodQuantity = 0;
        }
    }

    public void setWoodQuantityReduceFactor(int woodQuantityReduceFactor) {
        this.woodQuantityReduceFactor = woodQuantityReduceFactor;
    }

    @Override
    public int getWoodQuantity() {
        return this.woodQuantity;
    }

    @Override
    public int getProducedFurnitureCount() {
        return this.producedFurnitureCount;
    }

    @Override
    public int getWoodQuantityReduceFactor() {
        return this.woodQuantityReduceFactor;
    }

    @Override
    public void addWood(Wood wood) {
       /* if (wood.getClass().getSimpleName().equals("OakWood")) {
            woodQuantity += 200;
        }*/
        addWood(wood);
       // woodQuantity++;
    }

    @Override
    public void produce() {

        //o	In the furniture production process,
        // every time this method is called it reduces the amount of wood
        // in the given Workshop by the value of its woodQuantityReduceFactor.
        if (woodQuantity >= woodQuantityReduceFactor) {
            woodQuantity -= woodQuantityReduceFactor;
            //Increases by 1 total count of produced furniture.
            producedFurnitureCount++;
        }
    }
}
