package exampreparation.christmasPastryShop.repositories.interfaces;

import christmasPastryShop.repositories.interfaces.Repository;

public interface CocktailRepository<T> extends Repository<T> {
    T getByName(String name);
}
