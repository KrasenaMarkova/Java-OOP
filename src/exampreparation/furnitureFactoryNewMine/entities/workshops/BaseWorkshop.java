package exampreparation.furnitureFactoryNewMine.entities.workshops;

import furnitureFactory.entities.factories.Factory;
import furnitureFactory.entities.wood.Wood;
import furnitureFactory.entities.workshops.Workshop;

public abstract class BaseWorkshop implements Workshop {
    private int woodQuantity;
    private int producedFurnitureCount;
    private int woodQuantityReduceFactor;

    public BaseWorkshop(int woodQuantity, int woodQuantityReduceFactor) {
        setWoodQuantity(woodQuantity);
        this.producedFurnitureCount = 0;
        this.woodQuantityReduceFactor = woodQuantityReduceFactor;
    }

    public void setWoodQuantity(int woodQuantity) {
        this.woodQuantity = woodQuantity;
        if (this.woodQuantity < 0) {
            setWoodQuantity(0);
        }
    }

    public void setWoodQuantityReduceFactor(int woodQuantityReduceFactor) {
        this.woodQuantityReduceFactor = woodQuantityReduceFactor;
    }

    @Override
    public int getWoodQuantity() {
        return woodQuantity;
    }

    @Override
    public int getProducedFurnitureCount() {
        return producedFurnitureCount;
    }

    @Override
    public int getWoodQuantityReduceFactor() {
        return woodQuantityReduceFactor;
    }

    @Override
    abstract public void addWood(Wood wood);

    @Override
    public void produce() {
        //o	In the furniture production process, every time this method is called it reduces
        // the amount of wood in the given Workshop by the value of its woodQuantityReduceFactor.
        woodQuantity -= woodQuantityReduceFactor;
        //o	Increases by 1 total count of produced furniture.
        producedFurnitureCount++;
    }
}
