package org.ecos.groceryList.events;

public class ItemSendToUpdateEvent {
    private String mItemText;

    public ItemSendToUpdateEvent(String itemText) {
        mItemText = itemText;
    }

    public String getItemText() {
        return mItemText;
    }
}
