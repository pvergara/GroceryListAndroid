package org.ecos.grocerylist.core.tests.services;

import org.ecos.grocerylist.core.exceptions.SplitterException;
import org.ecos.grocerylist.core.items.ItemPart;
import org.ecos.grocerylist.core.service.ItemStringSplitter;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class ItemStringSplitterTests {
    @Test
    public void aSingleTextMustBeInterpretedAsItemsName() throws SplitterException {
        Map<ItemPart,String> result = ItemStringSplitter.split("lettuce");

        assertThat(result.keySet(),hasSize(1));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("lettuce")));
    }

    @Test
    public void aTextWithWithTwoExpressionsOneAWordAndTheOtherAPlusAndADigitMustBeInterpretedAsNameAndQuantityOfAnItem() throws SplitterException {
        Map<ItemPart,String> result = ItemStringSplitter.split("lettuce +2");

        assertThat(result.keySet(),hasSize(3));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("lettuce")));
        assertThat(result.containsKey(ItemPart.incrementType),is(true));
        assertThat(result.get(ItemPart.incrementType),is(equalTo("+")));
        assertThat(result.containsKey(ItemPart.quantity),is(true));
        assertThat(result.get(ItemPart.quantity),is(equalTo("2")));
    }

    @Test(expected = SplitterException.class)
    public void youCanNotWriteOnlyQuantity() throws SplitterException {
        ItemStringSplitter.split("+2");
    }

    @Test(expected = SplitterException.class)
    public void youCanNotWriteQuantityMoreThanOnce() throws SplitterException {
        ItemStringSplitter.split("Lettuce +2+1");
    }

    @Test
    public void youCanAlsoWriteNegativeQuantity() throws SplitterException {
        Map<ItemPart,String> result = ItemStringSplitter.split("lettuce -2");

        assertThat(result.keySet(),hasSize(3));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("lettuce")));
        assertThat(result.containsKey(ItemPart.incrementType),is(true));
        assertThat(result.get(ItemPart.incrementType),is(equalTo("-")));
        assertThat(result.containsKey(ItemPart.quantity),is(true));
        assertThat(result.get(ItemPart.quantity),is(equalTo("2")));

    }

    @Test
    public void theItemsNameCanHasMoreThanOneWord() throws SplitterException {
        Map<ItemPart,String> result = ItemStringSplitter.split("Powder milk");

        assertThat(result.keySet(),hasSize(1));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("Powder milk")));
    }

    @Test
    public void theItemsNameCanHasMoreThanOneWordAndThenTheQuantity() throws SplitterException {
        Map<ItemPart,String> result = ItemStringSplitter.split("Powder milk +2");

        assertThat(result.keySet(),hasSize(3));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("Powder milk")));
        assertThat(result.containsKey(ItemPart.incrementType),is(true));
        assertThat(result.get(ItemPart.incrementType),is(equalTo("+")));
        assertThat(result.containsKey(ItemPart.quantity),is(true));
        assertThat(result.get(ItemPart.quantity),is(equalTo("2")));
    }
}
