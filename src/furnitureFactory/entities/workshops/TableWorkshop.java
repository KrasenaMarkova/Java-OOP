package furnitureFactory.entities.workshops;

public class TableWorkshop extends BaseWorkshop{
    public TableWorkshop(int woodQuantity) {
        //the TableWorkshop reduces its wood quantity by 50 units while producing single furniture
        super(woodQuantity, 50);
    }

    @Override
    public void produce() {
        //•	The method reduces the Workshop's wood quantity by 50 units. (woodQuantityReduceFactor = 50)
        setWoodQuantityReduceFactor(50);
    }
}
