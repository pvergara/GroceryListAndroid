package org.ecos.android.infrastructure.mvvm.binding;

public interface BindingAction<T> {
    void fireAction(T sourceElementValue);
}
