package org.ecos.android.infrastructure.mvvm.binding;


import org.ecos.android.infrastructure.mvvm.view.View;

import java.util.HashMap;
import java.util.Map;

public class BindingManagerImpl implements BindingManager, OnChangeListener {
    private Map<Property, BindingAction> mActions = new HashMap<>();
    private Map<Property, View> mViews = new HashMap<>();

    @Override
    public void manageOnChangeFor(Property property, BindingAction bindingAction, View view) {
        mActions.put(property, bindingAction);
        mViews.put(property, view);
    }

    @Override
    public OnChangeListener getOnChangeListener() {
        return this;
    }

    @Override
    public void onChange(Property property, final Object sourceElementValue) {
        if (mActions.containsKey(property) && mActions.containsKey(property)) {
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
