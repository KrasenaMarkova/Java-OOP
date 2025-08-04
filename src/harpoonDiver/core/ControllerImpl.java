package harpoonDiver.core;

import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private final DiverRepository diverRepository;
    private final DivingSiteRepository divingSiteRepository;
    private int siteCount;

    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver;

        switch (kind) {
            case "OpenWaterDiver":
                diver = new OpenWaterDiver(diverName);
                break;
            case "DeepWaterDiver":
                diver = new DeepWaterDiver(diverName);
                break;
            case "WreckDiver":
                diver = new WreckDiver(diverName);
                break;
            default:
                throw new IllegalArgumentException(DIVER_INVALID_KIND);
        }
        this.diverRepository.add(diver);
        return String.format(DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite site = new DivingSiteImpl(siteName);

        for (String seaCreature : seaCreatures) {
            site.getSeaCreatures().add(seaCreature);
        }

        this.divingSiteRepository.add(site);

        return String.format(DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = this.diverRepository.byName(diverName);

        if (diver == null) {
            throw new IllegalArgumentException(DIVER_DOES_NOT_EXIST);
        }
        diverRepository.remove(diver);

        return String.format(DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        List<Diver> divers = this.diverRepository.getCollection().stream()
                .filter(diver -> diver.getOxygen() > 30)
                .collect(Collectors.toList());

        //Collection<Diver> divers1 = this.diverRepository.getCollection();

        if (divers.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }

        DivingSite site = this.divingSiteRepository.byName(siteName);
        Diving diving = new DivingImpl();
        diving.searching(site, divers);

        long removed = divers.stream().filter(diver -> diver.getOxygen() == 0).count();
        this.siteCount++;
        return String.format(SITE_DIVING, siteName, removed);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_DIVING_SITES, this.siteCount));
        sb.append(System.lineSeparator());
        sb.append(FINAL_DIVERS_STATISTICS);

        Collection<Diver> divers = diverRepository.getCollection();

        for (Diver diver : divers) {
            sb.append(System.lineSeparator());

            sb.append(String.format(FINAL_DIVER_NAME, diver.getName()));
            sb.append(System.lineSeparator());

            sb.append(String.format(FINAL_DIVER_OXYGEN, diver.getOxygen()));
            sb.append(System.lineSeparator());

            if (diver.getSeaCatch().getSeaCreatures().isEmpty()) {
                sb.append(String.format(FINAL_DIVER_CATCH, "None"));
            } else {
                sb.append(String.format(FINAL_DIVER_CATCH,
                        String.join(FINAL_DIVER_CATCH_DELIMITER, diver.getSeaCatch().getSeaCreatures())));
            }
        }
        return sb.toString().trim();
    }
}
