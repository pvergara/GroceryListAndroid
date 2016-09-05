package org.ecos.groceryList.events;

import org.ecos.groceryList.dtos.items.Item;

public class ItemSendToUpdateEvent {
    private Item mItemText;

    public ItemSendToUpdateEvent(Item itemText) {
        mItemText = itemText;
    }

    public Item getItem() {
        return mItemText;
    }
}
