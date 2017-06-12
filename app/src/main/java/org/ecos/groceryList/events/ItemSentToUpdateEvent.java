package org.ecos.groceryList.events;

import org.ecos.groceryList.dtos.items.Item;

public class ItemSentToUpdateEvent {
    private Item mItem;

    public ItemSentToUpdateEvent(Item item) {
        mItem = item;
    }

    public Item getItem() {
        return mItem;
    }
}
