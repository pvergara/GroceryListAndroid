package org.ecos.grocerylist.core.tests.services;

import org.ecos.grocerylist.core.exceptions.SplitterException;
import org.ecos.grocerylist.core.items.ItemPart;
import org.ecos.grocerylist.core.service.ItemStringSplitter;
import org.testng.annotations.*;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class ItemStringSplitterTests {
    @Test
    public void aSingleTextMustBeInterpretedAsItemsName() throws SplitterException {
        Map<ItemPart,CharSequence> result = new ItemStringSplitter().split("lettuce");

        assertThat(result.keySet(),hasSize(1));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name).toString(),is(equalTo("lettuce")));
    }

    @Test
    public void aTextWithTwoExpressionsOneAWordAndTheOtherAnXCharAndADigitMustBeInterpretedAsNameAndQuantityOfAnItem() throws SplitterException {
        Map<ItemPart,CharSequence> result = new ItemStringSplitter().split("lettuce x2");

        assertThat(result.keySet(),hasSize(2));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name).toString(),is(equalTo("lettuce")));
        assertThat(result.containsKey(ItemPart.quantity),is(true));
        assertThat(result.get(ItemPart.quantity).toString(),is(equalTo("2")));
    }

    @Test(expectedExceptions = SplitterException.class)
    public void youCanNotWriteOnlyQuantity() throws SplitterException {
        new ItemStringSplitter().split("x2");
    }

    @Test(expectedExceptions = SplitterException.class)
    public void youCanNotWriteQuantityMoreThanOnce() throws SplitterException {
        new ItemStringSplitter().split("Lettuce x2x1");
    }

    @Test
    public void theItemsNameCanHasMoreThanOneWord() throws SplitterException {
        Map<ItemPart,CharSequence> result = new ItemStringSplitter().split("Powder milk");

        assertThat(result.keySet(),hasSize(1));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name).toString(),is(equalTo("Powder milk")));
    }

    @Test
    public void bugTheItemsNameCanHasMoreThanOneWordNumbersWithTwoDigits() throws SplitterException {
        Map<ItemPart,CharSequence> result = new ItemStringSplitter().split("Powder milk x10");

        assertThat(result.get(ItemPart.quantity).toString(),is(equalTo("10")));
    }

    @Test
    public void theItemsNameCanHasMoreThanOneWordAndThenTheQuantity() throws SplitterException {
        Map<ItemPart,CharSequence> result = new ItemStringSplitter().split("Powder milk x2");

        assertThat(result.keySet(),hasSize(2));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name).toString(),is(equalTo("Powder milk")));
        assertThat(result.containsKey(ItemPart.quantity),is(true));
        assertThat(result.get(ItemPart.quantity).toString(),is(equalTo("2")));
    }
}
