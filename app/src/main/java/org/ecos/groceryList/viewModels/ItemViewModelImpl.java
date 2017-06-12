package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.groceryList.dtos.items.Item;
import org.ecos.groceryList.dtos.items.Name;
import org.ecos.groceryList.dtos.items.Quantity;
import org.ecos.groceryList.events.ItemSentToUpdateEvent;
import org.ecos.groceryList.events.NewItemSendEvent;
import org.ecos.groceryList.events.UpdateItemSendEvent;
import org.ecos.groceryList.exceptions.EmptyQuantityException;
import org.ecos.groceryList.exceptions.NegativeQuantityException;
import org.ecos.groceryList.exceptions.TooBigQuantityException;
import org.ecos.grocerylist.core.exceptions.SplitterException;
import org.ecos.grocerylist.core.items.ItemPart;
import org.ecos.grocerylist.core.service.ItemStringSplitter;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

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
            try {
                Map<ItemPart, CharSequence> result = new ItemStringSplitter().split(mItemText);
                mItemToUpdate.setName(Name.from(result.get(ItemPart.name)));
                mItemToUpdate.setQuantity(Quantity.from(result.get(ItemPart.quantity)));
                mMessagingService.send(new UpdateItemSendEvent(mItemToUpdate));
            } catch (SplitterException | EmptyQuantityException |NegativeQuantityException |TooBigQuantityException e) {
                e.printStackTrace();
            }
        }
        else
            mMessagingService.send(new NewItemSendEvent(mItemText));

        cleanItemText();
        dismissItemToUpdate();
    }

    private boolean isOnUpdateMode() {
        return mItemToUpdate!=null;
    }

    private void dismissItemToUpdate() {
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
    public void onEvent(ItemSentToUpdateEvent event) {
        mItemToUpdate = event.getItem();
        if(mItemToUpdate.getQuantity().equals(Quantity.fromDefault()))
            showItem(mItemToUpdate.getName().toString());
        else
            showItem(mItemToUpdate.getName().toString() + " x" + mItemToUpdate.getQuantity().asStringFromInteger());
    }

    private void showItem(String itemText) {
        mOnChangeListener.onPropertyChange(changeItemText,itemText);
    }

}