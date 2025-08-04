package magicGame.models.magics;

public class BlackMagic extends MagicImpl{

    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        setBulletsCount(this.getBulletsCount() - 10);
        if (this.getBulletsCount() == 0) {
            setBulletsCount(0);
        }
        return this.getBulletsCount();
    }
}
