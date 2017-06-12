package org.ecos.groceryList.events;

import org.ecos.grocerylist.core.exceptions.SplitterException;
import org.ecos.grocerylist.core.items.ItemPart;
import org.ecos.grocerylist.core.service.ItemStringSplitter;

import java.util.HashMap;
import java.util.Map;

import static org.ecos.grocerylist.core.items.ItemPart.name;
import static org.ecos.grocerylist.core.items.ItemPart.quantity;

public class NewItemSendEvent {
    private Map<ItemPart, CharSequence> mItemsMap;

    public NewItemSendEvent(CharSequence itemText) {
        try {
            ItemStringSplitter splitter = new ItemStringSplitter();
            mItemsMap = splitter.split(itemText);
        } catch (SplitterException e) {
            createMapIfDont();
            mItemsMap.put(name, itemText);
        }
    }

    private void createMapIfDont() {
        if (mItemsMap == null)
            mItemsMap = new HashMap<>();
    }

    public CharSequence getItemText() {
        return mItemsMap.get(name);
    }

    private boolean hasQuantity() {
        return mItemsMap.containsKey(quantity);
    }

    public CharSequence getItemQuantity() {
        if (!hasQuantity())
            mItemsMap.put(quantity,"1");
        return mItemsMap.get(quantity);
    }
}
