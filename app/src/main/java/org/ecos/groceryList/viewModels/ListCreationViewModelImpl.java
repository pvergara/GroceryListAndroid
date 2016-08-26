package org.ecos.groceryList.viewModels;

import java.util.ArrayList;
import java.util.Arrays;

public class ListCreationViewModelImpl implements ListCreationViewModel {
    private ArrayList<String> mCollection;

    public ArrayList<String> getCollection() {
        return mCollection;
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

}
