package org.ecos.groceryList.dtos.items;

public class Quantity {
    private float mQuantityAsDecimal;

    private Quantity(float quantityAsDecimal) {
        mQuantityAsDecimal = quantityAsDecimal;
    }

    public static Quantity from(int quantityAsInt){
        if(quantityAsInt<0)
            throw new IllegalArgumentException("The quantity can't be a negative value.");
        return new Quantity((float) quantityAsInt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantity quantity = (Quantity) o;

        return Float.compare(quantity.mQuantityAsDecimal, mQuantityAsDecimal) == 0;

    }

    @Override
    public int hashCode() {
        return (mQuantityAsDecimal != +0.0f ? Float.floatToIntBits(mQuantityAsDecimal) : 0);
    }

    @Override
    public String toString() {
        return "Quantity{" + "mQuantityAsDecimal=" + mQuantityAsDecimal +
            '}';
    }
}
