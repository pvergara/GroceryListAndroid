package org.ecos.groceryList.dtos.items;

class Name {
    private CharSequence mItemName;

    private Name(CharSequence itemName) {
        mItemName = itemName;
    }

    public static Name from (CharSequence itemName){
        if(itemName == null)
            throw new IllegalArgumentException("Name can not be null");
        return new Name(itemName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        return mItemName.equals(name.mItemName);
    }

    @Override
    public int hashCode() {
        return mItemName.hashCode();
    }

    @Override
    public String toString() {
        return "Name{" +
                "mItemName=" + mItemName +
                '}';
    }
}
