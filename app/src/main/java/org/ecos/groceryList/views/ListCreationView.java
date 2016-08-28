package org.ecos.groceryList.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ecos.android.infrastructure.mvvm.binding.BindingAction;
import org.ecos.android.infrastructure.mvvm.binding.BindingManager;
import org.ecos.android.infrastructure.mvvm.view.FragmentViewBase;
import org.ecos.groceryList.R;
import org.ecos.groceryList.application.GroceryListApplication;
import org.ecos.groceryList.viewModels.ListCreationViewModel;
import org.ecos.groceryList.views.adapters.CustomAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static org.ecos.groceryList.viewModels.ListCreationViewModel.Properties.addItem;

@SuppressWarnings("FieldCanBeLocal")
public class ListCreationView extends FragmentViewBase {

    public static final boolean ATTACH_TO_ROOT = true;

    @BindView(R.id.listCreationGroceryList)
    RecyclerView mGroceryListView;
    private LinearLayoutManager mLayoutManager;

    private CustomAdapter mAdapter;
    private Unbinder mUnbinder;

    @Inject
    ListCreationViewModel mViewModel;
    @Inject
    BindingManager mBindingManager;

    private GroceryListApplication mApplication;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_creation, container, !ATTACH_TO_ROOT);

        loadDependencies(rootView);

        initTheViewModel();

        initThe(mGroceryListView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewModel.deInit();
        mUnbinder.unbind();
    }

    private void loadDependencies(View rootView) {
        mActivity = getActivity();
        mLayoutManager = new LinearLayoutManager(mActivity);
        mAdapter = new CustomAdapter();

        mApplication = (GroceryListApplication) mActivity.getApplication();

        mApplication.getGeneralComponent().inject(this);
        mUnbinder = ButterKnife.bind(this, rootView);

    }

    private void initTheViewModel() {
        mBindingManager.manageOnChangeFor(addItem,mBindingActionOnGroceryListAddItem,this);

        mViewModel.setOnchangeListener(mBindingManager.getOnChangeListener());
        mViewModel.init();

        mAdapter.setCollection(mViewModel.getCollection());
    }

    private void initThe(RecyclerView recyclerView) {
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    private BindingAction<CharSequence> mBindingActionOnGroceryListAddItem = sentValue ->{
        mAdapter.notifyDataSetChanged();
        mGroceryListView.scrollToPosition(mAdapter.getItemCount() - 1);
    };

}