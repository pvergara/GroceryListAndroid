package org.ecos.groceryList.events;

import org.ecos.groceryList.dtos.items.Item;

public class UpdateItemSendEvent {
    private Item mItem;

    public UpdateItemSendEvent(Item item) {
        mItem = item;
    }

    public Item getItem() {
        return mItem;
    }
}
