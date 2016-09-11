package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.groceryList.dtos.items.Item;
import org.ecos.groceryList.dtos.items.Name;
import org.ecos.groceryList.events.NewItemSendEvent;
import org.ecos.groceryList.events.ItemSendToUpdateEvent;
import org.ecos.groceryList.events.UpdateItemSendEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import static org.ecos.groceryList.viewModels.ItemViewModel.Properties.changeActionStatus;
import static org.ecos.groceryList.viewModels.ItemViewModel.Properties.changeItemText;


public class ItemViewModelImpl implements ItemViewModel {
    private CharSequence mItemText;
    private OnChangeListener mOnChangeListener;
    private MessagingService mMessagingService;
    private Item mItemToUpdate;

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

        if(isOnUpdateMode()) {
            mItemToUpdate.setName(Name.from(mItemText));
            mMessagingService.send(new UpdateItemSendEvent(mItemToUpdate));
        }
        else
            mMessagingService.send(new NewItemSendEvent(mItemText));

        cleanItemText();
        eraseItemToUpdate();
    }

    private boolean isOnUpdateMode() {
        return mItemToUpdate!=null;
    }

    private void eraseItemToUpdate() {
        mItemToUpdate = null;
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
        if (itemText.toString().replaceAll(" ","").length()==0)
            enableActionButton = false;
        mOnChangeListener.onPropertyChange(changeActionStatus,enableActionButton);
    }

    //TODO: ABSTRACTION (register inside Messaging Service)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(ItemSendToUpdateEvent event) {
        mItemToUpdate = event.getItem();
        showItem(mItemToUpdate.getName().toString());
    }

    private void showItem(String itemText) {
        mOnChangeListener.onPropertyChange(changeItemText,itemText);
    }

}
