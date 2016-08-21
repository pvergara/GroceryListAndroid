package org.ecos.groceryList.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.ecos.groceryList.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ListCreationFragment extends ListFragment {

    private final ArrayList<String> my_array;

    public ListCreationFragment() {
        my_array = new ArrayList<>();
        my_array.addAll(Arrays.asList("1","2","3","4","5","6","7","8","9","10","1","2","3","4","5","6","7","8","9","10"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_creation, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(ListCreationFragment.class.getCanonicalName(),"eeeeeeeeoooooooo");
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, my_array);
        setListAdapter(adapter);

        adapter.notifyDataSetChanged();
        Log.e(ListCreationFragment.class.getCanonicalName(),"ooooooooeeeeeeee");
    }
}