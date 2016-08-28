package org.ecos.groceryList.viewModels;

import org.ecos.android.infrastructure.messaging.MessagingService;
import org.ecos.android.infrastructure.mvvm.binding.OnChangeListener;
import org.ecos.groceryList.events.ItemSendEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;

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
        mCollection.addAll(Arrays.asList("1","2","3","4","5","6","7","8","9","10","1","2","3","4","5","6","7","8","9","10"));
    }

    //TODO: ABSTRACTION (register inside Messaging Service)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(ItemSendEvent event) {
        addNewItemWith(event.getItemText());
    }

    private void addNewItemWith(CharSequence itemText) {
        if(itemText!=null) {
            mCollection.add(itemText.toString());
            mOnChangeListener.onPropertyChange(Properties.addItem, itemText);
        }
    }


    @Override
    public void deInit(){
        mMessagingService.unRegisterMe(this);
    }

}
