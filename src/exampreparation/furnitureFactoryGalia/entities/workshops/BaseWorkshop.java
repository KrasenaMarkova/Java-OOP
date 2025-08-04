package exampreparation.furnitureFactoryGalia.entities.workshops;

import furnitureFactory.entities.wood.Wood;
import furnitureFactory.entities.workshops.Workshop;

public abstract class BaseWorkshop implements Workshop {
    private int woodQuantity;
    private int producedFurnitureCount;
    private int woodQuantityReduceFactor;

    public BaseWorkshop(int woodQuantity, int woodQuantityReduceFactor) {
        setWoodQuantity(woodQuantity);
        this.woodQuantityReduceFactor = woodQuantityReduceFactor;
        this.producedFurnitureCount = 0;
    }

    public void setWoodQuantity(int woodQuantity) {
        if (woodQuantity < 0) {
            woodQuantity = 0;
        }
        this.woodQuantity = woodQuantity;
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
        this.setWoodQuantity(getWoodQuantity() + woodQuantity);
    }

    @Override
    public void produce() {
        this.woodQuantity -= this.woodQuantityReduceFactor;
        this.producedFurnitureCount++;
    }
}
