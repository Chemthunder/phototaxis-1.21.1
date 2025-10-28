package silly.chemthunder.phototaxis.object.entity.moths;

import java.util.Arrays;
import java.util.Comparator;

public enum MothVariant {
    BASIC(0),
    DUSTY(1),
    REDHEAD(2),
    IVORY(3);

    private static final MothVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            MothVariant::getId)).toArray(MothVariant[]::new);
    private final int id;

    MothVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MothVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}