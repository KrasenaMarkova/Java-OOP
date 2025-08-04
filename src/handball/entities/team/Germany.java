package handball.entities.team;

import handball.entities.team.BaseTeam;

public class Germany extends BaseTeam {

    public Germany(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        int newAdvantage = getAdvantage() + 145;
        setAdvantage(newAdvantage);
    }
}
