package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.android.infrastructure.mvvm.binding.Property;
import org.ecos.groceryList.R;
import org.ecos.groceryList.events.ItemSendEvent;

import javax.inject.Inject;


public class ItemViewModelImpl implements ItemViewModel {
    private CharSequence mItemText;
    private OnChangeListener mOnChangeListener;
    private MessagingService mMessagingService;

    @Inject
    public ItemViewModelImpl(MessagingService messagingService) {
        mMessagingService = messagingService;
    }

    @Override
    public void setOnchangeListener(OnChangeListener onChangeListener) {
        mOnChangeListener = onChangeListener;
    }

    @Override
    public void sendItemText() {
        mMessagingService.send(new ItemSendEvent(mItemText));
        cleanItemText();
    }

    @Override
    public void init() {
        cleanItemText();
    }

    private void cleanItemText() {
        mOnChangeListener.onPropertyChange(Property.from(R.id.itemViewItemText),"");
    }

    @Override
    public void setItemText(CharSequence itemText) {
        mItemText = itemText;
        enableDisableButtonDependsOn(mItemText);
    }

    private void enableDisableButtonDependsOn(CharSequence itemText) {
        boolean enableActionButton = true;
        if (itemText.length()==0)
            enableActionButton = false;
        mOnChangeListener.onPropertyChange(Property.from(R.id.itemViewActionButton),enableActionButton);
    }
}
