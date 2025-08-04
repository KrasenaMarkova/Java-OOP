package climbers.models.mountain;

import java.util.ArrayList;
import java.util.Collection;

import static climbers.common.ExceptionMessages.MOUNTAIN_NAME_NULL_OR_EMPTY;

public class MountainImpl implements Mountain{
    private String name;
    private Collection<String> peakList;

    public MountainImpl(String name) {
        this.name = name;
        this.peakList = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(MOUNTAIN_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getPeaksList() {
        return peakList;
    }

    @Override
    public String getName() {
        return name;
    }
}
