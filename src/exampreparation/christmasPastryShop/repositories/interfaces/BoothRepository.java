package exampreparation.christmasPastryShop.repositories.interfaces;

import christmasPastryShop.repositories.interfaces.Repository;

public interface BoothRepository<T> extends Repository<T> {
    T getByNumber(int number);
}
