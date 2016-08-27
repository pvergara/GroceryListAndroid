package org.ecos.groceryList.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ecos.android.infrastructure.mvvm.view.FragmentViewBase;
import org.ecos.groceryList.R;
import org.ecos.groceryList.application.GroceryListApplication;
import org.ecos.groceryList.viewModels.ListCreationViewModel;
import org.ecos.groceryList.views.adapters.CustomAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressWarnings("FieldCanBeLocal")
public class ListCreationView extends FragmentViewBase {

    public static final boolean ATTACH_TO_ROOT = true;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private CustomAdapter mAdapter;
    private Unbinder mUnbinder;

    @Inject
    ListCreationViewModel mViewModel;

    private GroceryListApplication mApplication;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loadDependencies();

        initTheViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_creation, container, !ATTACH_TO_ROOT);
        mUnbinder = ButterKnife.bind(this, rootView);

        initThe(mRecyclerView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void loadDependencies() {
        mActivity = getActivity();
        mLayoutManager = new LinearLayoutManager(mActivity);
        mAdapter = new CustomAdapter();

        mApplication = (GroceryListApplication) mActivity.getApplication();

        mApplication.getGeneralComponent().inject(this);

    }

    private void initTheViewModel() {
        mViewModel.init();
        mAdapter.setCollection(mViewModel.getCollection());
    }

    private void initThe(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}