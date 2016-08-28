package org.ecos.android.infrastructure.mvvm.binding;


import org.ecos.android.infrastructure.mvvm.view.View;

import java.util.HashMap;
import java.util.Map;

public class BindingManagerImpl implements BindingManager, OnChangeListener {
    private Map<Property, BindingAction> mActions = new HashMap<>();
    private Map<Property, View> mViews = new HashMap<>();

    @Override
    public <T> void manageOnChangeFor(Property property, BindingAction<T> bindingAction, View view) {
        mActions.put(property, bindingAction);
        mViews.put(property, view);
    }

    @Override
    public OnChangeListener getOnChangeListener() {
        return this;
    }

    @Override
    public <T> void onPropertyChange(Property property, final T sourceElementValue) {
        if (mActions.containsKey(property) && mViews.containsKey(property)) {
            final BindingAction action = mActions.get(property);
            View view = mViews.get(property);
            Runnable runnable = new Runnable() {
                public void run() {
                    action.fireAction(sourceElementValue);
                }
            };
            view.runOnUiThread(runnable);

        }
    }
}
