package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.booths.interfaces.Booth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BoothRepositoryImpl implements BoothRepository<Booth>{
    private Collection<Booth> booths;

    public BoothRepositoryImpl() {
        this.booths = new ArrayList<>();
    }

    @Override
    public Booth getByNumber(int number) {
        Booth booth1 = booths.stream()
                .filter(booth -> booth.getBoothNumber() == number)
                .findFirst()
                .orElse(null);
        return booth1;
    }

    @Override
    public Collection<Booth> getAll() {
        return Collections.unmodifiableCollection(this.booths);
    }

    @Override
    public void add(Booth booth) {
        this.booths.add(booth);
    }
}
