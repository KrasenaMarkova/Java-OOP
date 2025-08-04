package exampreparation.christmasPastryShop.repositories.interfaces;

import christmasPastryShop.repositories.interfaces.Repository;

public interface DelicacyRepository<T> extends Repository<T> {
    T getByName(String name);
}
