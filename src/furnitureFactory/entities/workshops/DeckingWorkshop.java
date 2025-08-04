package furnitureFactory.entities.workshops;

public class DeckingWorkshop extends BaseWorkshop{
    public DeckingWorkshop(int woodQuantity) {
        super(woodQuantity, 0);
    }

    @Override
    public void produce() {
        //•	The method reduces the Workshop's wood quantity by 150 units. (woodQuantityReduceFactor = 150)
        setWoodQuantityReduceFactor(150);
    }
}
