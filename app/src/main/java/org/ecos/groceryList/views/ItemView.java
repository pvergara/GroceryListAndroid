package org.ecos.groceryList.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ecos.android.infrastructure.mvvm.view.FragmentViewBase;
import org.ecos.groceryList.R;

public class ItemView extends FragmentViewBase {
    public ItemView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.fragment_item, container, false);
    }
}
