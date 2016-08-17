package org.ecos.android.infrastructure.mvvm.binding;

import org.ecos.android.infrastructure.mvvm.view.View;

public interface BindingManager {
    void manageOnChangeFor(Property property, BindingAction bindingAction, View view);
    OnChangeListener getOnChangeListener();
}
