package org.ecos.groceryList.events;

public class NewItemSendEvent {
    private CharSequence mItemText;

    public NewItemSendEvent(CharSequence itemText) {
        mItemText = itemText;
    }

    public CharSequence getItemText() {
        return mItemText;
    }
}
