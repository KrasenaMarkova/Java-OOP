package exampreparation.furnitureFactoryNewMine.entities.workshops;

import furnitureFactory.entities.wood.Wood;
import furnitureFactory.entities.workshops.BaseWorkshop;

public class DeckingWorkshop extends BaseWorkshop {

    public DeckingWorkshop(int woodQuantity) {
        super(woodQuantity, 0);
    }

    @Override
    public void addWood(Wood wood) {
        //•	The method reduces the Workshop's wood quantity by 150 units.
        // (woodQuantityReduceFactor = 150)
        this.setWoodQuantityReduceFactor(150);
        int currentWoodQuantity = wood.getWoodQuantity();
        setWoodQuantity(currentWoodQuantity -= getWoodQuantityReduceFactor());
    }
}
