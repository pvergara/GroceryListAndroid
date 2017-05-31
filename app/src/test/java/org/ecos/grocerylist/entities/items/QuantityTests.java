package org.ecos.grocerylist.entities.items;

import org.ecos.groceryList.dtos.items.Quantity;
import org.ecos.groceryList.exceptions.EmptyQuantityException;
import org.ecos.groceryList.exceptions.NegativeQuantityException;
import org.ecos.groceryList.exceptions.TooBigQuantityException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuantityTests {
    @Test
    public void howToCreateAQuantity() throws NegativeQuantityException, TooBigQuantityException, EmptyQuantityException {
        //Act
        Quantity quantity = Quantity.from(1);

        //Assertions
        assertThat(quantity,is(equalTo(Quantity.from(1))));
    }

    @Test(expected = NegativeQuantityException.class)
    public void whatIfIWantToCreateANegativeQuantity() throws NegativeQuantityException, TooBigQuantityException, EmptyQuantityException {
        //Act
        Quantity.from(-1);
    }

    @Test(expected = EmptyQuantityException.class)
    public void whatIfIWantToCreateAEmptyQuantity() throws NegativeQuantityException, TooBigQuantityException, EmptyQuantityException {
        //Act
        Quantity.from(0);
    }

    @Test(expected = TooBigQuantityException.class)
    public void QuantityCannotBeCreatedUpperThatNinetyNine() throws NegativeQuantityException, TooBigQuantityException, EmptyQuantityException {
        //Act
        Quantity.from(100);
    }
}
