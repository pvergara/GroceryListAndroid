package org.ecos.groceryList.viewModels;

import android.util.Log;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.groceryList.dtos.Items;
import org.ecos.groceryList.dtos.items.Item;
import org.ecos.groceryList.dtos.items.Name;
import org.ecos.groceryList.events.NewItemSendEvent;
import org.ecos.groceryList.events.UpdateItemSendEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

import static org.ecos.groceryList.viewModels.ListCreationViewModel.Properties.addItem;
import static org.ecos.groceryList.viewModels.ListCreationViewModel.Properties.updateItem;

public class ListCreationViewModelImpl implements ListCreationViewModel {
    private final MessagingService mMessagingService;
    private Items mCollection;
    private OnChangeListener mOnChangeListener;

    public Items getCollection() {
        return mCollection;
    }


    @Override
    public void setOnchangeListener(OnChangeListener onChangeListener) {
        mOnChangeListener = onChangeListener;
    }

    public ListCreationViewModelImpl(MessagingService messagingService) {
        mMessagingService = messagingService;

        mMessagingService.registerMe(this);

    }

    @Override
    public void init() {
        initTheList();
    }

    private void initTheList() {
        mCollection = new Items();
        initData();
    }

    private void initData() {
        mCollection.addAll(Arrays.asList(
                Name.from("Lechuga"),
                Name.from("Tomate"),
                Name.from("Arroz"),
                Name.from("Leche"),
                Name.from("Salchichas"),
                Name.from("Lavavajillas"),
                Name.from("Papel higiénico")/*,
            "Yogur de beber","Pavo","Queso","Comida gatos","Sopa 'Soba'","Detergente","Suaviante",
            "Café","Galletas","Agua","Bebida de cola","Puré de patatas","Manzana"*/
            ));
    }

    //TODO: ABSTRACTION (register inside Messaging Service)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(NewItemSendEvent event) {
        addNewItemWith(event.getItemText());
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(UpdateItemSendEvent event){
        Item itemWithNewValues = event.getItem();

        Item itemToUpdate = lookFor(itemWithNewValues);

        applyChanges(itemWithNewValues, itemToUpdate);
    }

    private void applyChanges(Item itemWithNewValues, Item itemToUpdate) {
        Log.e("fdsfs","applyChanges. itemWithNewValues: "+ itemWithNewValues + ", itemToUpdate: "+itemToUpdate+")");
        updateItemWith(itemWithNewValues, itemToUpdate);
        mOnChangeListener.onPropertyChange(updateItem, itemToUpdate.getName());
    }

    private void updateItemWith(Item itemWithNewValues, Item itemToUpdate) {
        itemToUpdate.setName(itemWithNewValues.getName());
        itemToUpdate.setQuantity(itemWithNewValues.getQuantity());
    }

    private Item lookFor(Item item) {
        for (Item itemToUpdate : mCollection) {
            if(itemToUpdate.equals(item))
                return item;
        }
        return item;
    }

    private void addNewItemWith(CharSequence itemText) {
        if(itemText!=null) {
            mCollection.add(Name.from(itemText));
            mOnChangeListener.onPropertyChange(addItem, itemText);
        }
    }


    @Override
    public void deInit(){
        mMessagingService.unRegisterMe(this);
    }

}
