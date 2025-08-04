package exampreparation.vehicleShop.models.shop;

import exampreparation.vehicleShop.models.vehicle.Vehicle;
import exampreparation.vehicleShop.models.worker.Worker;

public interface Shop {
    void make(Vehicle vehicle, Worker worker);
}
