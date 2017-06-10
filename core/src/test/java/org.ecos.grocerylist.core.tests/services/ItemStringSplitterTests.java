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
        Map<ItemPart,String> result = ItemStringSplitter.INSTANCE.split("lettuce");

        assertThat(result.keySet(),hasSize(1));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("lettuce")));
    }

    @Test
    public void aTextWithTwoExpressionsOneAWordAndTheOtherAnXCharAndADigitMustBeInterpretedAsNameAndQuantityOfAnItem() throws SplitterException {
        Map<ItemPart,String> result = ItemStringSplitter.INSTANCE.split("lettuce x2");

        assertThat(result.keySet(),hasSize(2));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("lettuce")));
        assertThat(result.containsKey(ItemPart.quantity),is(true));
        assertThat(result.get(ItemPart.quantity),is(equalTo("2")));
    }

    @Test(expected = SplitterException.class)
    public void youCanNotWriteOnlyQuantity() throws SplitterException {
        ItemStringSplitter.INSTANCE.split("x2");
    }

    @Test(expected = SplitterException.class)
    public void youCanNotWriteQuantityMoreThanOnce() throws SplitterException {
        ItemStringSplitter.INSTANCE.split("Lettuce x2x1");
    }

    @Test
    public void theItemsNameCanHasMoreThanOneWord() throws SplitterException {
        Map<ItemPart,String> result = ItemStringSplitter.INSTANCE.split("Powder milk");

        assertThat(result.keySet(),hasSize(1));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("Powder milk")));
    }

    @Test
    public void theItemsNameCanHasMoreThanOneWordAndThenTheQuantity() throws SplitterException {
        Map<ItemPart,String> result = ItemStringSplitter.INSTANCE.split("Powder milk x2");

        assertThat(result.keySet(),hasSize(2));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("Powder milk")));
        assertThat(result.containsKey(ItemPart.quantity),is(true));
        assertThat(result.get(ItemPart.quantity),is(equalTo("2")));
    }
}
