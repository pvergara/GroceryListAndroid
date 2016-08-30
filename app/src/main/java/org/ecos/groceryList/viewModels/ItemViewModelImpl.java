package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.groceryList.events.ItemSendEvent;
import org.ecos.groceryList.events.ItemSendToUpdateEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import static org.ecos.groceryList.viewModels.ItemViewModel.Properties.changeActionStatus;
import static org.ecos.groceryList.viewModels.ItemViewModel.Properties.changeItemText;


public class ItemViewModelImpl implements ItemViewModel {
    private CharSequence mItemText;
    private OnChangeListener mOnChangeListener;
    private MessagingService mMessagingService;

    @Inject
    public ItemViewModelImpl(MessagingService messagingService) {
        mMessagingService = messagingService;
        messagingService.registerMe(this);
    }

    @Override
    public void deInit(){
        mMessagingService.unRegisterMe(this);
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
        mOnChangeListener.onPropertyChange(changeItemText,"");
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
        mOnChangeListener.onPropertyChange(changeActionStatus,enableActionButton);
    }

    //TODO: ABSTRACTION (register inside Messaging Service)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(ItemSendToUpdateEvent event) {
        showItem(event.getItemText());
    }

    private void showItem(String itemText) {mOnChangeListener.onPropertyChange(changeItemText,itemText);
    }

}
