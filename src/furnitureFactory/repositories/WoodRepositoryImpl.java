package furnitureFactory.repositories;

import furnitureFactory.entities.wood.Wood;

import java.util.ArrayList;
import java.util.Collection;

public class WoodRepositoryImpl implements WoodRepository{
    private Collection<Wood> woodType;

    public WoodRepositoryImpl() {
        this.woodType = new ArrayList<>();
    }

    @Override
    public void add(Wood wood) {
        woodType.add(wood);
    }

    @Override
    public boolean remove(Wood wood) {
        return woodType.remove(wood);
    }

    @Override
    public Wood findByType(String type) {
        return woodType.stream()
                .filter(wood -> wood.equals(type))
                .findFirst()
                .orElse(null);
    }
}
