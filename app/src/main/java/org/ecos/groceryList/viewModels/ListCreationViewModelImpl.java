package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.groceryList.dtos.items.ItemFactory;
import org.ecos.groceryList.dtos.items.Items;
import org.ecos.groceryList.dtos.items.Item;
import org.ecos.groceryList.dtos.items.Name;
import org.ecos.groceryList.dtos.items.Quantity;
import org.ecos.groceryList.events.NewItemSendEvent;
import org.ecos.groceryList.events.UpdateItemSendEvent;
import org.ecos.groceryList.exceptions.EmptyQuantityException;
import org.ecos.groceryList.exceptions.FactoryException;
import org.ecos.groceryList.exceptions.NegativeQuantityException;
import org.ecos.groceryList.exceptions.NotFoundException;
import org.ecos.groceryList.exceptions.TooBigQuantityException;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

import static org.ecos.groceryList.viewModels.ListCreationViewModel.Properties.addItem;
import static org.ecos.groceryList.viewModels.ListCreationViewModel.Properties.updateItem;

public class ListCreationViewModelImpl implements ListCreationViewModel {
    private final MessagingService mMessagingService;
    private Items mItems;
    private OnChangeListener mOnChangeListener;

    public Items getItems() {
        return mItems;
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
        mItems = new Items();
        initData();
    }

    private void initData() {
        mItems.addAll(Arrays.asList(
        Name.from("Lechuga"),
        Name.from("Tomate")/*,
                Name.from("Leche"),
                Name.from("Salchichas"),
                Name.from("Lavavajillas"),
                Name.from("Papel higiénico"),
            "Yogur de beber","Pavo","Queso","Comida gatos","Sopa 'Soba'","Detergente","Suaviante",
            "Café","Galletas","Agua","Bebida de cola","Puré de patatas","Manzana"*/
        ));
        try {
            //@formatter:off
            Item item = ItemFactory.
                using(mItems).
                    and(Name.from("Arroz")).
                    and(Quantity.from(2)).
                create();
            //@formatter:on
            mItems.add(item);
        } catch (NegativeQuantityException | EmptyQuantityException | TooBigQuantityException | FactoryException e) {
            e.printStackTrace();
        }
    }

    //TODO: ABSTRACTION (register inside Messaging Service)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(NewItemSendEvent event) {
        Name itemName = Name.from(event.getItemText());

        try {
            Item itemToUpdate = mItems.lookFor(itemName);
            applyChanges(itemName, itemToUpdate);
        } catch (NotFoundException e) {
            addNewItemWith(event);
        }


    }

    //TODO: ABSTRACTION (register inside Messaging Service)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(UpdateItemSendEvent event) {
        Item itemWithNewValues = event.getItem();

        try {
            Item itemToUpdate = mItems.lookFor(itemWithNewValues);
            applyChanges(itemWithNewValues, itemToUpdate);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    private void applyChanges(Item itemWithNewValues, Item itemToUpdate) {
        updateItemWith(itemWithNewValues, itemToUpdate);
        mOnChangeListener.onPropertyChange(updateItem, itemToUpdate.getName());
    }

    private void applyChanges(Name itemName, Item itemToUpdate) {
        itemToUpdate.setName(itemName);

        mOnChangeListener.onPropertyChange(updateItem, itemToUpdate.getName());
    }

    private void updateItemWith(Item itemWithNewValues, Item itemToUpdate) {
        itemToUpdate.setName(itemWithNewValues.getName());
        itemToUpdate.setQuantity(itemWithNewValues.getQuantity());
    }

    private void addNewItemWith(NewItemSendEvent newItemSendEvent) {
        if (newItemSendEvent != null) {
            //@formatter:off
            try {
                Item item = ItemFactory.
                    using(mItems).
                        and(Name.from(newItemSendEvent.getItemText().toString())).
                        and(Quantity.from(Integer.valueOf(newItemSendEvent.getItemQuantity().toString()))).
                    create();
                mItems.add(item);
                mOnChangeListener.onPropertyChange(addItem, newItemSendEvent);
            //@formatter:on
            } catch (FactoryException ignored) {
            } catch (EmptyQuantityException | NegativeQuantityException | TooBigQuantityException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void deInit() {
        mMessagingService.unRegisterMe(this);
    }

}
