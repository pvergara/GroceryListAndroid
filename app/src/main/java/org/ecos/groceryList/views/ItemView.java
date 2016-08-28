package org.ecos.groceryList.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.ecos.android.infrastructure.mvvm.binding.BindingAction;
import org.ecos.android.infrastructure.mvvm.binding.BindingManager;
import org.ecos.android.infrastructure.mvvm.binding.Property;
import org.ecos.android.infrastructure.mvvm.view.FragmentViewBase;
import org.ecos.groceryList.R;
import org.ecos.groceryList.application.GroceryListApplication;
import org.ecos.groceryList.viewModels.ItemViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class ItemView extends FragmentViewBase {
    private Unbinder mUnbinder;

    @Inject
    ItemViewModel mViewModel;
    @Inject
    BindingManager mBindingManager;

    @BindView(R.id.itemViewItemText)
    TextView mItemText;

    @BindView(R.id.itemViewActionButton)
    ImageButton mActionButton;

    public ItemView() {}

    //TODO: Generalize
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    //TODO: To abstract
    private void loadDependencies(View rootView) {
        mActivity = getActivity();
        GroceryListApplication application = (GroceryListApplication) mActivity.getApplication();
        mUnbinder = ButterKnife.bind(this,rootView);

        application.getGeneralComponent().inject(this);

    }

    //TODO: To abstract
    private void initTheViewModel() {
        mBindingManager.manageOnChangeFor(Property.from(R.id.itemViewItemText),mBindingActionOnItemTextChange,this);
        mBindingManager.manageOnChangeFor(Property.from(R.id.itemViewActionButton),mBindingActionOnActionButtonChange,this);

        mViewModel.setOnchangeListener(mBindingManager.getOnChangeListener());
        mViewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_item, container, false);
        loadDependencies(rootView);

        initTheViewModel();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnTextChanged(R.id.itemViewItemText)
    protected void onTextChanged(CharSequence text) {
        mViewModel.setItemText(text);
    }

    @OnClick(R.id.itemViewActionButton)
    protected void onActionButtonClick(){
        mViewModel.sendItemText();
    }

    private BindingAction<CharSequence> mBindingActionOnItemTextChange = sentValue -> mItemText.setText(sentValue);
    private BindingAction<Boolean> mBindingActionOnActionButtonChange = sentValue -> mActionButton.setEnabled(sentValue);
}
