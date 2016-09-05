package org.ecos.groceryList.dtos.items;

import java.util.UUID;

class Identity {

    private CharSequence mIdentity;

    private Identity(CharSequence identity) {
        mIdentity = identity;
    }

    public static Identity from(UUID identity){
        if(identity == null)
            throw new IllegalArgumentException("Identity can not be null");

        return new Identity(identity.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        return mIdentity.equals(identity.mIdentity);

    }

    @Override
    public int hashCode() {
        return mIdentity.hashCode();
    }

    @Override
    public String toString() {
        return "Identity{" +
                "mIdentity=" + mIdentity +
                '}';
    }
}
