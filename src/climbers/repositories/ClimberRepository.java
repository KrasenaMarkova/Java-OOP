package climbers.repositories;

import climbers.models.climber.Climber;

import java.util.ArrayList;
import java.util.Collection;

public class ClimberRepository implements Repository<Climber>{
   //private Map<String, Climber> climbers;
    private Collection<Climber> climbers;

    public ClimberRepository() {
       // this.climbers = new LinkedHashMap<>();
        this.climbers = new ArrayList<>();
    }

    @Override
    public Collection<Climber> getCollection() {
        // return Collections.unmodifiableCollection(this.climbers.values());
        return climbers.stream().toList();
    }

    @Override
    public void add(Climber climber) {
        //climbers.put(climber.getName(), climber);
        climbers.add(climber);
    }

    @Override
    public boolean remove(Climber climber) {
        //return climbers.remove(climber.getName()) != null;
        return climbers.remove(climber);
    }

    @Override
    public Climber byName(String name) {
        // return climbers.get(name);

        return climbers.stream()
                .filter(climber -> climber.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
