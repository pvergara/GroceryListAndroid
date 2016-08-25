package org.ecos.groceryList.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ecos.groceryList.R;
import org.ecos.groceryList.views.adapters.CustomAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressWarnings("FieldCanBeLocal")
public class ListCreationView extends Fragment implements org.ecos.android.infrastructure.mvvm.view.View {

    public static final boolean ATTACH_TO_ROOT = true;
    private final ArrayList<String> mCollection;
    private FragmentActivity mActivity;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CustomAdapter mAdapter;
    private Unbinder mUnbinder;

    public ListCreationView() {
        mCollection = new ArrayList<>();
        initData();

        loadDependencies();
    }

    private void loadDependencies() {
        mActivity = getActivity();
        mLayoutManager = new LinearLayoutManager(mActivity);
        mAdapter = new CustomAdapter(mCollection);
    }

    private void initData() {
        mCollection.addAll(Arrays.asList("1","2","3","4","5","6","7","8","9","10","1","2","3","4","5","6","7","8","9","10"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_creation, container, !ATTACH_TO_ROOT);
        mUnbinder = ButterKnife.bind(this, rootView);

        initThe(mRecyclerView);

        return rootView;
    }

    private void initThe(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        mActivity.runOnUiThread(runnable);
    }
}