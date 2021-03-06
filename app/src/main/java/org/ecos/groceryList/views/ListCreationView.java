package org.ecos.groceryList.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ecos.android.infrastructure.mvvm.binding.BindingManager;
import org.ecos.android.infrastructure.mvvm.view.FragmentViewBase;
import org.ecos.android.infrastructure.ui.SimpleItemTouchHelperCallback;
import org.ecos.core.infrastructure.messaging.BroadcastingService;
import org.ecos.groceryList.R;
import org.ecos.groceryList.application.GroceryListApplication;
import org.ecos.groceryList.dtos.items.Name;
import org.ecos.groceryList.viewModels.ListCreationViewModel;
import org.ecos.groceryList.views.adapters.CustomAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static org.ecos.groceryList.viewModels.ListCreationViewModel.Properties.addItem;
import static org.ecos.groceryList.viewModels.ListCreationViewModel.Properties.updateItem;

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
    @Inject
    BroadcastingService mBroadcastingService;

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
        GroceryListApplication.getGeneralComponent().inject(this);
        mUnbinder = ButterKnife.bind(this, rootView);

        mLayoutManager = new LinearLayoutManager(mActivity);
        mAdapter = new CustomAdapter(mBroadcastingService);


    }

    private void initTheViewModel() {
        mBindingManager.manageOnChangeFor(addItem,this::onAddItem,this);
        mBindingManager.manageOnChangeFor(updateItem,this::onUpdateItem,this);

        mViewModel.setOnchangeListener(mBindingManager.getOnChangeListener());
        mViewModel.init();

        mAdapter.setCollection(mViewModel.getItems());
    }

    private void initThe(RecyclerView recyclerView) {

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    @SuppressWarnings("unused")
    private void onAddItem(Object newItemSendEventAsObject) {
        mGroceryListView.scrollToPosition(mAdapter.getItemCount() - 1);
        mAdapter.notifyDataSetChanged();
    }


    private void onUpdateItem(Object itemsNameAsObject) {
        if(!(itemsNameAsObject instanceof Name))
            return;

        mAdapter.prepareToShowUpdate((Name)itemsNameAsObject);
        mAdapter.notifyDataSetChanged();
    }

}