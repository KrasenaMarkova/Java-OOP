package exampreparation.goldDigger.models.museum;

import goldDigger.models.museum.Museum;

import java.util.ArrayList;
import java.util.Collection;

public class BaseMuseum implements Museum {
    private Collection<String> exhibits;

    public BaseMuseum() {
        this.exhibits = new ArrayList<>();
    }
    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }
}
