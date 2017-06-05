package org.ecos.grocerylist.core.service;

import org.ecos.grocerylist.core.items.ItemPart;

import java.util.HashMap;
import java.util.Map;

public class ItemStringSplitter {
    public static Map<ItemPart, String> split(String itemString) {
        return new HashMap<ItemPart,String>(){{
            put(ItemPart.name,itemString);}};
    }
}
