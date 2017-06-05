package org.ecos.grocerylist.core.tests.services;

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
    public void aSingleTextMustBeInterpretedAsItemsName(){
        Map<ItemPart,String> result = ItemStringSplitter.split("lettuce");

        assertThat(result.keySet(),hasSize(1));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("lettuce")));
    }

    @Test
    public void aTextWithWithTwoExpressionsOneAWordAndTheOtherAPlusAndADigitMustBeInterpretedAsNameAndQuantityOfAnItem(){
        Map<ItemPart,String> result = ItemStringSplitter.split("lettuce +2");

        assertThat(result.keySet(),hasSize(2));
        assertThat(result.containsKey(ItemPart.name),is(true));
        assertThat(result.containsKey(ItemPart.quantity),is(true));
        assertThat(result.get(ItemPart.name),is(equalTo("lettuce")));
        assertThat(result.get(ItemPart.quantity),is(equalTo("2")));
    }
}
