package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.groceryList.events.ItemSendEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;

import static org.ecos.groceryList.viewModels.ListCreationViewModel.Properties.addItem;

public class ListCreationViewModelImpl implements ListCreationViewModel {
    private final MessagingService mMessagingService;
    private ArrayList<String> mCollection;
    private OnChangeListener mOnChangeListener;

    public ArrayList<String> getCollection() {
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
        mCollection = new ArrayList<>();
        initData();
    }

    private void initData() {
        mCollection.addAll(Arrays.asList(
            "Lechuga","Tomate","Arroz","Leche","Salchichas","Lavavajillas","Papel higiénico"/*,
            "Yogur de beber","Pavo","Queso","Comida gatos","Sopa 'Soba'","Detergente","Suaviante",
            "Café","Galletas","Agua","Bebida de cola","Puré de patatas","Manzana"*/
            ));
    }

    //TODO: ABSTRACTION (register inside Messaging Service)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(ItemSendEvent event) {
        addNewItemWith(event.getItemText());
    }

    private void addNewItemWith(CharSequence itemText) {
        if(itemText!=null) {
            mCollection.add(itemText.toString());
            mOnChangeListener.onPropertyChange(addItem, itemText);
        }
    }


    @Override
    public void deInit(){
        mMessagingService.unRegisterMe(this);
    }

}
