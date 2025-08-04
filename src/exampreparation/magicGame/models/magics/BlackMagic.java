package exampreparation.magicGame.models.magics;

import magicGame.models.magics.MagicImpl;

public class BlackMagic extends MagicImpl {

    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    private int bullets = getBulletsCount();

    @Override
    public int fire() {
        if (bullets - 10 < 0) {
            bullets = 0;
            return 0;
        } else {
            bullets -= 10;
            return 10;
        }
    }
}
