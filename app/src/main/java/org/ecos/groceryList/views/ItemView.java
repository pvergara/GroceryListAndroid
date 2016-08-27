package org.ecos.groceryList.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ecos.groceryList.R;

public class ItemView extends Fragment implements org.ecos.android.infrastructure.mvvm.view.View {

    private Activity mActivity;

    public ItemView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item, container, false);
        mActivity = getActivity();
        return rootView;
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        mActivity.runOnUiThread(runnable);
    }
}
