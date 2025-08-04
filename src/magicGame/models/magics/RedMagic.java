package magicGame.models.magics;

public class RedMagic extends MagicImpl{

    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        setBulletsCount(this.getBulletsCount() - 1);
        if (this.getBulletsCount() == 0) {
            setBulletsCount(0);
        }
        return this.getBulletsCount();
    }
}
