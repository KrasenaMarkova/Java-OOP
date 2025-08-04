package exampreparation.magicGame.models.region;

import exampreparation.magicGame.models.magicians.Magician;

import java.util.Collection;

public interface Region {
    String start(Collection<Magician> magicians);

}
