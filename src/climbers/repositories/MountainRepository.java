package climbers.repositories;

import climbers.models.mountain.Mountain;

import java.util.ArrayList;
import java.util.Collection;

public class MountainRepository implements Repository<Mountain>{
    private Collection<Mountain> mountains;
    //private final Map<String, Mountain> mountains;

    public MountainRepository() {
        this.mountains = new ArrayList<>();
        //this.mountains = new LinkedHashMap<>();
    }

    @Override
    public Collection<Mountain> getCollection() {
        return mountains.stream().toList();
        // return Collections.unmodifiableCollection(this.mountains.values());
    }

    @Override
    public void add(Mountain mountain) {
        mountains.add(mountain);
        //mountains.put(site.getName(), site);
    }

    @Override
    public boolean remove(Mountain mountain) {
        return mountains.remove(mountain);
        //  return mountains.remove(site.getName()) != null;
    }

    @Override
    public Mountain byName(String name) {
        return mountains.stream()
                .filter(mountain -> mountain.getName().equals(name))
                .findFirst()
                .orElse(null);

        //return mountains.get(name);
    }
}
