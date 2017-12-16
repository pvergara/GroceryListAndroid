package org.ecos.android.infrastructure.mvvm.binding;

import org.ecos.android.infrastructure.mvvm.view.View;

public interface BindingManager {
    <T> void manageOnChangeFor(Property property, BindingAction<T> bindingAction, View view);
    OnChangeListener getOnChangeListener();
}