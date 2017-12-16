package org.ecos.groceryList.views;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.ecos.android.infrastructure.mvvm.binding.BindingAction;
import org.ecos.android.infrastructure.mvvm.binding.BindingManager;
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

import static org.ecos.groceryList.viewModels.ItemViewModel.Properties.changeActionStatus;
import static org.ecos.groceryList.viewModels.ItemViewModel.Properties.changeItemText;

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
        mUnbinder = ButterKnife.bind(this,rootView);

        GroceryListApplication.getGeneralComponent().inject(this);
    }

    //TODO: To abstract
    private void initTheViewModel() {
        mBindingManager.manageOnChangeFor(changeItemText,mBindingActionOnItemTextChange,this);
        mBindingManager.manageOnChangeFor(changeActionStatus,mBindingActionOnActionButtonChange,this);

        mViewModel.setOnchangeListener(mBindingManager.getOnChangeListener());
        mViewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_item, container, false);
        loadDependencies(rootView);

        initTheViewModel();

        initTheViews();

        return rootView;
    }

    private void initTheViews() {
        mItemText.setFocusableInTouchMode(true);
        mItemText.requestFocus();
        mItemText.setOnKeyListener((v, keyCode, event) -> {
            // If the event is a key-down event on the "enter" button
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Perform action on key press
                mViewModel.sendItemText();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mViewModel.deInit();
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
