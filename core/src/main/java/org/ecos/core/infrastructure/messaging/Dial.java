package org.ecos.core.infrastructure.messaging;

import java.util.UUID;

public class Dial {
    private final UUID mDialAsUUID;

    private Dial() {
        mDialAsUUID = UUID.randomUUID();
    }

    static Dial createANewDial() {
        return new Dial();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dial dial = (Dial) o;

        return mDialAsUUID.equals(dial.mDialAsUUID);

    }

    @Override
    public int hashCode() {
        return mDialAsUUID.hashCode();
    }
}
