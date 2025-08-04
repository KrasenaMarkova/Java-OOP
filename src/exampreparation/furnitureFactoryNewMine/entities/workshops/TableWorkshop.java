package exampreparation.furnitureFactoryNewMine.entities.workshops;

import furnitureFactory.entities.wood.Wood;
import furnitureFactory.entities.workshops.BaseWorkshop;

public class TableWorkshop extends BaseWorkshop {

    public TableWorkshop(int woodQuantity) {
        super(woodQuantity, 0);
    }

    @Override
    public void addWood(Wood wood) {
        //•	The method reduces the Workshop's wood quantity by 50 units. (woodQuantityReduceFactor = 50)
        this.setWoodQuantityReduceFactor(50);
        int currentWoodQuantity = wood.getWoodQuantity();
        setWoodQuantity(currentWoodQuantity -= getWoodQuantityReduceFactor());
    }
}
