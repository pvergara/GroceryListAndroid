package org.ecos.groceryList.dtos.items;

import android.support.annotation.NonNull;

public class Item {
    private final Identity mIdentity;

    private Name mName;
    private Quantity mQuantity;

    public Name getName() {
        return mName;
    }

    public void setName(Name name) {
        mName = name;
    }

    public Item(Identity identity) {
        mIdentity = identity;

        mName = createDefaultName();
        mQuantity = createDefaultQuantity();
    }

    @NonNull
    private Quantity createDefaultQuantity() {
        return Quantity.fromDefault();
    }

    @NonNull
    private Name createDefaultName() {
        return Name.from("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return mIdentity.equals(item.mIdentity);

    }

    @Override
    public int hashCode() {
        return mIdentity.hashCode();
    }

    @Override
    public String toString() {
        return "Item{" + "mIdentity=" + mIdentity +
            ", mName=" + mName +
            ", mQuantity=" + mQuantity +
            '}';
    }

    @SuppressWarnings("WeakerAccess")
    public Identity getIdentity() {
        return mIdentity;
    }


    public void setQuantity(Quantity quantity) {
        this.mQuantity = quantity;
    }

    public Quantity getQuantity() {
        return mQuantity;
    }
}
