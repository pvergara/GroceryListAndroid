package org.ecos.groceryList.events;

public class ItemSendEvent {
    private CharSequence mItemText;

    public ItemSendEvent(CharSequence itemText) {
        mItemText = itemText;
    }

    public CharSequence getItemText() {
        return mItemText;
    }
}
