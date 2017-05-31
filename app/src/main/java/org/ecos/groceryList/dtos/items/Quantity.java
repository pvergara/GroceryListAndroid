package org.ecos.groceryList.dtos.items;

import org.ecos.groceryList.exceptions.EmptyQuantityException;
import org.ecos.groceryList.exceptions.NegativeQuantityException;
import org.ecos.groceryList.exceptions.TooBigQuantityException;
import org.ecos.groceryList.interfaces.AsString;

public class Quantity implements AsString {
    private float mQuantityAsDecimal;

    private Quantity(float quantityAsDecimal) {
        mQuantityAsDecimal = quantityAsDecimal;
    }

    @SuppressWarnings("WeakerAccess")
    public static Quantity fromDefault() {
        try {
            return Quantity.from(1);
        } catch (NegativeQuantityException | EmptyQuantityException | TooBigQuantityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Default must do not generate exception.");
        }
    }

    public static Quantity from(int quantityAsInt) throws NegativeQuantityException, EmptyQuantityException, TooBigQuantityException {
        if(quantityAsInt<0)
            throw new NegativeQuantityException("The quantity can't be a negative value.");
        if(quantityAsInt==0)
            throw new EmptyQuantityException("There's no reason to create an empty quantity.");
        if(quantityAsInt>99)
            throw new TooBigQuantityException("The quantity can't be upper than ninety-nine.");
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

    @Override
    public String asString() {
        return String.valueOf(Float.valueOf(mQuantityAsDecimal).intValue());
    }
}
