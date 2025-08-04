package exampreparation.handball.entities.team;

import handball.entities.team.BaseTeam;

public class Bulgaria extends BaseTeam {



    public Bulgaria(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        this.setAdvantage(this.getAdvantage() + 115);
    }
}
