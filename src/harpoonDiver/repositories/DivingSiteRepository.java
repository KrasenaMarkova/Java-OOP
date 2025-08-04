package harpoonDiver.repositories;


import harpoonDiver.models.divingSite.DivingSite;

import java.util.ArrayList;
import java.util.Collection;

public class DivingSiteRepository implements Repository<DivingSite>{
    private Collection<DivingSite> sites;

    public DivingSiteRepository() {
        this.sites = new ArrayList<>();
    }

    @Override
    public Collection<DivingSite> getCollection() {
        return this.sites.stream().toList();
    }

    @Override
    public void add(DivingSite divingSite) {
        sites.add(divingSite);
    }

    @Override
    public boolean remove(DivingSite divingSite) {
        return sites.remove(divingSite);
    }

    @Override
    public DivingSite byName(String name) {
        return sites.stream()
                .filter(divingSite -> divingSite.equals(name))
                .findFirst()
                .orElse(null);
    }
}
