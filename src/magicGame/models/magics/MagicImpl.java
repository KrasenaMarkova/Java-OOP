package magicGame.models.magics;

import static magicGame.common.ExceptionMessages.INVALID_MAGICIAN_NAME;
import static magicGame.common.ExceptionMessages.INVALID_MAGIC_BULLETS_COUNT;

public abstract class MagicImpl implements Magic {
    private String name;
    private int bulletsCount;

    public MagicImpl(String name, int bulletsCount) {
        setName(name);
        setBulletsCount(bulletsCount);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.name = name;
    }

    public void setBulletsCount(int bulletsCount) {
        if (bulletsCount < 0 ){
            throw new IllegalArgumentException(INVALID_MAGIC_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

    @Override
    abstract public int fire();
}
